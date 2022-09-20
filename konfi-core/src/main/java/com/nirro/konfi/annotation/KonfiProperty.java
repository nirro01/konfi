package com.nirro.konfi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Apply to the methods that corresponds to property to mark it as
 * Konfi property.
 */
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface KonfiProperty {
    /**
     * @return property key
     */
    String key();

    /**
     * @return property description
     */
    String description() default "";

    /**
     * @return is deprecated
     */
    boolean deprecated() default false;

    /**
     * @return application version that introduced the property
     */
    String since() default "0.0.1";
}
