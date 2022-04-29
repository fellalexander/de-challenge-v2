# Requirements
This project is built using Maven, to download, install, configure and run Maven you can visit
[Maven's official site](https://maven.apache.org/)

# Deployment of Pipeline
Since this is an Apache Beam pipeline it can be run with different 
[runners](https://beam.apache.org/documentation/runners/capability-matrix/) according to the 
environment it will be executed. Take into consideration the Pipeline project is built using maven
profiles, so you can build it according to the desired runners.
Current available runners supported by this Pipeline are:
* Direct Runner
* Dataflow Runner
* Flink Runner
* Spark Runner
* Samza Runner
* Twister 2 Runner
* Nemo Runner
* Jet Runner

I will list two of the common execution runners you may choose to
run this pipeline: DirectRunner and Dataflow Runner. In case you choose to run with other runners
please visit [Apache runners' documentation](https://beam.apache.org/documentation/runners/capability-matrix/)
to see any additional parameters needed.



## Direct Runner
Apache Beam [Direct Runner](https://beam.apache.org/documentation/runners/direct/)  is the simplest 
runner, and it will run in you local machine. To generate the proper JAR file you will need to do the
following:
1. Using the console or terminal go to **DE-Challenge-Project** folder
2. Run the following maven command `mvn clean install -P direct-runner,portable-runner`
3. Go to the target folder `DE-Challenge-Project/Pipeline/target`
4. There you will find `Pipeline-bundled-0.1.jar`. Make sure to use the bundled jar as it contains all required libraries
5. Run the jar using the following command: 
```
 java -jar Pipeline-bundled-0.1.jar \
 --inputFile=<path-to-json-file.json> \
 --output=<path-to-output-folder> 
```
Here you need to replace `<path-to-json-file.json>` with the actual json files with the data and 
`<path-to-output-folder>` with the folder you want the results to be in.
Using the season 09-10 data for this challenge and outputting the data in the target folder of the jar
file, the above command would look like the following:
```
 java -jar Pipeline-bundled-0.1.jar \
 --inputFile=../../../../data/season-0910_json.json \
 --output=results 
```
6. Go to `results` folder to find the files with the results in them.

## Dataflow Runner
Google Cloud [Dataflow Runner](https://beam.apache.org/documentation/runners/dataflow/) will let you run this job
in Google's Cloud Dataflow service. Please view the 
[Cloud Dataflow Runner prerequisites and setup](https://beam.apache.org/documentation/runners/dataflow/) 
to make sure you will be able to run this job in Google cloud.

1. Using the console or terminal go to **DE-Challenge-Project** folder
2. Run the following maven command `mvn clean install -P dataflow-runner`
3. Go to the target folder `DE-Challenge-Project/Pipeline/target`
4. Copy the `Pipeline-bundled-0.1.jar` to your GCP project
5. Run the jar in cloudshell using the following command:
```
 java -jar Pipeline-bundled-0.1.jar \
 --inputFile=gs://<path-to-json-file.json> \
 --output=gs://<path-to-output-folder>
 --runner=DataflowRunner \
 --project=<your-gcp-project-id> \
 --region=<gcp-region> \
 --tempLocation=gs://<your-gcs-bucket>/temp/
```
6. You will find the results in the bucket folder you specified in the `--output` option