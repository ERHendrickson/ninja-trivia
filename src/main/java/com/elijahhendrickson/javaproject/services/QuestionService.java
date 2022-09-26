package com.elijahhendrickson.javaproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elijahhendrickson.javaproject.models.Category;
import com.elijahhendrickson.javaproject.models.Question;
import com.elijahhendrickson.javaproject.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questRepo;
	
//-------------------------CREATE A QUESTION----------------------------
	public Question createQuest(Question quest) {
		return questRepo.save(quest);
	}

//-------------------------FIND ALL QUESTIONS---------------------------
	public List<Question> allQuestions(){
		return questRepo.findAll();
	}
	
//-------------------------FIND ALL BY CATEGORY-------------------------
	public List<Question> findQuestsByCat(Category category){
		return questRepo.findAllByCategory(category);
	}
	
//-------------------------FIND QUESTION BY ID--------------------------
	public Question findQuest(Long id) {
		Optional<Question> foundQuest = questRepo.findById(id);
		if(foundQuest.isPresent()) {
			return foundQuest.get();
		}else {
			return null;		
		}
	}
	
//---------------------------UPDATE QUEST-------------------------------
	public Question updateQuest(Question question) {
		return questRepo.save(question);
	}
	
//---------------------------DELETE QUEST-------------------------------
	public void deleteQuest(Question quest) {
		questRepo.delete(quest);
	}
}
