docker exec -it kafka-console-producer \
  --topic test-topic \
  --bootstrap-server localhost:9092

docker exec -it kafka-console-consumer \
  --topic test-topic \
  --from-beginning \
  --bootstrap-server localhost:9092