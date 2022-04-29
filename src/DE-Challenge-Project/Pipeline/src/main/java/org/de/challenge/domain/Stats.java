package org.de.challenge.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter @Setter @ToString @EqualsAndHashCode
public class Stats implements Comparable<Stats>, Serializable {
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

    public void addPoints(int add){points+=add;}

    @Override
    public int compareTo(@NotNull Stats o) {
        return o.points-points;
    }
}
