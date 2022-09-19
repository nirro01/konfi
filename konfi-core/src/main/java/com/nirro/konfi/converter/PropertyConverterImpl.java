package com.nirro.konfi.converter;

import com.nirro.konfi.exception.InvalidReturnTypeException;
import com.nirro.konfi.exception.InvalidValueException;
import com.nirro.konfi.exception.MissingValueException;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PropertyConverterImpl implements PropertyConverter {

    private static final HashMap<Type, Function<String, ?>> propertyConverters;
    private final String collectionSeparator;

    public PropertyConverterImpl(String collectionSeparator) {
        this.collectionSeparator = collectionSeparator;
    }

    static {
        propertyConverters = new HashMap<>();

        // built-in property converters
        register(String.class, PropertyConverters::mapToString);
        register(Integer.class, PropertyConverters::mapToInteger);
        register(Boolean.class, PropertyConverters::mapToBoolean);
        register(Double.class, PropertyConverters::mapToDouble);
        register(Float.class, PropertyConverters::mapToFloat);
        register(Long.class, PropertyConverters::mapToLong);

    }

    private static void register(Type type, Function<String, ?> propertyConverter) {
        propertyConverters.put(type, propertyConverter);
    }

    @Override
    public Object convert(String value, Type type, boolean optional) {
        return doGenericConvert(value, type, optional, this::doConvert);
    }

    @Override
    public Object convertList(String value, Type type, boolean optional) {
        return doGenericConvert(value, type, optional, this::doConvertList);
    }

    @Override
    public Object convertSet(String value, Type type, boolean optional) {
        return doGenericConvert(value, type, optional, this::doConvertSet);
    }

    private Object doConvert(String value, Function<String, ?> converter) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(converter);
        return converter.apply(value);
    }
    private Object doConvertSet(String value, Function<String, ?> converter) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(converter);
        return Arrays.stream(value.split(collectionSeparator)).map(converter).collect(Collectors.toSet());
    }
    private Object doConvertList(String value, Function<String, ?> converter) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(converter);
        return Arrays.stream(value.split(collectionSeparator)).map(converter).toList();
    }

    private Object doGenericConvert(String value, Type type, boolean optional, BiFunction<String, Function<String, ?>, Object> func) {
        Function<String, ?> converter = propertyConverters.get(type);
        if (converter != null) {
            if (optional) {
                if (value != null) {
                    return Optional.ofNullable(func.apply(value, converter));
                } else {
                    return Optional.empty();
                }
            } else {
                if (value != null) {
                    return func.apply(value, converter);
                } else {
                    throw new MissingValueException();
                }
            }
        } else {
            throw new InvalidReturnTypeException();
        }
    }

    static class PropertyConverters {
        private PropertyConverters() {
        }

        public static String mapToString(String value) {
            return value;
        }


        public static Integer mapToInteger(String value) {
            try {
                return Integer.valueOf(value);
            } catch (Exception e) {
                throw new InvalidValueException(e);
            }
        }


        public static Boolean mapToBoolean(String value) {
            return Boolean.valueOf(value);
        }


        public static Double mapToDouble(String value) {
            try {
                return Double.valueOf(value);
            } catch (Exception e) {
                throw new InvalidValueException(e);
            }
        }


        public static Float mapToFloat(String value) {
            try {
                return Float.valueOf(value);
            } catch (Exception e) {
                throw new InvalidValueException(e);
            }
        }


        public static Long mapToLong(String value) {
            try {
                return Long.valueOf(value);
            } catch (Exception e) {
                throw new InvalidValueException(e);
            }
        }
    }
}
