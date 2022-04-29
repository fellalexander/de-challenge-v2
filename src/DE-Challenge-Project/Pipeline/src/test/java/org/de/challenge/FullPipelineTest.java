package org.de.challenge;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.de.challenge.options.SeasonResultsOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.Assert.assertTrue;

/**
 * Make sure you have file-write permissions to run this test, otherwise it might  fail as it creates temporary files
 * to test that the pipeline is writing the desired output in the different files
 */
public class FullPipelineTest {
    public String inputPath="src/test/resources/data-sample.json";
    public String outputPath="src/test/resources/output";

    @Before @After
    public void checkAndCleanOutputPath() throws IOException {
        Path outputFolder = Paths.get(outputPath);
        if(Files.exists(outputFolder)) {
            Files.walk(outputFolder)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    public void fullPipelineTest(){
        SeasonResultsOptions options = PipelineOptionsFactory.
                fromArgs(String.format("--inputFile=%s",inputPath),String.format("--output=%s",outputPath))
                .withValidation()
                .as(SeasonResultsOptions.class);

        Pipeline p = Pipeline.create(options);
        SeasonResults.runSeasonResultsPipeline(p,options);
        assertTrue(Files.exists(Paths.get(outputPath)));
        assertTrue(Files.exists(Paths.get(String.format("%s%s%s",outputPath,File.separator,
                "best-shots-to-shots-target-ratio-00000-of-00001"))));
        assertTrue(Files.exists(Paths.get(String.format("%s%s%s",outputPath,File.separator,
                "most-scored-00000-of-00001"))));
        assertTrue(Files.exists(Paths.get(String.format("%s%s%s",outputPath,File.separator,
                "most-scoring-00000-of-00001"))));
        assertTrue(Files.exists(Paths.get(String.format("%s%s%s",outputPath,File.separator,
                "scoreboard-00000-of-00001"))));

    }
}
