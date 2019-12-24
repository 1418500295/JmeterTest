package com.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class KafkaProduceMsg {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //指定kafka配置
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"47.244:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //创建生产者
        Producer producer = new KafkaProducer(properties);
        producer.send(new ProducerRecord("test","我是Daine"));
        producer.close();





    }


}
