package com.bezkoder.spring.jpa.h2.service;

import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.jpa.h2.model.Tutorial;

public interface TutorialService {

	List<Tutorial> findByPublished(boolean published);

	List<Tutorial> findByTitleContainingIgnoreCase(String title);

	Iterable<Tutorial> findAll();

	Optional<Tutorial> findById(long id);

	Tutorial save(Tutorial tutorial);

	void deleteById(long id);

	void deleteAll();
}
