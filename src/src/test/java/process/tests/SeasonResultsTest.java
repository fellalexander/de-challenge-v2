package process.tests;

import domain.converter.Converter;
import domain.model.Season;
import org.junit.Test;
import process.SeasonResults;
import process.Stats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

public class SeasonResultsTest {
    private String samplePath="src/test/resources/data-sample.json";
    @Test
    public void resultsTest() throws IOException {
        String json= loadJson();
        Season[] seasons= Converter.fromJsonString(json);
        Hashtable<String, Stats> results=SeasonResults.getStats(seasons);
        Stats wiganStats=results.get("Wigan");
        assertEquals(3,wiganStats.getPoints());
        assertEquals(2,wiganStats.getTotalGoals());
        assertEquals(0,wiganStats.getTotalGoalsTaken());
        assertEquals(14,wiganStats.getTotalShots());
        assertEquals(7,wiganStats.getTotalShotsTarget());
    }

    private String loadJson() throws IOException {
        return new String(Files.readAllBytes(Paths.get(samplePath)));
    }
}
