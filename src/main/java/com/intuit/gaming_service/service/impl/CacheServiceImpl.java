package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.CacheService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

  PriorityQueue<Scores> minHeap;

  int topN;

  Map<String, Scores> playerScore;
  @Override
  public void initialiseCache(Integer topN, List<Scores> allPlayerScores) {
    this.topN = topN;
    minHeap = new PriorityQueue<>();
    playerScore = new HashMap<>();
    for (Scores score : allPlayerScores) {
      updateCache(score);
    }
  }

  private void updateCache(Scores score) {
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

  @Override
  public List<Scores> getTopNPlayers() {
    List<Scores> res = new ArrayList<>(minHeap);
    res.sort(Collections.reverseOrder());
    return res;
  }

}

