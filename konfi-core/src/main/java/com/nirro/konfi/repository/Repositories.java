package com.nirro.konfi.repository;

import com.nirro.konfi.source.PropertiesSource;

/**
 * Factory methods for repositories
 */
public class Repositories {
    private Repositories() {
        super();
    }

    /**
     * create a new repository
     * @param propertiesSource property source
     * @return new Repository
     */
    public static Repository newRepository(PropertiesSource propertiesSource) {
        return new RepositoryImpl(propertiesSource);
    }

}
