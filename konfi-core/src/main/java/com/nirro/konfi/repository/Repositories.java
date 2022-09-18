package com.nirro.konfi.repository;

import com.nirro.konfi.source.Source;

public class Repositories {
    private Repositories() {
        super();
    }

    public static Repository newRepository(Source source) {
        return new RepositoryImpl(source);
    }

}
