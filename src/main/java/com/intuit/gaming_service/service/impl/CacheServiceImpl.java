package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.CacheService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.hibernate.cache.CacheException;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

  private static CacheServiceImpl cache = null;

  public PriorityQueue<Scores> minHeap;
  private int topN;

  private Map<String, Scores> playerScores;

  private CacheServiceImpl() {
  }

  public static CacheServiceImpl getInstance() {
    if (cache == null) {
      synchronized (CacheServiceImpl.class) {
        if (cache == null) {
          cache = new CacheServiceImpl();
        }
      }
    }
    return cache;
  }

  public Map<String, Scores> playerScore;
  @Override
  public void initialiseCache(Integer topN, List<Scores> allPlayerScores) {
    if (topN <= 0) {
      throw new IllegalArgumentException("Number of players cannot be less than or equal to 0");
    }
    try {
      this.topN = topN;
      minHeap = new PriorityQueue<>();
      playerScore = new HashMap<>();
      if (allPlayerScores != null) {
        for (Scores score : allPlayerScores) {
          updateMinHeap(score);
        }
      }
    } catch (Exception ex) {
      throw new CacheException("Error initialing cache");
    }
  }

  private void updateMinHeap(Scores score) {
    if (minHeap.size() < topN) {
      minHeap.add(score);
      playerScore.put(score.getPlayerId(), score);
    } else {
      if (score.getScore() > minHeap.peek().getScore()) {
        Scores removedScore = minHeap.poll();
        minHeap.add(score);
        playerScore.remove(removedScore.getPlayerId());
        playerScore.put(score.getPlayerId(), score);
      }
    }
  }

  public void addToCache(Scores newScore) {
      if (playerScore.containsKey(newScore.getPlayerId())) {
        Scores scoreToBeUpdated = playerScore.get(newScore.getPlayerId());
        if (scoreToBeUpdated.getScore() < newScore.getScore()) {
          minHeap.remove(scoreToBeUpdated);
          playerScore.put(newScore.getPlayerId(), newScore);
          minHeap.add(newScore);
        }
        return;
      }
      updateMinHeap(newScore);
  }

  @Override
  public List<Scores> getTopNScorers() {
    try {
      List<Scores> res = new ArrayList<>(minHeap);
      res.sort(Collections.reverseOrder());
      return res;
    }catch (Exception ex){
      throw new CacheException("Error getting data from cache");
    }
  }
}

