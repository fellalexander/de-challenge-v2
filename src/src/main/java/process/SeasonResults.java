package process;

import domain.converter.Converter;
import domain.model.Season;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class SeasonResults {
    public static final int WIN_POINTS=3;
    public static final int DRAW_POINTS=1;

    public static void main(String[] args) throws IOException {
        String dataPath="/home/alexander/Documents/Walmart-Challenge/de-challenge-v2/data/season-0910_json.json";
        String parseJson= new String(Files.readAllBytes(Paths.get(dataPath)));
        Season[] seasons = Converter.fromJsonString(parseJson);
        Hashtable<String,Stats> results= getStats(seasons);
        System.out.println(String.format("Most Scored Team is %s",getMostScoredTeam(results)));
        System.out.println(String.format("Most Scoring team is %s",getMostScoringTeam(results)));
        System.out.println(String.format("Team with best shots vs shots to target is %s",getBestShotsToShotsTargetRatio(results)));
        getPositionsTable(results);

    }

    public static Hashtable <String,Stats> getStats(Season[] seasons){
        Hashtable <String,Stats> teamsStats= new Hashtable<>();
        for(Season s:seasons){
            Stats homeStats=getStats(s.getHomeTeam(),teamsStats);
            Stats awayStats=getStats(s.getAwayTeam(),teamsStats);

            switch (s.getFtr()){
                case "A":
                    awayStats.setPoints(awayStats.getPoints()+WIN_POINTS);
                    break;
                case "D":
                    homeStats.setPoints(homeStats.getPoints()+DRAW_POINTS);
                    awayStats.setPoints(awayStats.getPoints()+DRAW_POINTS);
                    break;
                case "H":
                    homeStats.setPoints(homeStats.getPoints()+ WIN_POINTS);
                    break;
                default:
                    throw new RuntimeException(String.format("FTR values should be A, D or H. Value %s found not valid",
                            s.getFtr()));
            }
            int homeGoals=s.getFthg();
            int awayGoals=s.getFtag();
            homeStats.addTotalGoals(homeGoals);
            homeStats.addTotalGoalsTaken(awayGoals);
            awayStats.addTotalGoals(awayGoals);
            awayStats.addTotalGoalsTaken(homeGoals);
            homeStats.addTotalShots(s.getHs());
            homeStats.addTotalShotsTarget(s.getHst());
            awayStats.addTotalShots(s.getAs());
            awayStats.addTotalShotsTarget(s.getAst());

        }
        return teamsStats;
    }

    private static Stats getStats(String team, @NotNull Hashtable<String,Stats> posiciones){
        Stats stats = posiciones.get(team);
        if(stats==null){
            stats= new Stats(team);
            posiciones.put(team,stats);
        }
        return stats;
    }

    public static String getMostScoredTeam(Hashtable<String,Stats>stats){
        String mostScoredTeam="";
        int mostGoalsTaken=-1;
        for(String team:stats.keySet()){
            int goalsTaken=stats.get(team).getTotalGoalsTaken();
            if(goalsTaken>mostGoalsTaken){
                mostGoalsTaken=goalsTaken;
                mostScoredTeam=team;
            }
        }
        return mostScoredTeam;
    }

    public static String getMostScoringTeam(Hashtable<String,Stats>stats){
        String mostScoringTeam="";
        int mostGoalsMade=-1;
        for(String team:stats.keySet()){
            int goalsMade=stats.get(team).getTotalGoals();
            if(goalsMade>mostGoalsMade){
                mostGoalsMade=goalsMade;
                mostScoringTeam=team;
            }
        }
        return mostScoringTeam;
    }

    public static String getBestShotsToShotsTargetRatio(Hashtable<String,Stats>stats){
        String bestRatioTeam="";
        double bestRatio=-1;
        for(String team:stats.keySet()){
            double ratio=stats.get(team).getShotsToShotsTargetRatio();
            if(ratio>bestRatio){
                bestRatio=ratio;
                bestRatioTeam=team;
            }
        }
        return bestRatioTeam;
    }

    public static void getPositionsTable(Hashtable<String,Stats>stats){
        List<Stats> teams = new ArrayList<Stats>(stats.values());
        Collections.sort(teams);
        for(int i =0;i<teams.size();i++){
            System.out.println(String.format("%d°  %s  Score:%d",i+1,teams.get(i).getTeam(),teams.get(i).getPoints()));
        }
    }





}
