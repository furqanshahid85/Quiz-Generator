package hello;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import hello.dao.QuizQuestionsDao;
import hello.dao.QuizCredentialsDao;
import hello.dao.StudentScoreDao;
import hello.dao.RegisteredUsersDao;

import javax.validation.Valid;

@Controller
public class AppController {

    
    @Autowired
    private RegisteredUsersDao userDao;
    
    @Autowired
    private QuizCredentialsDao quizdao;


    @Autowired
    private QuizQuestionsDao questionsdao;
    
    @Autowired
    private StudentScoreDao resultsdao;

   
    //this is the home page which asks for user credentials
    @RequestMapping("/home")
    public String userLogin(Model model) {
    	return "home"; // this page is loaded
    } 
    
    //after login this page loads. here the student can view all the quizes and select one for attempt
    // teacher can create new quizes
    
    @RequestMapping("/userLoggedIn")
    public String getUserAndQuiz(@RequestParam("name") String name ,Model model){
    	
    	List<RegisteredUsers> getUserObj = userDao.findByUserName(name);//searches for the user and stores it in the getUserObj
        model.addAttribute("users" , getUserObj);//maps getUserObj to "user"->it is used in html page to access the current user attributes
        
        List<QuizCredentials> getAllQuizzes = quizdao.findAll();
        model.addAttribute("quiz" , getAllQuizzes);
        
        return  "userLoggedIn"; // this page is loaded
    }
   
    //this page loads if teacher clicks on create new quiz link. this page takes the quiz credentials
    @RequestMapping("/createNewQuiz")
    public String quiz(Model model) {
    model.addAttribute("quiz" , new QuizCredentials());
    return "createNewQuiz";// this page is loaded
    } 
    
    //when teacher submits the quiz credentials they are sent to this page and are stored.
    @RequestMapping(value = "/savequiz" , method = RequestMethod.POST)
    public String saveQuiz(@Valid QuizCredentials quiz , Model model){
    	quizdao.save(quiz);
    	model.addAttribute("quiz" , quiz);
        return "savequiz";
    }
    
    //this page allows the user to add question to the newly created quiz
    @RequestMapping("addQuestion")
    
    public String addNewQuestion(Model model) {
    model.addAttribute("questions" , new QuizQuestions());
    return "addQuestion";
    } 
    
    //this page loads after the teacher has added the questions and saved.
   
    
    
    
    @RequestMapping(value = "/storeQuestions" , method = RequestMethod.POST)
    public String questionsSubmit(@Valid QuizQuestions questions , Model model){
    	questionsdao.save(questions);
    	model.addAttribute("questions" , questions);
        return "storeQuestions";
    }
    
  //displays all the saved quizzes
    
    @RequestMapping("/selectquiz")
    public String getSavedQuiz(Model model){
        List<QuizCredentials> getAllQuiz = quizdao.findAll();
        model.addAttribute("quiz" , getAllQuiz);
        return  "selectquiz";
    }

    //print questions
    /*@RequestMapping("/selectquestions")
    public String Question(Model model){
        List<QuizQuestions> questions = questionsdao.findAll();
        model.addAttribute("questions" , questions);
       
        return  "selectquestions";
        
    }*/
    
  //view all questions in a quiz
    @RequestMapping("/viewQuestions")
    public String QuizQuestions(@RequestParam("quizId") Integer quizid ,Model model){
        List<QuizQuestions> questions = questionsdao.findByQuizId(quizid);
        model.addAttribute("questions" , questions);
        return  "viewQuestions";
    }
    
}
