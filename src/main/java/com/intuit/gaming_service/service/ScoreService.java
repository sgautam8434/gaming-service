package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbUpdateException;

public interface ScoreService {

  void registerLeaderBoard(LeaderBoardService leaderBoard);

  void addNewScore(Scores newScore) throws DbUpdateException;
}
