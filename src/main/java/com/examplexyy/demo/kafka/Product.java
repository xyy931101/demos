package com.examplexyy.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * @author: xiongyayun
 * @date: 2022/1/8 15:04
 */
public class Product {

    public static void main(String[] args) {
        Properties config = new Properties();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(config);

    }
}
