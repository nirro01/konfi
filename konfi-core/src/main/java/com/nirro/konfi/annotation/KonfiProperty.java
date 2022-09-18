package com.nirro.konfi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface KonfiProperty {
    String key();
    String description() default "";
    boolean deprecated() default false;
    String since() default "0.0.1";
}
