package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class ExternalPropertiesFileTest {

    @Test
    void read() throws IOException {
        File file = File.createTempFile( "app", "properties");
        file.deleteOnExit();
        FileWriter myWriter = new FileWriter(file);
        myWriter.write("value=a");
        myWriter.close();

        var fileSource = PropertiesSources.newExternalFileSource(file.getAbsolutePath());
        var properties = fileSource.get();
        Assertions.assertEquals("a", properties.getProperty("value"));
        Assertions.assertNull(properties.getProperty("missing"));
    }

    @Test
    void readMissingResource() {
        var fileSource = PropertiesSources.newExternalFileSource("missing.properties");
        Assertions.assertThrows(SourceAccessException.class, fileSource::get);
    }
}
