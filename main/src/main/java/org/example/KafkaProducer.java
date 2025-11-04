package org.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;
import java.util.concurrent.*;

public class KafkaProducer {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "test-topic";

    private static final List<String> FRUITS = Arrays.asList(
            "Apple", "Banana", "Orange", "Grape", "Strawberry",
            "Pineapple", "Mango", "Watermelon", "Kiwi", "Peach"
    );

    public static void main(String[] args) {
        // Настройки Producer
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Дополнительные настройки для надежности
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

        try (Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties)) {
            Random random = new Random();

            for (int i = 0; i < 20; i++) {
                String key = "fruit-" + (i + 1);
                String fruit = FRUITS.get(random.nextInt(FRUITS.size()));
                String value = String.format("{\"id\": %d, \"fruit\": \"%s\", \"price\": %.2f}", i + 1, fruit, random.nextDouble() * 10);

                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, value);

                producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.printf("Отправлено сообщение: key=%s, value=%s, partition=%d, offset=%d%n",
                                key, value, metadata.partition(), metadata.offset());
                    } else {
                        System.out.println("Ошибка при отправке: " + exception.getMessage());
                    }
                });

                // Задержка между сообщениями
                Thread.sleep(1000);
            }

            producer.flush();
            System.out.println("Все сообщения отправлены!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
