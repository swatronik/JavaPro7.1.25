docker exec -it kafka /bin/bash

kafka-topics --create --topic test-topic \
  --bootstrap-server localhost:9092 \
  --partitions 1 \
  --replication-factor 1

kafka-topics --list --bootstrap-server localhost:9092

kafka-console-consumer \
  --topic test-topic \
  --from-beginning \
  --bootstrap-server localhost:9092

exit