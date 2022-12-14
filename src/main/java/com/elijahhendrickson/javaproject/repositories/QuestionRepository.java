package com.elijahhendrickson.javaproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elijahhendrickson.javaproject.models.Category;
import com.elijahhendrickson.javaproject.models.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {
	
	List<Question> findAll();
	
	List<Question> findAllByCategory(Category category);
}
