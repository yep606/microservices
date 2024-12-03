docker run --rm confluentinc/cp-kafka:7.4.0 kafka-storage random-uuid - generate random UUID using docker
or previously downloaded sh script package

Random UUID: LMIkhGvjT_OlpfAbh2MNAg

./kafka-topics.sh --create --topic payment-created-events-topic --partitions 3 --replication-factor 3 --bootstrap-server localhost:9093,localhost:9092,localhost:9091

./kafka-topics.sh --list --bootstrap-server localhost:9093,localhost:9092,localhost:9091

./kafka-console-producer.sh --topic payment-created-events-topic --bootstrap-server localhost:9093,localhost:9092,localhost:9091

./kafka-console-consumer.sh --topic payment-created-events-topic --from-beginning --bootstrap-server localhost:9093,localhost:9092,localhost:9091
