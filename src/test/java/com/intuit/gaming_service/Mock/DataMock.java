package com.intuit.gaming_service.Mock;

import com.intuit.gaming_service.entity.Scores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMock {

  static Scores p1 = new Scores("100","P1", 100L);
  static Scores p2 = new Scores("101","P2", 200L);
  static Scores p3 = new Scores("102","P3", 300L);
  static Scores p4 = new Scores("103","P4", 400L);

  static Scores p5 = new Scores("104","P5", 500L);

  static Scores p6 = new Scores("105","P6", 600L);

  public static List<Scores> getAllScores() {
    List<Scores> allScores = new ArrayList<>();
    allScores.add(p1);
    allScores.add(p2);
    allScores.add(p3);
    allScores.add(p4);
    allScores.add(p5);
    allScores.add(p6);
    return allScores;
  }

  public static List<Scores> getCacheData() {
    List<Scores> cacheData = new ArrayList<>();
    cacheData.add(p6);
    cacheData.add(p5);
    cacheData.add(p4);
    cacheData.add(p3);
    cacheData.add(p2);
    return cacheData;
  }

  public static Scores getScore(){
    return p1;
  }

  public static Map<String,Scores> getPlayerMap(){
    Map<String, Scores> playerScoreMap = new HashMap<>();
    playerScoreMap.put(p1.getPlayerId(),p1);
    playerScoreMap.put(p2.getPlayerId(),p2);
    playerScoreMap.put(p3.getPlayerId(),p3);
    playerScoreMap.put(p4.getPlayerId(),p4);
    playerScoreMap.put(p5.getPlayerId(),p5);
    playerScoreMap.put(p6.getPlayerId(),p6);
    return playerScoreMap;
  }
}
