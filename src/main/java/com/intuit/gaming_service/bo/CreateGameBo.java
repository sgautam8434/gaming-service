package com.intuit.gaming_service.bo;

public class CreateGameBo {

  public Long gameId;

  public Integer topN;

  public Long getGameId() {
    return gameId;
  }

  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  public Integer getTopN() {
    return topN;
  }

  public void setTopN(Integer topN) {
    this.topN = topN;
  }
}
