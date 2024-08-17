package com.intuit.gaming_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="scores")
public class Scores {

  private String player_name;

  private Long score;

  public String getPlayer_name() {
    return player_name;
  }

  public void setPlayer_name(String player_name) {
    this.player_name = player_name;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Scores(String player_name, Long score) {
    this.player_name = player_name;
    this.score = score;
  }

  @Override
  public String toString() {
    return "Scores{" +
        "player_name='" + player_name + '\'' +
        ", score=" + score +
        '}';
  }
}
