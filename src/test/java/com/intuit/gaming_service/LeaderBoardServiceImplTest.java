package com.intuit.gaming_service;

import com.intuit.gaming_service.Mock.DataMock;
import com.intuit.gaming_service.dto.ResponseDto;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.impl.LeaderBoardServiceImpl;

import java.util.List;

import org.hibernate.cache.CacheException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = GamingServiceApplicationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LeaderBoardServiceImplTest {

  @Mock
  private CacheService cacheService;

  @InjectMocks
  private LeaderBoardServiceImpl leaderBoardService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetTopScorersWhenLeaderBoardInitialized() {

    List<Scores> topNPlayers = DataMock.getCacheData();
    leaderBoardService.leaderBoardInitialized =true;
    when(cacheService.getTopNScorers()).thenReturn(topNPlayers);
    ResponseDto response = leaderBoardService.getTopScorers();

    assertNotNull(response);
    assertTrue(response.isSuccess());
    assertEquals(topNPlayers, response.getData());
    assertEquals("top N players", response.getMessage());
    verify(cacheService).getTopNScorers();
  }

  @Test
  void testGetTopScorersWhenLeaderBoardNotInitialized() {

    leaderBoardService.leaderBoardInitialized = false;

    CacheException thrown = assertThrows(CacheException.class, () -> {
      leaderBoardService.getTopScorers();
    });
    assertEquals("Cache not initialised", thrown.getMessage());
    verify(cacheService, never()).getTopNScorers();
  }
}
