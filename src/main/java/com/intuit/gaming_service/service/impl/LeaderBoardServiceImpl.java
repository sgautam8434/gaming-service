package com.intuit.gaming_service.service.impl;

import jakarta.annotation.PostConstruct;
import com.intuit.gaming_service.bo.CreateGameBo;
import com.intuit.gaming_service.dto.ResponseDto;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.repository.ScoreRepository;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.LeaderBoardService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

  @Value("game.default.topN")
  private Integer defaultLeaderBoardSize;
  @PostConstruct
  public void createBoard() {
    initialiseGame(defaultLeaderBoardSize);
  }

  @Autowired
  private ScoreRepository scoreRepository;

  @Autowired
  private CacheService cacheService;

  @Override
  public ResponseDto getTopScorers() {
    return new ResponseDto(cacheService.getTopNPlayers(), "top N players", true);
  }

  @Override
  public ResponseDto createGame(CreateGameBo requestBo){
    return new ResponseDto(null,"game created",true);
  }

  private void initialiseGame(Integer topN){
    List<Scores> allPlayerScores = scoreRepository.findAll();
    cacheService.initialiseCache(topN,allPlayerScores);
  }
}
