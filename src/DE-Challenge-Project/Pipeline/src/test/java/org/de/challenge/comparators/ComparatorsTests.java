package org.de.challenge.comparators;

import org.de.challenge.comparators.MostScoredComparator;
import org.de.challenge.comparators.MostScoringComparator;
import org.de.challenge.comparators.ShotsToShotsTargetComparator;
import org.de.challenge.domain.Stats;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ComparatorsTests {

    @Test
    public void mostScoredComparator(){
        MostScoredComparator msc = new MostScoredComparator();
        Stats stats1 = new Stats("team1");
        Stats stats2 = new Stats("team2");
        stats1.setTotalGoalsTaken(5);
        stats2.setTotalGoalsTaken(5);
        assertEquals(0,msc.compare(stats1,stats2));

        stats1.setTotalGoalsTaken(6);
        assertEquals(1,msc.compare(stats1,stats2));

        stats2.setTotalGoalsTaken(7);
        assertEquals(-1,msc.compare(stats1,stats2));

    }

    @Test
    public void mostScoringComparator(){
        MostScoringComparator msc = new MostScoringComparator();
        Stats stats1 = new Stats("team1");
        Stats stats2 = new Stats("team2");
        stats1.setTotalGoals(5);
        stats2.setTotalGoals(5);
        assertEquals(0,msc.compare(stats1,stats2));

        stats1.setTotalGoals(6);
        assertEquals(1,msc.compare(stats1,stats2));

        stats2.setTotalGoals(7);
        assertEquals(-1,msc.compare(stats1,stats2));
    }

    @Test
    public void ShotsToShotsTargetComparator(){
        ShotsToShotsTargetComparator ststc=new ShotsToShotsTargetComparator();
        Stats stats1 = new Stats("team1");
        Stats stats2 = new Stats("team2");
        stats1.setTotalShots(20);
        stats1.setTotalShotsTarget(10);
        stats2.setTotalShots(20);
        stats2.setTotalShotsTarget(10);
        assertEquals(0,ststc.compare(stats1,stats2));

        stats1.setTotalShotsTarget(15);
        assertEquals(1,ststc.compare(stats1,stats2));

        stats1.setTotalShotsTarget(5);
        assertEquals(-1,ststc.compare(stats1,stats2));
    }
}
