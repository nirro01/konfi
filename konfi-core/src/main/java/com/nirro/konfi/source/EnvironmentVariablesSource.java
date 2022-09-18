package com.nirro.konfi.source;


public class EnvironmentVariablesSource extends MapSource {

    public EnvironmentVariablesSource() {
        super(System.getenv());
    }
}
