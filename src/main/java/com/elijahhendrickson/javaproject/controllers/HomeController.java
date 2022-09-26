package com.elijahhendrickson.javaproject.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elijahhendrickson.javaproject.models.Category;
import com.elijahhendrickson.javaproject.models.Choice;
import com.elijahhendrickson.javaproject.models.Game;
import com.elijahhendrickson.javaproject.models.LoginUser;
import com.elijahhendrickson.javaproject.models.Question;
import com.elijahhendrickson.javaproject.models.User;
import com.elijahhendrickson.javaproject.services.CategoryService;
import com.elijahhendrickson.javaproject.services.ChoiceService;
import com.elijahhendrickson.javaproject.services.GameService;
import com.elijahhendrickson.javaproject.services.QuestionService;
import com.elijahhendrickson.javaproject.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private QuestionService questServ;
	
	@Autowired
	private ChoiceService choiceServ;
	
	@Autowired
	private CategoryService catServ;
	
	@Autowired
	private GameService gameServ;
	
	@Autowired
	private UserService userServ;


//=====================================================================

//----------------------------LOGIN/REGIS------------------------------
	@GetMapping("/")
	public String rendLogin(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
//---------------------------POST REGIS--------------------------------
	@PostMapping("/regis")
	public String procRegis(@Valid @ModelAttribute("newUser")User newUser,
			BindingResult result,
			Model model,
			HttpSession session) {

		userServ.register(newUser,result);

		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
		session.setAttribute("userId", newUser.getId());
		
		return "redirect:/game/dash";
	}

//---------------------------POST LOGIN--------------------------------
	@PostMapping("/login")
	public String procLogin(@Valid @ModelAttribute("newLogin")LoginUser newLogin,
			BindingResult result,
			Model model,
			HttpSession session) {
		
		User user = userServ.login(newLogin,result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/game/dash";
	}

//=====================================================================
	
//----------------------------GET DASH---------------------------------
	@GetMapping("/game/dash")
	public String rendDash(HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		return "dash.jsp";
	}

//----------------------------LOGOUT-----------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
//=====================================================================
//--------------------------GET QUEST CREATE---------------------------
	@GetMapping("/quest/new")
	public String rendNew(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		List<Question> questList = questServ.allQuestions();
		List<Category> catList = catServ.allCats();
		
		model.addAttribute("questList", questList);
		model.addAttribute("catList", catList);
		model.addAttribute("cat", new Category());
		model.addAttribute("quest", new Question());
		model.addAttribute("choice", new Choice());
		return "create.jsp";
	}
	
//-----------------------------POST CAT CREATE-------------------------
	@PostMapping("/cat/new")
	public String createNewCat(@Valid @ModelAttribute("cat")Category cat, BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			return "create.jsp";
		}else {
			catServ.createCat(cat);
			return "redirect:/quest/new";			
		}
		
	}
	
//-----------------------------POST QUEST CREATE-----------------------
	@PostMapping("/quest/new")
	public String createNewQuest(@Valid @ModelAttribute("cat")Category cat, @ModelAttribute("quest")Question quest, BindingResult result,
			Model model) {
		if(result.hasErrors()) {	
			return "create.jsp";
		}else {
			questServ.createQuest(quest);
			return "redirect:/quest/new";	
		}
	}
	
//----------------------------POST CHOICE CREATE-----------------------
	@PostMapping("/choice/new")
	public String createNewChoice(@ModelAttribute("choice")Choice choice, BindingResult result,
			Model model) {
		if(result.hasErrors()) {		
			return "create.jsp";
		}else {
			choiceServ.createChoice(choice);
			return "redirect:/quest/new";
			
		}
	}

//=====================================================================
//---------------------------GET NEW GAME------------------------------
	@GetMapping("/game/new")
	public String rendGameNew(Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		List<Category> catList = catServ.allCats();
		Long userID = (Long) session.getAttribute("userId");
		User user = userServ.findUser(userID);
		
		model.addAttribute("user", user);
		model.addAttribute("game", new Game());
		model.addAttribute("catList", catList);
		
		return "newgame.jsp";
	}
	
//--------------------------POST GAME----------------------------------
	@PostMapping("/game/new")
	public String procGameNew(@ModelAttribute("game")Game game, BindingResult result, 
			HttpSession session
			) {          
		//create a game with the category id and the user id
		Game newGame = gameServ.createGame(game);
		
		//add the game to session to be updated as the game plays through
		session.setAttribute("currentGame", newGame);
		
		//add the list of questions for the selected category to session
		session.setAttribute("allQuests", questServ.findQuestsByCat(newGame.getCategory()));
		
		return "redirect:/quest/"+newGame.getId();
	}
	
//------------------------------QUESTION PROMPT----------------------
	@GetMapping("/quest/{id}")
	public String rendQuestion(@PathVariable("id")Long id, Model model,
			HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		//get the list of questions from session
		List<Question> allQuests = (List<Question>)session.getAttribute("allQuests");
		if(allQuests.size()== 0) {
			return "redirect:/game/over";
		}else {
			
			//shuffle the list of questions
			Collections.shuffle(allQuests);
			//pull the question from index 0
			Question quest = allQuests.get(0);
			//add quest to model
			model.addAttribute("question", quest);
			
			//find list of choices for the question
			List<Choice> choiceList = choiceServ.allChoiceForQuest(quest);
			//shuffle the list of choices
			Collections.shuffle(choiceList);
			//add choiceList to model
			model.addAttribute("choiceList",choiceList);
			
			//add the categoryId to the model to be passed through the path variable
			model.addAttribute("id", id);
			
			//remove the question from the list of questions
			allQuests.remove(quest);
			//test
			model.addAttribute("questList",allQuests);
			
			return "quest.jsp";
		}
		
	}

//-------------------------------POST ANSWER-------------------------
	@PostMapping("/quest/answer/{id}")
	public String procAnswer(@PathVariable("id")Long id, @RequestParam("possible")String possible,
			@RequestParam("answer")String answer,
			Model model, HttpSession session) {
		
		model.addAttribute("possible", possible);
		model.addAttribute("answer", answer);

		//get current game from session to give points
		Game currentGame = (Game)session.getAttribute("currentGame");
		
		//get game from database
		Game game = gameServ.findGame(currentGame.getId());
		//update the score of the game from the game in session
		game.setScore(currentGame.getScore());
		
		if(possible.equals(answer)){
			//increment the current game points by an amount
			game.setScore((Integer)game.getScore()+500);
			gameServ.updateGame(game);
			session.setAttribute("currentGame", game);
			return "redirect:/quest/correct/"+id;
		}else {
			return "redirect:/quest/wrong/"+id;
		}
		
	}

//----------------------------ANSWER CORRECT---------------------------
	@GetMapping("/quest/correct/{id}")
	public String rendCorrect(@PathVariable("id")Long id, Model model,
			HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		//find and create an instance of the quest
		Question quest = questServ.findQuest(id);
		//create a model of the quest to render on page
		model.addAttribute("quest", quest);
		
		//find the game from session
		Game currentGame = (Game)session.getAttribute("currentGame");
		//pass the current game into the model so the correct.jsp can redirect to the next question
		model.addAttribute("game", currentGame);
		
		
		return "correct.jsp";
	}

	
//----------------------------ANSWER WRONG-----------------------------
	@GetMapping("/quest/wrong/{id}")
	public String rendWrong(@PathVariable("id")Long id, Model model,
			HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		//find and create and instance of the quest
		Question quest = questServ.findQuest(id);
		//create a model of the quest to render on page
		model.addAttribute("quest", quest);
		
		//find the game from session
		Game currentGame = (Game)session.getAttribute("currentGame");
		//pass the current game into the model so the correct.jsp can redirect to the next question
		model.addAttribute("game", currentGame);
		
		return "wrong.jsp";
	}

//----------------------------GAME OVER-------------------------------
	@GetMapping("/game/over")
	public String rendOver(Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		//get current game from session
		Game currentGame = (Game)session.getAttribute("currentGame");
		
		//save the currentGame score with the db game score
		Game dBGame = gameServ.findGame(currentGame.getId());
		dBGame.setScore(currentGame.getScore());
		
		//pass current game into model to display score and user info
		model.addAttribute("game", dBGame);
		
		//get all games by category
		List<Game> allGamesByCat = gameServ.scoreByCategory(currentGame.getCategory());
		
		model.addAttribute("allGames", allGamesByCat);
		
		return "gameover.jsp";
	}

	
//-------------------------GET LEADERBOARD----------------------------
	@GetMapping("/leaderboard")
	public String rendLeader(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		List<Category> allCategories = catServ.allCats();
		model.addAttribute("catList", allCategories);
		model.addAttribute("cat", new Category());

		return "leader.jsp";
		
	}

//-------------------------GET LEADERBOARD FOR CAT--------------------
	@GetMapping("/leaderboard/{id}")
	public String leadByCat(@PathVariable("id")Long id, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		Category category = catServ.findCat(id);
		List<Game> topGames = gameServ.scoreByCategory(category);
		
		model.addAttribute("leaders",topGames);
		model.addAttribute("cat", category);
		return "topLeader.jsp";
	}

}
