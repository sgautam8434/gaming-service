package com.intuit.gaming_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="scores")
public class Scores {

  @Id
  @Column(name="player_id")
  private String playerId;

  @Column(name="player_name")
  private String playerName;

  private Long score;

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Scores() {
  }

  public Scores(String player_name, Long score) {
    this.playerName = player_name;
    this.score = score;
  }

  @Override
  public String toString() {
    return "Scores{" +
        "player_name='" + playerName + '\'' +
        ", score=" + score +
        '}';
  }
}
