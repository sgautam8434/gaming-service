package com.intuit.gaming_service.service.impl;

import com.intuit.gaming_service.dto.ResponseDto;
import com.intuit.gaming_service.service.LeaderBoardService;

import org.springframework.stereotype.Service;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

  @Override
  public ResponseDto getTopScorers(){

    return new ResponseDto(null,"return from service layer",true);
  }

}
