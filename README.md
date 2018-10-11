#kinduct
Project using docker-compose to manage a producer written in akka, a kafka server and a spark consumer with a single worker node.

To launch it it's only needed to download the full structure and execute ./launch_docker.sh

Once the message

................. Services are up .................

Appears, connect into the consumer machine with the command docker exec -it consumer /bin/bash. And then execute /opt/consumer/launch_consumer.sh to be able to see on the console the output.


Elastic search image and stand-alone run 
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.4.1
