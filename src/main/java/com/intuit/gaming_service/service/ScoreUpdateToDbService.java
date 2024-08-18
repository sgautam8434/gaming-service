package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;

public interface ScoreUpdateToDbService {

  void saveToDb(Scores newScore);

}
