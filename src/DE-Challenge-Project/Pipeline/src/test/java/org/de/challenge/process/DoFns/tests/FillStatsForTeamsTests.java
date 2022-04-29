package org.de.challenge.process.DoFns.tests;

import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.de.challenge.domain.Season;
import org.apache.beam.sdk.values.KV;
import org.de.challenge.domain.Stats;
import org.de.challenge.process.DoFns.FillStatsForTeams;
import org.de.challenge.utils.StatsFiller;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class FillStatsForTeamsTests {

    @Rule public TestPipeline p = TestPipeline.create();

    @Test
    public void FillStatsTest(){
        String team="team1";
        Season season1 = new Season();
        season1.setFtr(StatsFiller.AWAY_WINS);
        season1.setAwayTeam("team1");
        season1.setHomeTeam("hteam");
        season1.setFthg(2);
        season1.setFtag(4);
        season1.setAs(6);
        season1.setAst(5);

        Season season2 = new Season();
        season2.setFtr(StatsFiller.DRAW);
        season2.setAwayTeam("team1");
        season2.setHomeTeam("hteam");
        season2.setFthg(2);
        season2.setFtag(4);
        season2.setAs(6);
        season2.setAst(5);

        List<KV<String,Iterable<Season>>> teamStats=Arrays.asList(KV.of(team,Arrays.asList(season1,season2)));

        PCollection<Stats> output=p.apply(Create.of(teamStats))
                .apply(ParDo.of(new FillStatsForTeams()));
        Stats stats1= new Stats(team);
        stats1.setPoints(4);
        stats1.setTotalGoals(8);
        stats1.setTotalGoalsTaken(4);
        stats1.setTotalShots(12);
        stats1.setTotalShotsTarget(10);

        PAssert.that(output).containsInAnyOrder(stats1);
        p.run().waitUntilFinish();


    }
}
