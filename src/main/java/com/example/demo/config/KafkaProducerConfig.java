package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.example.demo.serializers.SlotForecastSerializer;
import com.example.demo.vo.SlotForecastDetails;

@Configuration
@ConditionalOnClass(KafkaTemplate.class)
public class KafkaProducerConfig {
    
    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapAddress;

 
    @Bean
    public ProducerFactory<String, SlotForecastDetails> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        /*configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);*/
        
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SlotForecastSerializer.class.getName());

        return new DefaultKafkaProducerFactory<>(configProps);
    }
 
    @Bean
    @ConditionalOnMissingBean(KafkaTemplate.class)
    public KafkaTemplate<String, SlotForecastDetails> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    
}