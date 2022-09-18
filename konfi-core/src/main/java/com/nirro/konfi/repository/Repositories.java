package com.nirro.konfi.repository;

import com.nirro.konfi.source.PropertiesSource;

import java.util.List;

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

    /**
     * create a new Composite Repository
     * @param repositories list of repositories
     * @return new Composite Repository
     */
    public static Repository newCompositeRepository(List<Repository> repositories) {
        return new CompositeRepository(repositories);
    }

}
