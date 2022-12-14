package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.util.Properties;

/**
 * Properties source
 */
public interface PropertiesSource {

    /**
     * get the properties from the source
     *
     * @return Properties
     * @throws SourceAccessException when unable to get the properties from the source
     */
    Properties get() throws SourceAccessException;

}
