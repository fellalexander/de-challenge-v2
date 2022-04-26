package process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter @Setter @ToString
public class Stats implements Comparable<Stats>{
    private String team;
    private int points;
    private int totalGoals;
    private int totalGoalsTaken;
    private int totalShots;
    private int totalShotsTarget;

    public Stats(String team){
        this.team=team;
    }
    public void addTotalGoals(int add){
        totalGoals+=add;
    }
    public void addTotalGoalsTaken(int add){
        totalGoalsTaken+=add;
    }

    public void addTotalShots(int add){ totalShots+=add; }
    public void addTotalShotsTarget(int add){
        totalShotsTarget+=add;
    }

    public double getShotsToShotsTargetRatio(){
        return ((double) totalShotsTarget)/totalShots;
    }

    @Override
    public int compareTo(@NotNull Stats o) {
        if(points<o.points){
            return 1;
        }else{
            if(points==o.points){
               return team.compareTo(o.team);
            }else{
                return -1;
            }
        }
    }
}
