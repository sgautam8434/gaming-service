package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.constants.KafkaConstants;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.ProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

  @Autowired
  private KafkaTemplate<String, Scores> kafkaTemplate;

  @Override
  public void createNewScore(Scores newScore){
    kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, newScore);
  }
}
