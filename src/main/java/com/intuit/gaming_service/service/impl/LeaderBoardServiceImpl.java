package com.intuit.gaming_service.service.impl;

import jakarta.annotation.PostConstruct;
import com.intuit.gaming_service.bo.CreateGameBo;
import com.intuit.gaming_service.dto.ResponseDto;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbFetchException;
import com.intuit.gaming_service.repository.ScoreRepository;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.EntityService;
import com.intuit.gaming_service.service.LeaderBoardService;
import com.intuit.gaming_service.service.ScoreService;

import java.util.List;

import org.hibernate.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

  @Value("${game.default.topN}")
  private Integer defaultLeaderBoardSize;

  @Autowired
  private EntityService entityService;

  @Autowired
  private CacheService cacheService;

  @Autowired
  private ScoreService scoreService;

  public boolean leaderBoardInitialized;

  Logger LOGGER = LoggerFactory.getLogger(LeaderBoardServiceImpl.class);

  @PostConstruct
  public void createBoard() throws DbFetchException {
    initialiseGame(defaultLeaderBoardSize);
  }

  @Override
  public ResponseDto getTopScorers() {
    if (leaderBoardInitialized) {
      return new ResponseDto(cacheService.getTopNPlayers(), "top N players", true);
    } else {
      LOGGER.error("Cache not initialised");
      throw new CacheException("Cache not initialised");
    }
  }

  @Override
  public ResponseDto createGame(CreateGameBo requestBo) throws DbFetchException {
    initialiseGame(requestBo.getTopNScorers());
    return new ResponseDto(null,"game created",true);
  }

  private void initialiseGame(Integer topN) throws DbFetchException {
    try {
      List<Scores> allPlayerScores = entityService.findAllEntities();
      cacheService.initialiseCache(topN, allPlayerScores);
      scoreService.registerLeaderBoard(this);
      leaderBoardInitialized = true;
    } catch (Exception e) {
      throw new DbFetchException("Exception while fetching data from the db");
    }
  }
}
