package domain.model.tests;

import domain.converter.Converter;
import domain.model.Season;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConverterTest {
private String samplePath="src/test/resources/data-sample.json";
    @Test
    public void ConversionTest() throws IOException {
        String json= loadJson();
        Season[] seasons=Converter.fromJsonString(json);
        assertEquals(2,seasons.length);
    }

    private String loadJson() throws IOException {
        return new String(Files.readAllBytes(Paths.get(samplePath)));
    }

}
