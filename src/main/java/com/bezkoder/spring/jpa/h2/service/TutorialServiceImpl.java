package com.bezkoder.spring.jpa.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	TutorialRepository tutorialRepository;
	
	@Autowired
	RestTemplate restTemplate; 
	
	@Override
	@Async
	public CompletableFuture<List<Tutorial>> findByPublished(boolean published) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		List<Tutorial> byPublished = tutorialRepository.findByPublished(published);
		return CompletableFuture.completedFuture(byPublished);
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

	public List<Tutorial> getTutorialEditHistory(long postID) {
		
		List<Tutorial> historyList = new ArrayList<Tutorial>();
		
		tutorialRepository.findRevisions(postID).get().forEach(x -> {
			x.getEntity().setEditVersion(x.getMetadata());
			historyList.add(x.getEntity());
		});
		
		return historyList;
	}

	@Override
	public CompletableFuture<ResponseEntity<List<Tutorial>>> findByPublishedExternal(boolean b) {
		ResponseEntity<CompletableFuture<ResponseEntity<List<Tutorial>>>> forObject = this.restTemplate.
				exchange("http://localhost:8080/api/tutorials/published",HttpMethod.GET, null, new ParameterizedTypeReference<CompletableFuture<ResponseEntity<List<Tutorial>>>>() {});
		return forObject.getBody();
	}
	
}
