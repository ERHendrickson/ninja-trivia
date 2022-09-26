package com.elijahhendrickson.javaproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elijahhendrickson.javaproject.models.Category;
import com.elijahhendrickson.javaproject.models.Game;

@Repository
public interface GameRepository extends CrudRepository<Game,Long>{

	List<Game> findAll();
	
	List<Game> findTop3ByCategoryOrderByScoreDesc(Category category);
}
