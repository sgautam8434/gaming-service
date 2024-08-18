package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.constants.KafkaConstants;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.KafkaService;
import com.intuit.gaming_service.service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaServiceImpl implements KafkaService {

  @Autowired
  private ScoreService scoreService;

  @Override
  @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.KAFKA_GROUP_ID)
  public void consumeDataFromQueue(Scores newScore){
    scoreService.addNewScore(newScore);
  }

}
