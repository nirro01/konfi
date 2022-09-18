package com.nirro.konfi.invocationhandler;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * MethodMetadata
 * @param key property key
 * @param description property description
 * @param deprecated is deprecated
 * @param since application version that introduced the property
 * @param returnType method return type
 * @param method method
 */
public record MethodMetadata(String key,
                             String description,
                             boolean deprecated,
                             String since,
                             Type returnType,
                             Method method) {
}
