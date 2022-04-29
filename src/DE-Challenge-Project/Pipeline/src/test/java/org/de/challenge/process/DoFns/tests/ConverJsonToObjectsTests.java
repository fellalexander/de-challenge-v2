package org.de.challenge.process.DoFns.tests;

import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.de.challenge.converter.Converter;
import org.de.challenge.domain.Season;
import org.de.challenge.process.DoFns.ConvertJsonToObjects;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConverJsonToObjectsTests {
    @Rule
    public TestPipeline p = TestPipeline.create();
    public String samplePath="src/test/resources/data-sample.json";

    @Test
    public void convertJsonToObjectTest() throws IOException {
        Season season = Converter.fromJsonString(getJson())[0];

        PCollection<KV<String,Season>> output= p.apply(Create.of(getJson()))
                .apply(ParDo.of(new ConvertJsonToObjects()));

        PAssert.that(output).containsInAnyOrder(KV.of("Wigan",season),KV.of("Aston Villa",season));
        p.run().waitUntilFinish();
    }
    private String getJson() throws IOException {
        return new String(Files.readAllBytes(Paths.get(samplePath)));
    }
}
