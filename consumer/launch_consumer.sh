#!/bin/bash

spark-shell --jars /opt/consumer/jar/spark-streaming-kafka-0-10_2.11-2.3.1.jar,/opt/consumer/jar/spark-sql-kafka-0-10_2.11-2.3.1.jar -i /opt/consumer/consumer.scala 
#spark-shell --packages org.apache.spark:spark-streaming-kafka-0-10_2.11:2.3.1,org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.1 -i /opt/consumer/consumer.scala 
