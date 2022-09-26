package com.elijahhendrickson.javaproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elijahhendrickson.javaproject.models.Choice;
import com.elijahhendrickson.javaproject.models.Question;
import com.elijahhendrickson.javaproject.repositories.ChoiceRepository;

@Service
public class ChoiceService {

	@Autowired
	private ChoiceRepository choiceRepo;
	
//------------------------CREATE CHOICE FOR QUESTION------------------
	public Choice createChoice(Choice choice) {
		return choiceRepo.save(choice);
	}
		
//------------------------FIND ALL CHOICES FOR QUESTION---------------
	public List<Choice> allChoiceForQuest(Question question){
		return choiceRepo.findAllByQuestion(question);
	}

//------------------------FIND CHOICE BY ID---------------------------
	public Choice findChoice(Long id) {
		Optional<Choice> foundChoice = choiceRepo.findById(id);
		if(foundChoice.isPresent()) {
			return foundChoice.get();
		}else {
			return null;
		}
	}

//------------------------UPDATE CHOICE-------------------------------
	public Choice updateChoice(Choice choice) {
		return choiceRepo.save(choice);
	}
	
//------------------------DELETE CHOICE-------------------------------
	public void deleteChoice(Choice choice) {
		choiceRepo.delete(choice);
	}
}
