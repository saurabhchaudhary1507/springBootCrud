package com.bezkoder.spring.jpa.h2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.history.RevisionRepository;

import com.bezkoder.spring.jpa.h2.model.Tutorial;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>,CustomTutorialRepository,RevisionRepository<Tutorial, Long, Integer> {
	
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContainingIgnoreCase(String title);
  
  @Lock(value = LockModeType.PESSIMISTIC_WRITE)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "0")})
  Optional<Tutorial> findById(Long id);
}
