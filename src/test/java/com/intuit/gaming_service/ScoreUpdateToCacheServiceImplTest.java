package com.intuit.gaming_service;

import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.impl.ScoreUpdateToCacheServiceImpl;

import org.hibernate.cache.CacheException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ScoreUpdateToCacheServiceImplTest {

  @Mock
  private CacheService cacheService;

  @InjectMocks
  private ScoreUpdateToCacheServiceImpl scoreUpdateToCacheService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddScoreSuccessfully() {
    Scores newScore = new Scores("P1", "player1", 100L);
    scoreUpdateToCacheService.addScore(newScore);
    verify(cacheService).addToCache(newScore);
  }

  @Test
  public void testAddScoreThrowsCacheException() {
    Scores newScore = new Scores("P1", "player1", 100L);
    doThrow(new RuntimeException("Cache error")).when(cacheService).addToCache(newScore);

    CacheException exception = assertThrows(CacheException.class, () -> {
      scoreUpdateToCacheService.addScore(newScore);
    });

    assertEquals("Error while updating cache", exception.getMessage());
  }
}
