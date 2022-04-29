package org.de.challenge.domain;

import org.de.challenge.domain.Stats;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatsTests {
    @Test
    public void checkShotToShotsTargetTest(){
        Stats stats = new Stats("team");
        stats.setTotalShotsTarget(10);
        stats.setTotalShots(20);

        assertEquals(0.5d,stats.getShotsToShotsTargetRatio(),0);
    }
}
