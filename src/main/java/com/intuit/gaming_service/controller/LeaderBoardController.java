package com.intuit.gaming_service.controller;

import com.intuit.gaming_service.dto.ResponseDto;
import com.intuit.gaming_service.service.LeaderBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderBoardController {

  @Autowired
  private LeaderBoardService leaderBoardService;


  @GetMapping(path = {"/getTopScorers"})
  public ResponseEntity<ResponseDto> getTopScorers() {
    return new ResponseEntity<>(new ResponseDto(null, "get Top Scorers controller", true),
        HttpStatus.OK);
  }
}
