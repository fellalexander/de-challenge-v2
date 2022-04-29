package org.de.challenge.process.functions.tests;

import org.apache.beam.sdk.values.KV;
import org.de.challenge.domain.Stats;
import org.de.challenge.process.functions.ScoreBoardKeying;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreBoardKeyingTest {
    @Test
    public void ScoreBoardKeyingTests(){
        ScoreBoardKeying scoreBoardKeying = new ScoreBoardKeying();
        Stats stats = new Stats("team");

        KV<String,Stats> result=scoreBoardKeying.apply(stats);
        assertEquals(KV.of("Sorting",stats),result);
    }
}
