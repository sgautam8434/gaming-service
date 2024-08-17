package com.intuit.gaming_service.config;

import com.intuit.gaming_service.constants.KafkaConstants;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

  @Bean
  public NewTopic topic() {
    return new NewTopic(KafkaConstants.KAFKA_TOPIC, 1, (short) 1);
  }
}
