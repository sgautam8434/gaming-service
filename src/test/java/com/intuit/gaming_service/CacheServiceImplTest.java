package com.intuit.gaming_service;

import com.intuit.gaming_service.Mock.CacheServiceMock;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.service.CacheService;
import com.intuit.gaming_service.service.impl.CacheServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GamingServiceApplicationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CacheServiceImplTest {

  @InjectMocks
  private CacheServiceImpl cacheService;

  @Test
  public void getTopNPlayersTest(){
    List<Scores> allScores = CacheServiceMock.getAllScores();
    List<Scores> getCacheValue = CacheServiceMock.getCacheData();
    cacheService.initialiseCache(5,allScores);
    assertEquals(getCacheValue, cacheService.getTopNPlayers());
  }


}
