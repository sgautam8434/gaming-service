package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbUpdateException;

public interface ScoreUpdateService {

  void addScore(Scores newScore) throws DbUpdateException;

}
