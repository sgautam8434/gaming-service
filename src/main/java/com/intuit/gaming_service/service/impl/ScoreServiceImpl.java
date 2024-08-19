package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbUpdateException;
import com.intuit.gaming_service.service.LeaderBoardService;
import com.intuit.gaming_service.service.ScoreService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreServiceImpl implements ScoreService {

  List<LeaderBoardService> leaderBoards = new ArrayList<LeaderBoardService>();


  @Autowired
  ScoreUpdateToDbServiceImpl scoreUpdateServiceDb;

  @Autowired
  ScoreUpdateToCacheServiceImpl scoreUpdateToCacheService;

  @Override
  public void registerLeaderBoard(LeaderBoardService leaderBoard) {
    leaderBoards.add(leaderBoard);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void addNewScore(Scores newScore) throws DbUpdateException {
    scoreUpdateServiceDb.addScore(newScore);
    scoreUpdateToCacheService.addScore(newScore);
  }
}
