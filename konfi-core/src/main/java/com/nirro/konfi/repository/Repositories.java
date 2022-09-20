package com.nirro.konfi.repository;

import com.nirro.konfi.source.Source;

/**
 * Factory methods for repositories
 */
public class Repositories {
    private Repositories() {
        super();
    }

    /**
     * @param source property source
     * @return new Repository
     */
    public static Repository newRepository(Source source) {
        return new RepositoryImpl(source);
    }

}
