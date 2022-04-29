package org.de.challenge.process.functions.tests;

import org.apache.beam.sdk.values.KV;
import org.de.challenge.domain.Stats;
import org.de.challenge.process.functions.ScoreBoardFormatting;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ScoreBoardFormattingTest {

    @Test
    public void scoreBoardFormattingTest(){
        ScoreBoardFormatting scoreBoardFormatting = new ScoreBoardFormatting();
        Stats stats1= new Stats("team1");
        Stats stats2= new Stats("team2");
        Stats stats3= new Stats("team3");
        stats1.setPoints(32);
        stats2.setPoints(30);
        stats3.setPoints(30);

        String scoreboard=scoreBoardFormatting.apply(KV.of("stats", Arrays.asList(stats1,stats2,stats3)));
        String result="Position                                              Team               Score\n" +
                     "         1                                             team1                  32\n" +
                     "         2                                             team2                  30\n" +
                     "         3                                             team3                  30\n";
        assertEquals(result,scoreboard);

    }
}
