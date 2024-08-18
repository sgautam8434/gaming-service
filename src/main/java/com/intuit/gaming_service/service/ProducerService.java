package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;

public interface ProducerService {
  void createNewScore(Scores newScore);
}
