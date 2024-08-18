package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.ScoreUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreUpdateToCacheServiceImpl implements ScoreUpdateService {

  @Autowired
  CacheService cacheService;

  @Override
  public void addScore(Scores newScore){
    cacheService.addToCache(newScore);
  }

}
