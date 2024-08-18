package com.intuit.gaming_service.service;

import com.intuit.gaming_service.entity.Scores;

import java.util.List;

public interface EntityService {

  List<Scores> findAllEntities();
}
