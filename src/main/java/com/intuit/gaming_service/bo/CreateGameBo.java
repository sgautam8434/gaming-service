package com.intuit.gaming_service.bo;

public class CreateGameBo {

  public Long gameId;

  public Integer topNScorers;

  public Long getGameId() {
    return gameId;
  }

  public void setGameId(Long gameId) {
    this.gameId = gameId;
  }

  public Integer getTopNScorers() {
    return topNScorers;
  }

  public void setTopNScorers(Integer topNScorers) {
    this.topNScorers = topNScorers;
  }
}
