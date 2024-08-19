package com.intuit.gaming_service;

import com.intuit.gaming_service.Mock.DataMock;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbUpdateException;
import com.intuit.gaming_service.exception.KafkaException;
import com.intuit.gaming_service.service.impl.ConsumerServiceImpl;
import com.intuit.gaming_service.service.impl.ScoreServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = GamingServiceApplicationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ConsumerServiceImplTest {

  @InjectMocks
  private ConsumerServiceImpl consumerService;

  Scores mockScore;

  @Mock
  ScoreServiceImpl scoreService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockScore = new Scores();
  }
  @Test
  public void testConsumeDataFromQueueSuccessfully() throws KafkaException, DbUpdateException {
    Scores newScore = DataMock.getScore();
    consumerService.consumeDataFromQueue(newScore);
    verify(scoreService).addNewScore(newScore);
  }

  @Test
  public void testConsumeDataFromQueueThrowsKafkaException() throws DbUpdateException {
    Scores newScore = DataMock.getScore();
    doThrow(new RuntimeException("exception")).when(scoreService).addNewScore(newScore);
    assertThrows(KafkaException.class, () -> {
      consumerService.consumeDataFromQueue(newScore);
    });
  }
}
