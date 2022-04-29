package org.de.challenge.process.functions;

import com.google.common.collect.Lists;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.de.challenge.domain.Stats;

import java.util.Arrays;

public class ScoreBoardKeying extends SimpleFunction<Stats,KV<String, Stats>> {
    @Override
    public KV<String,Stats> apply(Stats stats){
        return KV.of("Sorting", stats);
    }
}
