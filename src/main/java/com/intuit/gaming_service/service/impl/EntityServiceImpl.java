package com.intuit.gaming_service.service.impl;

import jakarta.transaction.Transactional;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.repository.ScoreRepository;
import com.intuit.gaming_service.service.EntityService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityServiceImpl implements EntityService {

  @Autowired
  ScoreRepository scoreRepository;

  @Override
  @Transactional
  public List<Scores> findAllEntities() {
    return scoreRepository.findAll();
  }
}


