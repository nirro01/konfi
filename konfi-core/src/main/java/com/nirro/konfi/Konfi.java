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

public final class Konfi {

    private Konfi() {
    }

    public static void refreshAll(Object proxy) {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxy);
        if(invocationHandler instanceof KonfiHandler konfiHandler) {
            konfiHandler.refreshAll();
        }
    }

    public static <T> RepositoriesStep<T> builder(Class<T> type) {
        return new Builder<>(type);
    }

    public interface RepositoriesStep<T> {
        OptionsStep<T> repositories(List<Repository> repositories);
    }

    public interface OptionsStep<T> {
        OptionsStep<T> collectionSeparator(String collectionSeparator);
        OptionsStep<T> preProcessors(List<UnaryOperator<String>> preProcessors);
        T build();
    }

    public static class Builder<T> implements RepositoriesStep<T>, OptionsStep<T> {
        private final Class<T> target;
        private List<Repository> repositories;
        private String collectionSeparator = ",";
        private List<UnaryOperator<String>> preProcessors = List.of();

        public Builder(Class<T> target) {
            this.target = target;
        }

        @Override
        public OptionsStep<T> repositories(List<Repository> repositories) {
            this.repositories = repositories;
            return this;
        }

        public OptionsStep<T> collectionSeparator(String collectionSeparator) {
            this.collectionSeparator = collectionSeparator;
            return this;
        }

        public OptionsStep<T> preProcessors(List<UnaryOperator<String>> preProcessors) {
            this.preProcessors = preProcessors;
            return this;
        }

        @SuppressWarnings("unchecked")
        public T build() {
            var propertyConverter = new PropertyConverterImpl(collectionSeparator);
            var metadataMap = getMethodMetadataMap(target);
            repositories.parallelStream().forEach(Repository::refresh);
            var handler = new KonfiHandler(propertyConverter, metadataMap, repositories, preProcessors);
            return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class<?>[] {target}, handler);
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
            if(konfiProperty != null) {
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

/*        public static String document(Object proxy) {
            InvocationHandler innerProxy = Proxy.getInvocationHandler(proxy);
            if (innerProxy instanceof KonfiHandler handler) {
                return handler.document();
            } else {
                throw new IllegalArgumentException("proxy is not a proxy instance of KonfiHandler");
            }

        }*/
    }
}
