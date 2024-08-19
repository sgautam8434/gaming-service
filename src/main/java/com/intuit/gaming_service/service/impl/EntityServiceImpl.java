package com.intuit.gaming_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import com.intuit.gaming_service.entity.Scores;
import com.intuit.gaming_service.exception.DbFetchException;
import com.intuit.gaming_service.repository.ScoreRepository;
import com.intuit.gaming_service.service.EntityService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.stereotype.Service;

@Service
public class EntityServiceImpl implements EntityService {

  @Autowired
  ScoreRepository scoreRepository;

  @Override
  public List<Scores> findAllEntities() {
    return scoreRepository.findAll();
  }
}


