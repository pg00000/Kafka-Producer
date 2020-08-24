/*package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	@Value(value = "${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapAddress;
	
	@Value(value = "${spring.kafka.consumer.fetch-min-size}")
	private String minFetchBytes;
	
	@Value(value = "${spring.kafka.consumer.fetch-max-size}")
	private String maxFetchBytes;
	
	@Value(value = "${spring.kafka.consumer.max-poll-records}")
	private String maxPollRecords;
	
//Maximum Fetch Bytes < 600KB else it will not fetch
//Maximum Poll Records = 10
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, minFetchBytes);
		props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, maxFetchBytes);		
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
		//props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		//props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
*/