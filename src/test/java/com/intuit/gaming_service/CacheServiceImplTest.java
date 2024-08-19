package com.intuit.gaming_service;

import com.intuit.gaming_service.Mock.DataMock;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.impl.CacheServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.hibernate.cache.CacheException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;


@SpringBootTest(classes = GamingServiceApplicationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CacheServiceImplTest {

  @InjectMocks
  private CacheServiceImpl cacheService;

  @Mock
  private PriorityQueue<Scores> minHeap;

  @Test
  public void getTopNPlayersTest(){
    List<Scores> allScores = DataMock.getAllScores();
    List<Scores> getCacheValue = DataMock.getCacheData();
    cacheService.initialiseCache(5,allScores);
    assertEquals(getCacheValue, cacheService.getTopNScorers());
  }

  @Test
  public void testGetTopNPlayersThrowsCacheException() {
    doThrow(new RuntimeException("throw runtime exception")).when(minHeap).iterator();
    assertThrows(CacheException.class, () -> {
      cacheService.getTopNScorers();
    });
  }

  @Test
  public void testInitialiseCacheSuccessfully() {
    List<Scores> scoresList = DataMock.getCacheData();

    cacheService.initialiseCache(3, scoresList);

    assertEquals(3, cacheService.getTopNScorers().size());
    assertEquals(3, cacheService.minHeap.size());
  }

  @Test
  public void testInitialiseCacheWithEmptyList() {
    List<Scores> emptyScoresList = Collections.emptyList();

    cacheService.initialiseCache(3, emptyScoresList);

    assertEquals(0, cacheService.getTopNScorers().size());
    assertTrue(cacheService.minHeap.isEmpty());
  }

  @Test
  public void testInitialiseCacheThrowsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> {
      cacheService.initialiseCache(0, null);
    });
  }

  @Test
  public void testInitialiseCacheThrowsCacheException() {
    List<Scores> scoresList = Arrays.asList(
        new Scores("1","player1", 100L),
        null
    );

    assertThrows(CacheException.class, () -> {
      cacheService.initialiseCache(3, scoresList);
    });
  }

  @Test
  public void testAddToCacheWithNewPlayer() {
    List<Scores> allScores = DataMock.getAllScores();

    cacheService.initialiseCache(2, allScores);

    Scores newScore = new Scores("110","player3", 3000L);
    cacheService.addToCache(newScore);

    assertEquals(2, cacheService.minHeap.size());
    assertTrue(cacheService.playerScore.containsKey("110"));
  }

  @Test
  public void testAddToCacheWithExistingPlayerAndHigherScore() {
    List<Scores> scoresList = DataMock.getAllScores();

    cacheService.initialiseCache(2, scoresList);

    Scores newScore = new Scores("105","P6", 1500L);
    cacheService.addToCache(newScore);

    assertEquals(2, cacheService.minHeap.size());
    assertEquals(1500L, cacheService.playerScore.get("105").getScore());
  }

  @Test
  public void testAddToCacheWithExistingPlayerAndLowerScore() {
    List<Scores> scoresList = DataMock.getAllScores();

    cacheService.initialiseCache(2, scoresList);

    Scores newScore = new Scores("105","P6", 150L);
    cacheService.addToCache(newScore);


    assertEquals(2, cacheService.minHeap.size());
    assertEquals(600L, cacheService.playerScore.get("105").getScore()); // Score should not update
  }

}
