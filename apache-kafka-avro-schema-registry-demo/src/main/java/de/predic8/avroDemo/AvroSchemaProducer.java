package de.predic8.avroDemo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;

public class AvroSchemaProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", "http://localhost:8081");

        System.out.println("Creating Message Producer");
        try (Producer<String, Article> producer = new KafkaProducer<>(props)){

            ProducerRecord<String, Article> record = new ProducerRecord<>("articles", "key",
                    new Article("Salami", 5, "Deutschland"));

            producer.send(record);

            System.out.println("Message sent.");
        }
    }
}
