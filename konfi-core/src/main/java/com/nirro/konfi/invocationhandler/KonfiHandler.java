package com.nirro.konfi.invocationhandler;

import com.nirro.konfi.converter.PropertyConverter;
import com.nirro.konfi.exception.InvalidReturnTypeException;
import com.nirro.konfi.exception.KonfiException;
import com.nirro.konfi.repository.Repository;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

public class KonfiHandler implements InvocationHandler {

    private static final String JAVA_UTIL_OPTIONAL = "java.util.Optional";
    private static final String JAVA_UTIL_LIST = "java.util.List";
    private static final String JAVA_UTIL_SET = "java.util.Set";
    private static final String EQUALS_METHOD = "equals";
    private static final String HASH_CODE_METHOD = "hashCode";
    private static final String TO_STRING_METHOD = "toString";


    private final Map<Method, MethodMetadata> methodMetadataMap;
    private final PropertyConverter propertyConverter;
    private final List<Repository> repositories;


    public KonfiHandler(PropertyConverter propertyConverter,
                        Map<Method, MethodMetadata> methodMetadataMap,
                        List<Repository> repositories) {
        this.propertyConverter = propertyConverter;
        this.methodMetadataMap = methodMetadataMap;
        this.repositories = repositories;
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
                String value = getValue(methodMetadata.key());
                return invoke(value, methodMetadata.returnType());
        }
    }

    public void refreshAll() {
         repositories.forEach(Repository::refresh);
    }

    /**
     *
     * @param key property key
     * @return the first value associated with the key found in the repositories,
     *         null if no values were found
     */
    private String getValue(String key) {
        String value;
        for (var repository : repositories) {
            value = repository.getProperty(key);
            if(value != null) {
                return value;
            }
        }
        return null;
    }

    private Object invoke(String value, Type type) {
        if (type instanceof ParameterizedType parameterizedType) {
            //Type is optional, List or Set
            return handleFirstLevelParameterizedType(value, (ParameterizedType) type, parameterizedType);
        }
        else if (type instanceof Class<?>) {
            // Type is String, Long, Boolean etc
            return propertyConverter.convert(value, type, false);
        } else {
            // Unsupported type
            throw new KonfiException(); //TODO
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
                    throw new KonfiException(); //TODO
                }
            case JAVA_UTIL_LIST:
                return propertyConverter.convertList(value, type.getActualTypeArguments()[0], false);
            case JAVA_UTIL_SET:
                return propertyConverter.convertSet(value, type.getActualTypeArguments()[0], false);
            default:
                throw new KonfiException(); //TODO

        }
    }

    private Object handleSecondLevelParameterizedType(String value, ParameterizedType nestedParameterizedType) {
        var rawTypeName =  nestedParameterizedType.getRawType().getTypeName();
        return switch (rawTypeName) {
            case JAVA_UTIL_LIST -> propertyConverter.convertList(value, nestedParameterizedType.getActualTypeArguments()[0], true);
            case JAVA_UTIL_SET -> propertyConverter.convertSet(value, nestedParameterizedType.getActualTypeArguments()[0], true);
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

   /*
   public String document() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Documentation for MyProperties:")
                .append(System.lineSeparator())
                .append("Repositories:")
                .append(System.lineSeparator());
        IntStream.range(0, repositories.size())
                .forEach(index -> sb.append(index).append(". ").append(repositories.get(index)).append(System.lineSeparator()));
        return sb.toString();
    }


    private static String formatAsTable(List<List<String>> rows)
    {
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows)
        {
            for (int i = 0; i < row.size(); i++)
            {
                if(row.get(i) != null) {
                    maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
                }
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths)
        {
            formatBuilder.append("%-").append(maxLength + 8).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows)
        {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }
    */
}
