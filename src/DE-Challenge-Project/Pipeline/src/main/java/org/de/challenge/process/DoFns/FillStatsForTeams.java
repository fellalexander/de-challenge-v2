package org.de.challenge.process.DoFns;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.de.challenge.domain.Season;
import org.de.challenge.domain.Stats;
import org.de.challenge.utils.StatsFiller;

import java.util.Objects;

public class FillStatsForTeams extends DoFn<KV<String,Iterable<Season>>, Stats> {

    @ProcessElement
    public void processElement (ProcessContext c){
        Objects.requireNonNull(c.element(),"Element of process context must not be null");
        String teamName= c.element().getKey();
        Objects.requireNonNull(c.element().getValue(),String.format("Team %s does not have games played",teamName));
        Stats stats = new Stats(teamName);

        for(Season season: c.element().getValue()){
            StatsFiller.fillStatsPerSeason(stats,season,teamName);
        }
        c.output(stats);
    }
}
