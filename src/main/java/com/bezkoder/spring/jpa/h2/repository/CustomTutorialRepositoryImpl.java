package com.bezkoder.spring.jpa.h2.repository;

import java.util.Optional;

import com.bezkoder.spring.jpa.h2.model.Tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;

public class CustomTutorialRepositoryImpl implements CustomTutorialRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Tutorial> lockById(Long id, LockModeType lockMode) {
		Optional<Tutorial> of =  Optional.of(entityManager.find(Tutorial.class, id));//Optional.of(entityManager.find(Tutorial.class, id, lockMode));
		return of;

	}
}
