package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.repository.ScoreRepository;
import com.intuit.gaming_service.service.ScoreUpdateService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreUpdateToDbServiceImpl implements ScoreUpdateService {

  @Autowired
  ScoreRepository scoreRepository;

  @Override
  public void addScore(Scores newScore){
    Optional<Scores> scoreAlreadyPresent = scoreRepository.findById(newScore.getPlayerId());
    if (scoreAlreadyPresent.isPresent()
        && scoreAlreadyPresent.get().getScore() >= newScore.getScore()) {
      return;
    }
    scoreRepository.save(newScore);
  }

}
