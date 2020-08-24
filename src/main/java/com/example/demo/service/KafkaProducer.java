package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.example.demo.vo.SlotForecastDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, SlotForecastDetails> kafkaTemplate;

	@Value(value = "${spring.kafka.producer.topic}")
	private String topic;


	//private KafkaTemplate<String, String> kafkaTemplate;
	
	/*public void sendMessage(String message) {
	    ListenableFuture<SendResult<String, String>> future = 
	      kafkaTemplate.send("test", message);
		
	    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
	 
	        @Override
	        public void onSuccess(SendResult<String, String> result) {
	            log.info("Sent message=[" + message + 
	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        
	        @Override
	        public void onFailure(Throwable ex) {
	            log.info("Unable to send message=[" 
	              + message + "] due to : " + ex.getMessage());
	        }
	    });
	}*/
	
	public void sendMSTMessage(SlotForecastDetails message) {
	    ListenableFuture<SendResult<String, SlotForecastDetails>> future =
	      kafkaTemplate.send(topic, message);
		
	    future.addCallback(new ListenableFutureCallback<SendResult<String, SlotForecastDetails>>() {
	 
	        @Override
	        public void onSuccess(SendResult<String, SlotForecastDetails> result) {
	            log.info("Sent message=[" + result.getProducerRecord().value() +
	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        
	        @Override
	        public void onFailure(Throwable ex) {
	            log.info("Unable to send message=[" 
	              + message + "] due to : " + ex.getMessage());
	        }
	    });
	}
}
