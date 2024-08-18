package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;

import java.util.List;

public interface CacheService {

  List<Scores> getTopNPlayers();

  void initialiseCache(Integer topN, List<Scores> allPlayerScores);

  void addToCache(Scores newScore);
}
