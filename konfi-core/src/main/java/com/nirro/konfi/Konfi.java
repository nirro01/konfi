package com.nirro.konfi;

import com.nirro.konfi.annotation.KonfiProperty;
import com.nirro.konfi.converter.PropertyConverterImpl;
import com.nirro.konfi.invocationhandler.KonfiHandler;
import com.nirro.konfi.invocationhandler.MethodMetadata;
import com.nirro.konfi.reflection.Types;
import com.nirro.konfi.repository.Repository;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Entry point for the library
 */
public final class Konfi {

    private Konfi() {
    }

    /**
     * creates a step builder for the properties instance
     *
     * @param type interface with methods annotated using {@code KonfiAnnotation}
     * @param <T> class type
     * @return RepositoriesStep
     * @see Builder
     * @see RepositoriesStep
     * @see OptionsStep
     * @see Builder#build()
     */
    public static <T> RepositoriesStep<T> builder(Class<T> type) {
        return new Builder<>(type);
    }

    /**
     * Repositories Step
     * @param <T> class type
     */
    public interface RepositoriesStep<T> {

        /**
         *
         * @param repositories list of repositories order by lookup order
         * @return OptionsStep
         */
        OptionsStep<T> repositories(List<Repository> repositories);
    }

    /**
     * Options Step
     * include all optional steps and the final build method
     * @param <T> class type
     */
    public interface OptionsStep<T> {

        /**
         * regex to be used for splitting a list or set values
         * @param regex – the delimiting regular expression
         * @return OptionsStep
         * @see String#split(String)
         */
        OptionsStep<T> collectionSeparator(String regex);

        /**
         * @param preProcessors preProcessors to be applied on each value
         * @return OptionsStep
         */
        OptionsStep<T> preProcessors(List<UnaryOperator<String>> preProcessors);

        /**
         * @return a new instance built from the current state of this builder
         */
        T build();
    }

    /**
     * A step builder of konfi properties instance
     * builders are created by invoking {@link Konfi#builder(Class)}
     * @param <T> class type
     */
    public static class Builder<T> implements RepositoriesStep<T>, OptionsStep<T> {
        private final Class<T> target;
        private List<Repository> repositories;
        private String collectionSeparator = ",";
        private List<UnaryOperator<String>> preProcessors = List.of();

        private Builder(Class<T> target) {
            this.target = target;
        }

        @Override
        public OptionsStep<T> repositories(List<Repository> repositories) {
            this.repositories = repositories;
            return this;
        }

        @Override
        public OptionsStep<T> collectionSeparator(String collectionSeparator) {
            this.collectionSeparator = collectionSeparator;
            return this;
        }

        @Override
        public OptionsStep<T> preProcessors(List<UnaryOperator<String>> preProcessors) {
            this.preProcessors = preProcessors;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T build() {
            var propertyConverter = new PropertyConverterImpl(collectionSeparator);
            var metadataMap = getMethodMetadataMap(target);
            repositories.parallelStream().forEach(Repository::refresh);
            var handler = new KonfiHandler(propertyConverter, metadataMap, repositories, preProcessors);
            return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class<?>[]{target}, handler);
        }

        private Map<Method, MethodMetadata> getMethodMetadataMap(Class<?> targetType) {
            return Arrays.stream(targetType.getMethods())
                    .map(method -> getMethodMetadata(targetType, method))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toMap(MethodMetadata::method, Function.identity()));
        }

        private Optional<MethodMetadata> getMethodMetadata(Class<?> targetType, Method method) {
            final KonfiProperty konfiProperty = method.getAnnotation(KonfiProperty.class);
            if (konfiProperty != null) {
                var key = konfiProperty.key();
                var description = konfiProperty.description();
                var deprecated = konfiProperty.deprecated();
                var since = konfiProperty.since();
                var returnType = Types.resolve(targetType, method.getReturnType(), method.getGenericReturnType());
                var metadata = new MethodMetadata(key, description, deprecated, since, returnType, method);
                return Optional.of(metadata);
            }
            return Optional.empty();
        }

    }

    /**
     * convenient method for refreshing all repositories associated with the instance
     * @param proxy instance to refresh
     */
    public static void refreshAll(Object proxy) {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxy);
        if (invocationHandler instanceof KonfiHandler konfiHandler) {
            konfiHandler.refreshAll();
        }
    }
}
