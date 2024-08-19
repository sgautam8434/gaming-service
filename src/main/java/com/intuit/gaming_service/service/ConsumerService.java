package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.KafkaException;

public interface ConsumerService {

  void consumeDataFromQueue(Scores newScore) throws KafkaException;
}
