package com.nirro.konfi.repository;

/**
 * Repository manage the access to the property source
 */
public interface Repository {
    /**
     * refresh the repository.
     */
    void refresh();

    /**
     *
     * @param key property key
     * @return value associated with the key if exists, null otherwise
     */
    String getProperty(String key);
}
