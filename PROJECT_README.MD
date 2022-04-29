# CONSIDERATIONS
I know this problem is not specifically meant for a big data ingestion, but I've created a pipeline used for this as the
challenge specifies that it must be treated as an ETL.

It still outputs the correct results, according to tests I created :). I've done it this way because this is the 
technology I'm most familiarized with capable of creating an ingestion pipeline.

I've also only included the Apache Beam jar, but in theory this could be added to other orchestration frameworks
like Airflow, Apache NiFi or other cloud-based ones like Composer (which is actually managed Airflow) or AWS Glue. This
would allow to set the job's trigger, cadence and probably final destination (e.g a table of some sort).

The final deployment in this scenario could be done when merging changes to a master branch, where a jenkins pipeline
could pick it up and deploy it using Terraform for example.