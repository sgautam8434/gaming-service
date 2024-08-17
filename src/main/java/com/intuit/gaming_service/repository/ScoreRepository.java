package com.intuit.gaming_service.repository;

import com.intuit.gaming_service.entity.Scores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Scores, String>{

}

