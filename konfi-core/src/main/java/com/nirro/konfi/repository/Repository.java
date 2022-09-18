package com.nirro.konfi.repository;

public interface Repository {
    /**
     * refresh the repository.
     * must be called before first usage
     */
    void refresh();

    /**
     *
     * @param key property key
     * @return value associated with the key if exists, null otherwise
     */
    String getProperty(String key);
}
