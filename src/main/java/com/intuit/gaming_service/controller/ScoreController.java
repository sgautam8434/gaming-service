package com.intuit.gaming_service.controller;

import com.intuit.gaming_service.dto.ResponseDto;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.ProducerService;
import com.intuit.gaming_service.service.ScoreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ScoreController {

  @Autowired
  ScoreService scoreService;

  @Autowired
  ProducerService producerService;

  Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);

  @PostMapping("/updateScore")
  public void updateScore(@RequestBody Scores newScore) {
    try {
      scoreService.addNewScore(newScore);
    } catch (Exception e) {
      LOGGER.error("Leaderboard Update failed - " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PostMapping("/createNewScore")
  public ResponseEntity<ResponseDto> createNewScore(@RequestBody Scores newScore) {
    producerService.createNewScore(newScore);
    return new ResponseEntity<>(new ResponseDto(null,"score produced",true),HttpStatus.OK);
  }

}
