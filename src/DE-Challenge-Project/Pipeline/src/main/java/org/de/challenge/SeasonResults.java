package org.de.challenge;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;

import org.de.challenge.comparators.MostScoredComparator;
import org.de.challenge.comparators.MostScoringComparator;
import org.de.challenge.comparators.ShotsToShotsTargetComparator;
import org.de.challenge.domain.Season;
import org.de.challenge.domain.Stats;
import org.de.challenge.options.SeasonResultsOptions;
import org.de.challenge.process.DoFns.ConvertJsonToObjects;
import org.de.challenge.process.DoFns.FillStatsForTeams;
import org.de.challenge.process.functions.FormatAsTextFn;
import org.de.challenge.process.functions.ScoreBoardFormatting;
import org.de.challenge.process.functions.ScoreBoardKeying;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SeasonResults {
    private static final Logger logger = LoggerFactory.getLogger(SeasonResults.class);

    /**
     * Main method that runs the logic of calculating the desired statistics for each team in the EPL given a JSON file
     * @param args These are the options taken by the program. There are two of them explicitly specified in
     * {@link SeasonResultsOptions}:
     * The output path where the 3 different files will be placed
     * <pre>{@code
     *      --output=[YOUR_LOCAL_FILE | YOUR_OUTPUT_PREFIX]
     * }</pre>
     *
     * The place where the specific file for a season is located
     *  <pre>{@code
     *      --inputFile=[JSON_FILE_LOCATION]
     *  }
     *  </pre>
     *
     *  Not specified in {@link SeasonResultsOptions}, but since this class extends
     *  {@link org.apache.beam.sdk.options.PipelineOptions} the runner can be explicitly defined using the following
     * <pre>{@code
     *      --runner=YOUR_SELECTED_RUNNER
     * }</pre>
     * By default the runner is DirectRunner which can be run in any computer. If run with Direct runner, this option
     * can be omitted.
     */
    public static void main(String[] args) {
        SeasonResultsOptions options = PipelineOptionsFactory.fromArgs(args).withValidation()
                .as(SeasonResultsOptions.class);

        Pipeline p = Pipeline.create(options);
        runSeasonResultsPipeline(p,options);
    }

    /**
     * This method runs the pipeline to process the EPL information for a given season. It is structured so that:
     * <p>
     * 1) Reads the Json data and fills a class called {@link Stats} where the information required to calculate
     * the scoreboard, the total goals each team scores, the total goals the team got scored, and the ratio between
     * shots taken vs shots that reached the target. Then with the possibility of parallelism runs the following:
     * 2) Gets the highest scored team
     * 3) Gets the highest scoring team
     * 4) Gets the best ratio between shots taken vs shots that reached the target.
     * <p>
     * Steps 2,3,4 can be run in parallel and will depend on the Runner used
     * @param pipeline The pipeline to be run in this program
     * @param options The options used to create the pipeline in this case it's a custom type of class
     * {@link SeasonResultsOptions} to provide better control over the options exposed to users.
     */
    public static void runSeasonResultsPipeline(Pipeline pipeline, SeasonResultsOptions options){
        logger.info(String.format("Running pipeline with following options:\n" +
                                   "Runner:%s\n" +
                                   "Input Path:%s\n" +
                                   "Output Path:%s"
                ,options.getRunner(),options.getInputFile(),options.getOutput()));

        PCollection<Stats> statsPerTeam=pipeline
                .apply("ReadJsonData",TextIO.read().from(options.getInputFile()))
                .apply("ConvertToSeasons",ParDo.of(new ConvertJsonToObjects()))
                .apply("GroupingByTeamName",GroupByKey.<String,Season>create())
                .apply("FillingStatistics",ParDo.of(new FillStatsForTeams()));

        statsPerTeam.apply("FindMaxValueForMostScoredTeam",Max.globally(new MostScoredComparator()))
                .apply("FormattingOutputMostScored",MapElements.via(new FormatAsTextFn("Most scored team")))
                .apply("WriteMostScored",TextIO.write().to(String.format("%s%s%s",options.getOutput(),
                        File.separator,"most-scored")));

        statsPerTeam.apply("FindMaxValueForMostScoringTeam",Max.globally(new MostScoringComparator()))
                .apply("FormattingOutputMostScoring",MapElements.via(new FormatAsTextFn("Most scoring team")))
                .apply("Write Most Scoring",TextIO.write().to(String.format("%s%s%s",options.getOutput(),
                        File.separator,"most-scoring")));

        statsPerTeam.apply("FindMaxValueForStoSTRatio",Max.globally(new ShotsToShotsTargetComparator()))
                .apply("FormattingOutputRatio",MapElements.via(new FormatAsTextFn("Best shots to shots target ratio")))
                .apply("Write Best shots to shots target ratio",TextIO.write().to(String.format("%s%s%s",options.getOutput(),
                        File.separator,"best-shots-to-shots-target-ratio")));

        statsPerTeam.apply("KeyingToGroupAllStats",MapElements.via(new ScoreBoardKeying()))
                .apply("GroupingAllStats",GroupByKey.create())
                .apply("ApplyingScoreBoardFormating",MapElements.via(new ScoreBoardFormatting()))
                .apply("WriteScoreboard",TextIO.write().to(String.format("%s%s%s",options.getOutput(),
                        File.separator,"scoreboard")));

        pipeline.run().waitUntilFinish();
    }
}
