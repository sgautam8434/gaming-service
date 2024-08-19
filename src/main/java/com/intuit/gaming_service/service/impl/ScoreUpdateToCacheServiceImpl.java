package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.ScoreUpdateService;

import org.hibernate.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreUpdateToCacheServiceImpl implements ScoreUpdateService {

  @Autowired
  CacheService cacheService;

  @Override
  public void addScore(Scores newScore) {
    try {
      cacheService.addToCache(newScore);
    } catch (Exception e) {
      throw new CacheException("Error while updating cache");
    }
  }
}
