package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbUpdateException;
import com.intuit.gaming_service.service.LeaderBoardObserver;
import com.intuit.gaming_service.service.ScoreService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreServiceImpl implements ScoreService {

  List<LeaderBoardObserver> leaderBoards = new ArrayList<>();
  ScoreUpdateToDbServiceImpl scoreUpdateServiceDb;

  ScoreUpdateToCacheServiceImpl scoreUpdateToCacheService;

  @Override
  public void registerLeaderBoard(LeaderBoardObserver leaderBoard) {
    leaderBoards.add(leaderBoard);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void addNewScore(Scores newScore) throws DbUpdateException {
    scoreUpdateServiceDb.addScore(newScore);
    scoreUpdateToCacheService.addScore(newScore);
    for (LeaderBoardObserver leaderBoard : leaderBoards) {
      leaderBoard.updateScores(newScore);
    }
  }
}
