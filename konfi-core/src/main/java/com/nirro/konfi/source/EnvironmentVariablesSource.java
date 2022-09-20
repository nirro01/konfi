package com.nirro.konfi.source;

/**
 * Environment variables properties source
 */
public class EnvironmentVariablesSource extends MapSource {

    /**
     * construct a new EnvironmentVariablesSource
     */
    public EnvironmentVariablesSource() {
        super(System.getenv());
    }
}
