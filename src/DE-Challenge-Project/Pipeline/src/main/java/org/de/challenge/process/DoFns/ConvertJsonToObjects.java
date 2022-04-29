package org.de.challenge.process.DoFns;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.de.challenge.converter.Converter;
import org.de.challenge.domain.Season;

import java.io.IOException;

public class ConvertJsonToObjects extends DoFn<String, KV<String, Season>> {

    @ProcessElement
    public void processElement(@Element String element, OutputReceiver<KV<String,Season>> receiver) {
        try {
            Season[] seasons = Converter.fromJsonString(element);
            for(Season season:seasons){
                receiver.output(KV.of(season.getHomeTeam(),season));
                receiver.output(KV.of(season.getAwayTeam(),season));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
