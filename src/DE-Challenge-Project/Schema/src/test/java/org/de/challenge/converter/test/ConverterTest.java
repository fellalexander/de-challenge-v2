package org.de.challenge.converter.test;


import org.de.challenge.converter.Converter;
import org.de.challenge.domain.Season;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class ConverterTest {
private String samplePath1="src/test/resources/data-sample.json";
private String samplePath2="src/test/resources/data-sample2.json";
private String samplePath3="src/test/resources/data-sample3.json";
private String samplePath4="src/test/resources/data-sample4.json";

    @Test
    public void ConversionTest() throws IOException {
        String json= loadJson(samplePath1);
        Season[] seasons= Converter.fromJsonString(json);
        assertEquals(2,seasons.length);
    }

    @Test
    public void ConversionTestOtherTimeFormat() throws IOException {
        String json= loadJson(samplePath2);
        Season[] seasons= Converter.fromJsonString(json);
        assertEquals(2,seasons.length);
    }

    @Test
    public void ConversionTestExtraField() throws IOException {
        String json= loadJson(samplePath3);
        Season[] seasons= Converter.fromJsonString(json);
        assertEquals(2,seasons.length);
    }

    @Test
    public void ConversionTestYetAnotherTimeFormat() throws IOException {
        String json= loadJson(samplePath4);
        Season[] seasons= Converter.fromJsonString(json);
        assertEquals(2,seasons.length);
    }

    private String loadJson(String samplePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(samplePath)));
    }

}
