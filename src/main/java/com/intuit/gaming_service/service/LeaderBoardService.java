package com.intuit.gaming_service.service;

import com.intuit.gaming_service.bo.CreateGameBo;
import com.intuit.gaming_service.dto.ResponseDto;

import org.springframework.stereotype.Service;

public interface LeaderBoardService {

  ResponseDto getTopScorers();

  ResponseDto createGame(CreateGameBo requestBo);
}
