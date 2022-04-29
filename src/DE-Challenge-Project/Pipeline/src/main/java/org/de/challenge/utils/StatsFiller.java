package org.de.challenge.utils;

import org.de.challenge.domain.Season;
import org.de.challenge.domain.Stats;

public class StatsFiller {
    public static final int WIN_POINTS=3;
    public static final int DRAW_POINTS=1;
    public static final String HOME_WINS="H";
    public static final String DRAW="D";
    public static final String AWAY_WINS="A";

    public static void fillStatsPerSeason(Stats stats, Season s, String teamName){
        if (s.getFtr().equals(DRAW)) {
            stats.addPoints(DRAW_POINTS);
        }

        if(s.getHomeTeam().equals(teamName)) {
            if(s.getFtr().equals(HOME_WINS)){
                stats.addPoints(WIN_POINTS);
            }
            stats.addTotalGoals(s.getFthg());
            stats.addTotalGoalsTaken(s.getFtag());
            stats.addTotalShots(s.getHs());
            stats.addTotalShotsTarget(s.getHst());
        }else{
            if(s.getAwayTeam().equals(teamName)){
                if(s.getFtr().equals(AWAY_WINS)){
                    stats.addPoints(WIN_POINTS);
                }
                stats.addTotalGoals(s.getFtag());
                stats.addTotalGoalsTaken(s.getFthg());
                stats.addTotalShots(s.getAs());
                stats.addTotalShotsTarget(s.getAst());
            }else{
                throw new RuntimeException(String.format("Team %s hasn't played this match %s",teamName,s.toString()));
            }
        }
    }
}
