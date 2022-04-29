package org.de.challenge.utils;

import org.de.challenge.domain.Season;
import org.de.challenge.domain.Stats;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatsFillerTests {

    @Test
    public void testFillStatsHomeWin(){
        String team="team1";
        Stats stats= new Stats(team);
        Season season = new Season();
        season.setFtr(StatsFiller.HOME_WINS);
        season.setHomeTeam("team1");
        season.setFthg(4);
        season.setFtag(2);
        season.setHs(6);
        season.setHst(5);

        StatsFiller.fillStatsPerSeason(stats,season,team);

        assertEquals(team,stats.getTeam());
        assertEquals(StatsFiller.WIN_POINTS,stats.getPoints());
        assertEquals(4,stats.getTotalGoals());
        assertEquals(2,stats.getTotalGoalsTaken());
        assertEquals(6,stats.getTotalShots());
        assertEquals(5,stats.getTotalShotsTarget());
    }

    @Test
    public void testFillStatsHomeLose(){
        String team="team1";
        Stats stats= new Stats(team);
        Season season = new Season();
        season.setFtr(StatsFiller.AWAY_WINS);
        season.setHomeTeam("team1");
        season.setFthg(4);
        season.setFtag(2);
        season.setHs(6);
        season.setHst(5);

        StatsFiller.fillStatsPerSeason(stats,season,team);

        assertEquals(team,stats.getTeam());
        assertEquals(0,stats.getPoints());
        assertEquals(4,stats.getTotalGoals());
        assertEquals(2,stats.getTotalGoalsTaken());
        assertEquals(6,stats.getTotalShots());
        assertEquals(5,stats.getTotalShotsTarget());
    }

    @Test
    public void testFillStatsHomeDraw(){
        String team="team1";
        Stats stats= new Stats(team);
        Season season = new Season();
        season.setFtr(StatsFiller.DRAW);
        season.setHomeTeam("team1");
        season.setFthg(4);
        season.setFtag(2);
        season.setHs(6);
        season.setHst(5);

        StatsFiller.fillStatsPerSeason(stats,season,team);

        assertEquals(team,stats.getTeam());
        assertEquals(StatsFiller.DRAW_POINTS,stats.getPoints());
        assertEquals(4,stats.getTotalGoals());
        assertEquals(2,stats.getTotalGoalsTaken());
        assertEquals(6,stats.getTotalShots());
        assertEquals(5,stats.getTotalShotsTarget());
    }

    @Test
    public void testFillStatsAwayWin(){
        String team="team1";
        Stats stats= new Stats(team);
        Season season = new Season();
        season.setFtr(StatsFiller.AWAY_WINS);
        season.setAwayTeam("team1");
        season.setHomeTeam("hteam");
        season.setFthg(2);
        season.setFtag(4);
        season.setAs(6);
        season.setAst(5);

        StatsFiller.fillStatsPerSeason(stats,season,team);

        assertEquals(team,stats.getTeam());
        assertEquals(StatsFiller.WIN_POINTS,stats.getPoints());
        assertEquals(4,stats.getTotalGoals());
        assertEquals(2,stats.getTotalGoalsTaken());
        assertEquals(6,stats.getTotalShots());
        assertEquals(5,stats.getTotalShotsTarget());
    }

    @Test
    public void testFillStatsAwayLose(){
        String team="team1";
        Stats stats= new Stats(team);
        Season season = new Season();
        season.setFtr(StatsFiller.HOME_WINS);
        season.setAwayTeam("team1");
        season.setHomeTeam("hteam");
        season.setFthg(2);
        season.setFtag(4);
        season.setAs(6);
        season.setAst(5);

        StatsFiller.fillStatsPerSeason(stats,season,team);

        assertEquals(team,stats.getTeam());
        assertEquals(0,stats.getPoints());
        assertEquals(4,stats.getTotalGoals());
        assertEquals(2,stats.getTotalGoalsTaken());
        assertEquals(6,stats.getTotalShots());
        assertEquals(5,stats.getTotalShotsTarget());
    }

    @Test
    public void testFillStatsAwayDraw(){
        String team="team1";
        Stats stats= new Stats(team);
        Season season = new Season();
        season.setFtr(StatsFiller.DRAW);
        season.setAwayTeam("team1");
        season.setHomeTeam("hteam");
        season.setFthg(2);
        season.setFtag(4);
        season.setAs(6);
        season.setAst(5);

        StatsFiller.fillStatsPerSeason(stats,season,team);

        assertEquals(team,stats.getTeam());
        assertEquals(StatsFiller.DRAW_POINTS,stats.getPoints());
        assertEquals(4,stats.getTotalGoals());
        assertEquals(2,stats.getTotalGoalsTaken());
        assertEquals(6,stats.getTotalShots());
        assertEquals(5,stats.getTotalShotsTarget());
    }
}
