package com.nirro.konfi.converter;

import java.lang.reflect.Type;

public interface PropertyConverter {
    Object convert(String value, Type type, boolean optional);

    Object convertList(String value, Type type, boolean optional);

    Object convertSet(String value, Type type, boolean optional);
}
