package com.nirro.konfi.repository;

import java.util.List;

/**
 * repository that composite other repositories
 */
public class CompositeRepository implements Repository{
    private final List<Repository> repositories;

    /**
     * Construct a new CompositeRepository
     *
     * @param repositories list of repositories
     */
    public CompositeRepository(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void refresh() {
        for(var repository : repositories) {
            repository.refresh();
        }
    }

    @Override
    public String getProperty(String key) {

        String value;
        for (var repository : repositories) {
            value = repository.getProperty(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
