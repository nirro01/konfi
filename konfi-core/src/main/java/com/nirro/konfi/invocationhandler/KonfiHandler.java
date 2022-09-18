package com.nirro.konfi.invocationhandler;

import com.nirro.konfi.converter.PropertyConverter;
import com.nirro.konfi.exception.InvalidReturnTypeException;
import com.nirro.konfi.repository.Repository;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * An {@link InvocationHandler} that forwards methods annotated with
 * {@link com.nirro.konfi.annotation.KonfiProperty} to the type safe
 * representation of the value found the using the property key
 * in the lookup repositories
 */
public class KonfiHandler implements InvocationHandler {

    private static final String JAVA_UTIL_OPTIONAL = "java.util.Optional";
    private static final String JAVA_UTIL_LIST = "java.util.List";
    private static final String JAVA_UTIL_SET = "java.util.Set";
    private static final String EQUALS_METHOD = "equals";
    private static final String HASH_CODE_METHOD = "hashCode";
    private static final String TO_STRING_METHOD = "toString";


    private final Map<Method, MethodMetadata> methodMetadataMap;
    private final PropertyConverter propertyConverter;
    private final Repository repository;
    private final UnaryOperator<String> preProcessor;

    /**
     * constructs a new KonfiHandler
     *
     * @param propertyConverter propertyConverter
     * @param methodMetadataMap methodMetadataMap
     * @param repository Repository
     * @param preProcessors preProcessors
     */
    public KonfiHandler(PropertyConverter propertyConverter,
                        Map<Method, MethodMetadata> methodMetadataMap,
                        Repository repository,
                        List<UnaryOperator<String>> preProcessors) {
        this.propertyConverter = propertyConverter;
        this.methodMetadataMap = methodMetadataMap;
        this.repository = repository;
        this.preProcessor = combinePreProcessors(preProcessors);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        switch (method.getName()) {
            case EQUALS_METHOD:
                return invokeEquals(args);
            case HASH_CODE_METHOD:
                return hashCode();
            case TO_STRING_METHOD:
                return toString();
            default:
                MethodMetadata methodMetadata = methodMetadataMap.get(method);
                String value = repository.getProperty(methodMetadata.key());
                if (value != null) {
                    value = preProcessor.apply(value);
                }
                return invoke(value, methodMetadata.returnType());
        }
    }

    /**
     * refresh all property sources
     */
    public void refresh() {
        repository.refresh();
    }

    private Object invoke(String value, Type type) {
        if (type instanceof ParameterizedType parameterizedType) {
            //Type is optional, List or Set
            return handleFirstLevelParameterizedType(value, (ParameterizedType) type, parameterizedType);
        } else if (type instanceof Class<?>) {
            // Type is String, Long, Boolean etc
            return propertyConverter.convert(value, type, false);
        } else {
            // Unsupported type
            throw new InvalidReturnTypeException();
        }
    }

    private Object handleFirstLevelParameterizedType(String value, ParameterizedType type, ParameterizedType parameterizedType) {
        String rawTypeName = parameterizedType.getRawType().getTypeName();
        switch (rawTypeName) {
            case JAVA_UTIL_OPTIONAL:
                var nestedType = parameterizedType.getActualTypeArguments()[0];
                if (nestedType instanceof ParameterizedType nestedParameterizedType) {
                    return handleSecondLevelParameterizedType(value, nestedParameterizedType);
                } else if (nestedType instanceof Class<?>) {
                    return propertyConverter.convert(value, nestedType, true);
                } else {
                    throw new InvalidReturnTypeException();
                }
            case JAVA_UTIL_LIST:
                return propertyConverter.convertList(value, type.getActualTypeArguments()[0], false);
            case JAVA_UTIL_SET:
                return propertyConverter.convertSet(value, type.getActualTypeArguments()[0], false);
            default:
                throw new InvalidReturnTypeException();

        }
    }

    private Object handleSecondLevelParameterizedType(String value, ParameterizedType nestedParameterizedType) {
        var rawTypeName = nestedParameterizedType.getRawType().getTypeName();
        return switch (rawTypeName) {
            case JAVA_UTIL_LIST ->
                    propertyConverter.convertList(value, nestedParameterizedType.getActualTypeArguments()[0], true);
            case JAVA_UTIL_SET ->
                    propertyConverter.convertSet(value, nestedParameterizedType.getActualTypeArguments()[0], true);
            default -> throw new InvalidReturnTypeException();
        };
    }

    private Object invokeEquals(Object[] args) {
        try {
            Object otherHandler = args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
            return equals(otherHandler);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private UnaryOperator<String> combinePreProcessors(List<UnaryOperator<String>> preprocessors) {
        return preprocessors.stream().reduce(
                identity -> identity,
                (a, b) -> a.andThen(b)::apply
        );
    }
}
