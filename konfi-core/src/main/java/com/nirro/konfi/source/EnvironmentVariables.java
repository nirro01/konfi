package com.nirro.konfi.source;

/**
 * Environment variables properties supplier
 */
public class EnvironmentVariables extends PropertiesMap {

    /**
     * construct a new EnvironmentVariablesSource
     */
    public EnvironmentVariables() {
        super(System.getenv());
    }
}
