package de.predic8.avroDemo;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;

public class AvroSchemaConsumer {


    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put("group.id", "article-consumer");
        // always read from first
        props.put("auto.commit.enable", "false");
        props.put("auto.offset.reset", "earliest");

        // avro
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put("schema.registry.url", "http://localhost:8081");
        props.setProperty("specific.avro.reader", "true");

        try (KafkaConsumer<String, Article> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singleton("articles"));

            while (true) {
                System.out.println("Polling messages.");
                ConsumerRecords<String, Article> records = consumer.poll(Duration.ofSeconds(1));

                records.forEach(record -> System.out.println(record.value().toString()));

                consumer.commitSync();
            }
        }
    }
}
