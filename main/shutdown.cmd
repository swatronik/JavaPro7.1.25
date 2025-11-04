docker-compose down

sudo rm -rf kafka-data
sudo rm -rf zookeeper-data

docker volume ls | grep kafka | awk '{print $2}' | xargs docker volume rm