package org.de.challenge.converter.test;


import org.de.challenge.converter.Converter;
import org.de.challenge.domain.Season;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ConverterTest {
private String samplePath="src/test/resources/data-sample.json";
    @Test
    public void ConversionTest() throws IOException {
        String json= loadJson();
        Season[] seasons= Converter.fromJsonString(json);
        assertEquals(2,seasons.length);
    }

    //TODO: test objects with OpenPojo https://github.com/OpenPojo/openpojo

    private String loadJson() throws IOException {
        return new String(Files.readAllBytes(Paths.get(samplePath)));
    }

}
