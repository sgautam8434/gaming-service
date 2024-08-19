package com.intuit.gaming_service.controller;

import com.intuit.gaming_service.converters.LeaderBoardMapper;
import com.intuit.gaming_service.dto.CreateGameRequestDto;
import com.intuit.gaming_service.dto.ResponseDto;
import com.intuit.gaming_service.exception.DbFetchException;
import com.intuit.gaming_service.service.LeaderBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderBoardController {

  @Autowired
  private LeaderBoardService leaderBoardService;

  @Autowired
  private LeaderBoardMapper leaderBoardMapper;

  @GetMapping(path = {"/getTopScorers"})
  public ResponseEntity<ResponseDto> getTopScorers() {
    return new ResponseEntity<>(leaderBoardService.getTopScorers(), HttpStatus.OK);
  }

  @PostMapping(path = {"/createGame"})
  public ResponseEntity<ResponseDto> createGame(@RequestBody CreateGameRequestDto request)
      throws DbFetchException {
    return new ResponseEntity<>(
        leaderBoardService.createGame(leaderBoardMapper.gameDtoToBo(request)), HttpStatus.OK);
  }
}
