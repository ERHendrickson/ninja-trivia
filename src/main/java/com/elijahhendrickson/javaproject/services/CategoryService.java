package com.elijahhendrickson.javaproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elijahhendrickson.javaproject.models.Category;
import com.elijahhendrickson.javaproject.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository catRepo;
	
//------------------------------CREATE A CATEGORY-------------------------
	public Category createCat(Category cat) {
		return catRepo.save(cat);
	}

//------------------------------FIND ALL CATEGORIES-----------------------
	public List<Category> allCats(){
		return catRepo.findAll();
	}
	
//------------------------------FIND ONE CAT------------------------------
	public Category findCat(Long id) {
		Optional<Category> foundCat = catRepo.findById(id);
		if(foundCat.isPresent()) {
			return foundCat.get();
		}else {
			return null;
		}
	}
	
//-----------------------------UPDATE CATEGORY---------------------------
	public Category updateCat(Category cat) {
		return catRepo.save(cat);
	}

//-----------------------------DELETE CATEGORY---------------------------
	public void deleteCat(Category cat) {
		catRepo.delete(cat);
	}
}
