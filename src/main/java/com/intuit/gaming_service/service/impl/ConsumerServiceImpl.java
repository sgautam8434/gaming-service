package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.constants.KafkaConstants;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.KafkaException;
import com.intuit.gaming_service.service.ConsumerService;
import com.intuit.gaming_service.service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

  @Autowired
  private ScoreService scoreService;

  @Override
  @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.KAFKA_GROUP_ID, properties = {
      "spring.json.value.default.type=com.intuit.gaming_service.entity.Scores"})
  public void consumeDataFromQueue(Scores newScore) throws KafkaException {
    try {
      scoreService.addNewScore(newScore);
    } catch (Exception ex) {
      throw new KafkaException("Error getting data from Kafka");
    }
  }
}
