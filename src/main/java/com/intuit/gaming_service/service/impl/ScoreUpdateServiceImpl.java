package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.repository.ScoreRepository;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.LeaderBoardService;
import com.intuit.gaming_service.service.ScoreUpdateService;
import com.intuit.gaming_service.service.ScoreUpdateToCacheService;
import com.intuit.gaming_service.service.ScoreUpdateToDbService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreUpdateServiceImpl implements ScoreUpdateService, ScoreUpdateToDbService,
    ScoreUpdateToCacheService {

  List<LeaderBoardService> leaderBoards = new ArrayList<LeaderBoardService>();

  @Autowired
  ScoreRepository scoreRepository;

  @Autowired
  CacheService cacheService;

  @Override
  public void saveToDb(Scores newScore) {
    Optional<Scores> scoreAlreadyPresent = scoreRepository.findById(newScore.getPlayerId());
    if (scoreAlreadyPresent.isPresent()
        && scoreAlreadyPresent.get().getScore() >= newScore.getScore()) {
      return;
    }
    scoreRepository.save(newScore);
  }

  @Override
  public void registerLeaderBoard(LeaderBoardService leaderBoard) {
    leaderBoards.add(leaderBoard);
  }

  public void publishToLeaderBoards(Scores newScore)  {
    for (LeaderBoardService leaderBoard : leaderBoards) {
      addNewScoreToCache(newScore);
    }
  }

  @Override
  public void addNewScoreToCache(Scores newScore){
    cacheService.addToCache(newScore);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void consumeFromKafka(Scores newScore) {
    saveToDb(newScore);
    publishToLeaderBoards(newScore);
  }
}
