package com.nirro.konfi.invocationhandler;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public record MethodMetadata(String key,
                             String description,
                             boolean deprecated,
                             String since,
                             Type returnType,
                             Method method) {
}
