package com.elijahhendrickson.javaproject.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Size;

@Entity
@Table(name="choices")
public class Choice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	
//	@Size(min=3, max=30, message="Possible must be between 3 and 30 characters")
	private String possible;
	
	public Choice() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getPossible() {
		return possible;
	}

	public void setPossible(String possible) {
		this.possible = possible;
	}
	 
	 
}
