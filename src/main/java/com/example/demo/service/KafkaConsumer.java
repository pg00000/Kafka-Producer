/*package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "test", groupId = "group_id")
	public void listen(String message) {
	    System.out.println("Received Messasge in group foo: " + message);
	}

}
*/