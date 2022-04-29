package org.de.challenge.process.functions;

import org.apache.beam.sdk.transforms.SimpleFunction;
import org.de.challenge.domain.Stats;

public class FormatAsTextFn extends SimpleFunction<Stats,String> {
    String title;
    public FormatAsTextFn(String title){
        this.title=title;
    }
    @Override
    public String apply(Stats stats){
        return String.format("%s: %s",title,stats.getTeam());
    }
}
