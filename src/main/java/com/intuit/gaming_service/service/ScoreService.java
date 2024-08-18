package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;

public interface ScoreService {

  void registerLeaderBoard(LeaderBoardService leaderBoard);

  void publishToLeaderBoards(Scores newScore);

  void addNewScore(Scores newScore);
}
