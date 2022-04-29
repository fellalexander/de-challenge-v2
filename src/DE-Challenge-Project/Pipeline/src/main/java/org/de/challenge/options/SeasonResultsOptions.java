package org.de.challenge.options;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation.Required;


public interface SeasonResultsOptions extends PipelineOptions {

    @Description("Path to the seasons data in json format")
    @Required
    public String getInputFile();

    public void setInputFile(String inputFile);

    @Description("Path to the file to write to")
    @Required
    public String getOutput();

    public void setOutput(String output);
}
