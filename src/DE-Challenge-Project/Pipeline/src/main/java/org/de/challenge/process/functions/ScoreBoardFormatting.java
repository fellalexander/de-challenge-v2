package org.de.challenge.process.functions;

import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.de.challenge.domain.Stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoardFormatting extends SimpleFunction<KV<String,Iterable<Stats>>,String> {

    @Override
    public String apply(KV<String,Iterable<Stats>> groupedStats){
        StringBuilder scoreBoard = new StringBuilder();
        scoreBoard.append(String.format("%8s%50s%20s\n","Position","Team","Score"));
        List<Stats> results= new ArrayList<Stats>();
        groupedStats.getValue().forEach(results::add);
        Collections.sort(results);
        for(int i=0;i<results.size();i++){
            Stats stats= results.get(i);
            scoreBoard.append(String.format("%10d%50s%20d\n",i+1,stats.getTeam(),stats.getPoints()));
        }
        return scoreBoard.toString();
    }
}
