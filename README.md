# microserv

Project using docker-compose to manage a producer written in akka, a kafka server and a spark consumer with a single worker node.

To launch it it's only needed to download the full structure and execute 

> ./launch_docker.sh

Once the message

> ................. Services are up .................

Appears, connect into the consumer machine with the command 

> docker exec -it consumer /bin/bash. 

To start to consume service execute the script below, the output will be printed on your console:

> /opt/consumer/launch_consumer.sh

#### For more details about the Dockerfile on the consumer and producer you can look at: 

- [Scala Container](https://github.com/danspaes/scala-slim/blob/master/Dockerfile)
- [Spark Container](https://github.com/danspaes/spark-slim/blob/master/Dockerfile)
