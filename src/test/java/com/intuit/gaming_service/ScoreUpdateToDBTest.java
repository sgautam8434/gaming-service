package com.intuit.gaming_service;

import com.intuit.gaming_service.Mock.DataMock;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbUpdateException;
import com.intuit.gaming_service.repository.ScoreRepository;
import com.intuit.gaming_service.service.impl.ScoreUpdateToDbServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScoreUpdateToDBTest {

  @Mock
  private ScoreRepository scoreRepository;

  @InjectMocks
  private ScoreUpdateToDbServiceImpl scoreUpdateToDbService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddScoreWhenScoreNotPresent() throws DbUpdateException {
    Scores newScore = new Scores("P1", "player1", 100L);
    when(scoreRepository.findById(newScore.getPlayerId())).thenReturn(Optional.empty());

    scoreUpdateToDbService.addScore(newScore);

    verify(scoreRepository).save(newScore);
  }

  @Test
  public void testAddScoreWhenScorePresentWithLowerValue() throws DbUpdateException {
    Scores existingScore = new Scores("P1", "player1", 50L);
    Scores newScore = new Scores("P1", "player1", 100L);
    when(scoreRepository.findById(newScore.getPlayerId())).thenReturn(Optional.of(existingScore));

    scoreUpdateToDbService.addScore(newScore);

    verify(scoreRepository).save(newScore);
  }

  @Test
  public void testAddScoreWhenScorePresentWithHigherValue() throws DbUpdateException {
    Scores existingScore = new Scores("P1", "player1", 150L);
    Scores newScore = new Scores("P1", "player1", 100L);
    when(scoreRepository.findById(newScore.getPlayerId())).thenReturn(Optional.of(existingScore));

    scoreUpdateToDbService.addScore(newScore);

    verify(scoreRepository, never()).save(newScore);
  }

  @Test
  public void testAddScoreThrowsDbUpdateException() {
    Scores newScore = DataMock.getScore();
    when(scoreRepository.findById(newScore.getPlayerId())).thenThrow(new RuntimeException("Database error"));

    DbUpdateException exception = assertThrows(DbUpdateException.class, () -> {
      scoreUpdateToDbService.addScore(newScore);
    });

    assertEquals("Error in updating database", exception.getMessage());
  }
}
