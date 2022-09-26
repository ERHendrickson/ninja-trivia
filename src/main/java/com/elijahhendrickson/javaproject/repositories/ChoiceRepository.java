package com.elijahhendrickson.javaproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elijahhendrickson.javaproject.models.Choice;
import com.elijahhendrickson.javaproject.models.Question;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice,Long>{
	
	List<Choice> findAllByQuestion(Question question);
	
}
