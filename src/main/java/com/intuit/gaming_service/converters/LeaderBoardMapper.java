package com.intuit.gaming_service.converters;

import com.intuit.gaming_service.bo.CreateGameBo;
import com.intuit.gaming_service.dto.CreateGameRequestDto;
import com.intuit.gaming_service.utils.BasicUtils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LeaderBoardMapper {

  @Mapping(target = "gameId", expression = "java(createGameId())")
  CreateGameBo gameDtoToBo(CreateGameRequestDto dto);

  default Long createGameId() {
    return BasicUtils.createRandomUUID();
  }
}
