package com.bezkoder.spring.jpa.h2.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;

import com.bezkoder.spring.jpa.h2.model.Tutorial;

public interface TutorialService {

	CompletableFuture<List<Tutorial>> findByPublished(boolean published);

	List<Tutorial> findByTitleContainingIgnoreCase(String title);

	Iterable<Tutorial> findAll();

	Optional<Tutorial> findById(long id);

	Tutorial save(Tutorial tutorial);

	void deleteById(long id);

	void deleteAll();

	CompletableFuture<ResponseEntity<List<Tutorial>>> findByPublishedExternal(boolean b);
}
