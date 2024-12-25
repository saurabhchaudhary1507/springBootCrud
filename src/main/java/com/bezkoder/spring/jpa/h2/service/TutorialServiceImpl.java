package com.bezkoder.spring.jpa.h2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.repository.TutorialRepository;

import jakarta.persistence.LockModeType;

@Service
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	TutorialRepository tutorialRepository;

	@Override
	public List<Tutorial> findByPublished(boolean published) {
		return tutorialRepository.findByPublished(published);
	}

	@Override
	public List<Tutorial> findByTitleContainingIgnoreCase(String title) {
		return tutorialRepository.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public Iterable<Tutorial> findAll() {
		return tutorialRepository.findAll();
	}

	@Override
	//@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	public Optional<Tutorial> findById(long id) {
		//return tutorialRepository.findById(id);
		System.out.println("findById called");
//		Optional<Tutorial> tutorial = tutorialRepository.lockById(id, LockModeType.PESSIMISTIC_WRITE);
		Optional<Tutorial> tutorial = tutorialRepository.findById(id);
		return tutorial; 
	}

	@Override
	@Transactional
	public Tutorial save(Tutorial tutorial) {
		System.out.println("save called");
		Tutorial tut=tutorialRepository.save(tutorial);
		return tut;
	}

	@Override
	public void deleteById(long id) {
		tutorialRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		tutorialRepository.deleteAll();
	}

}
