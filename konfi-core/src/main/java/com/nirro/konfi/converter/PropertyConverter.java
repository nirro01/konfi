package com.nirro.konfi.converter;

import java.lang.reflect.Type;

/**
 * A converter which can convert an <code>String</code> representation
 * value into different types
 */
public interface PropertyConverter {
    /**
     * convert to single value property or Optional value
     * @param value value
     * @param type type to convert to
     * @param optional is optional
     * @return value converted to the right type
     */
    Object convert(String value, Type type, boolean optional);

    /**
     * convert to list property or Optional List
     * @param value value
     * @param type type to convert to
     * @param optional is optional
     * @return value converted to the right type
     */
    Object convertList(String value, Type type, boolean optional);

    /**
     * convert to Set property or Optional Set
     * @param value value
     * @param type type to convert to
     * @param optional is optional
     * @return value converted to the right type
     */
    Object convertSet(String value, Type type, boolean optional);
}
