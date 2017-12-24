package quizzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import quizzer.dao.QuizCredentialsDao;
import quizzer.dao.QuizQuestionsDao;
import quizzer.dao.RegisteredUsersDao;
import quizzer.dao.StudentScoreDao;
import quizzer.model.QuizCredentials;
import quizzer.model.QuizQuestions;
import quizzer.model.RegisteredUsers;

import java.util.List;

import javax.validation.Valid;

@RestController
public class AppRestController {

	@Autowired
	private RegisteredUsersDao userDao;

	@Autowired
	private QuizCredentialsDao quizdao;

	@Autowired
	private QuizQuestionsDao questionsdao;

	@Autowired
	private StudentScoreDao resultsdao;

	// this is the home page which asks for user credentials
	@RequestMapping("/home")
	public String userLogin(Model model) {
		System.out.println("Requeted Home ");
		return "home"; // this page is loaded
	}

	// after login this page loads. here the student can view all the quizes and
	// select one for attempt
	// teacher can create new quizes

	@RequestMapping("/userLoggedIn")
	public String getUser(@RequestParam("userEmail") String userName,
			@RequestParam("userPassword") String userPassword,
			@RequestParam("userRole") String userRole) {
		try {
		RegisteredUsers getUser = userDao.findByUserName(userName);// searches for the user and stores it in the
		System.out.println(userName+" "+userPassword+" "+userRole);
		System.out.println(getUser.getUserRole());
		if (userPassword.equals(getUser.getUserPassword())){
			if(userRole.equals(getUser.getUserRole())){
				if(userRole.equals("teacher"))
					return "T";
				else
				{
					System.out.println("Student Logged");
					return "S";
				}
			}

		} else
			return "error password incorrect"+getUser.getUserRole();
		}catch(Exception E) {
			return "user does not exists";
		}
		return "";
	}

	// this page loads if teacher clicks on create new quiz link. this page takes
	// the quiz credentials
	
	
	@RequestMapping("/createNewQuiz")
	public int quiz(@RequestParam("quizTitle") String quizTitle,
			@RequestParam("quizDes") String quizDes,
			@RequestParam("quizMarks") String quizMarks,
			@RequestParam("quizTime") String quizTime) {
		
		
		QuizCredentials newQuiz=new QuizCredentials();
		newQuiz.setQuizTitle(quizTitle);
		newQuiz.setQuizDescription(quizDes);
		newQuiz.setQuizMarks(quizMarks);
		newQuiz.setQuizTime(quizTime);
		
		quizdao.save(newQuiz);
		return newQuiz.getQuizId();
	}

	
	// this page allows the user to add question to the newly created quiz
	@RequestMapping("addQuizQuestions")

	public int quizQuestions(@RequestParam("quizId") String quizID,
			@RequestParam("quizQuestion") String quizQue,
			@RequestParam("questionType") String questionType,
			@RequestParam("optA") String optA,
			@RequestParam("optB") String optB,
			@RequestParam("optC") String optC,
			@RequestParam("optD") String optD,
			@RequestParam("correctAns") String correctAns) {
		
		
		int quiz_ID=Integer.parseInt(quizID);
		
		QuizCredentials quizCredentials = quizdao.findByQuizId(quiz_ID);
		List<QuizQuestions> lq = quizCredentials.getQuestions();
		
		QuizQuestions newQuestion=new QuizQuestions();
		
		newQuestion.setQuizId(quiz_ID);
		newQuestion.setQuestionStatement(quizQue);
		newQuestion.setQuestionType(questionType);
		newQuestion.setQuestionOptA(optA);
		newQuestion.setQuestionOptB(optB);
		newQuestion.setQuestionOptC(optC);
		newQuestion.setQuestionOptD(optD);
		newQuestion.setCorrectAnswer(correctAns);	
		lq.add(newQuestion);
		quizCredentials.setQuestions(lq);
		questionsdao.save(newQuestion);
		quizdao.save(quizCredentials);
		return newQuestion.getQuizId();
	}

}
