package com.elijahhendrickson.javaproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elijahhendrickson.javaproject.models.Category;
import com.elijahhendrickson.javaproject.models.Game;
import com.elijahhendrickson.javaproject.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;
	
//------------------------CREATE GAME--------------------------------
	public Game createGame(Game game) {
		return gameRepo.save(game);
	}
	
//------------------------ALL GAMES----------------------------------
	public List<Game> allGames(){
		return gameRepo.findAll();
	}

//------------------------FIND GAME----------------------------------
	public Game findGame(Long id) {
		Optional<Game> foundGame = gameRepo.findById(id);
		if(foundGame.isPresent()) {
			return foundGame.get();
		}else {
			return null;
		}
	}

//-----------------------FIND TOP 5---------------------------------
	public List<Game> scoreByCategory(Category category){
		return gameRepo.findTop3ByCategoryOrderByScoreDesc(category);
	}

//-----------------------UPDATE GAME--------------------------------
	public Game updateGame(Game game) {
		return gameRepo.save(game);
	}
	
//----------------------DELETE GAME---------------------------------
	public void deleteGame(Game game) {
		gameRepo.delete(game);
	}
}
