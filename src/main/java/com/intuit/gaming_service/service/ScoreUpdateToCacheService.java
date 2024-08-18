package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;

public interface ScoreUpdateToCacheService {

  void addNewScoreToCache(Scores newScore);

}
