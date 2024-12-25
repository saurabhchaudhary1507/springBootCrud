package com.bezkoder.spring.jpa.h2.repository;

import jakarta.persistence.LockModeType;

import java.util.Optional;

import com.bezkoder.spring.jpa.h2.model.Tutorial;

public interface CustomTutorialRepository {
	
	Optional<Tutorial> lockById(Long id, LockModeType lockMode);
}
