package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;

public interface ScoreUpdateService {

  void registerLeaderBoard(LeaderBoardService leaderBoard);

  void publishToLeaderBoards(Scores newScore);

  void consumeFromKafka(Scores newScore);
}
