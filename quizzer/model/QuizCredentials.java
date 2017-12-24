package quizzer.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "quiz_credentials")                        //name of the table
@Inheritance(strategy=InheritanceType.JOINED)
public class QuizCredentials {                       //class name
  
//attributes
    @Id
    @GeneratedValue
    @Column(name = "quiz_id" , unique = true)
    @NotNull
	private int quizId;

    
    @Column(name = "quiz_title")
    @NotNull
    private String quizTitle;
    
    @Column(name = "quiz_description")
    @NotNull
    private String quizDescription;
    
    
    @Column(name = "quiz_marks")
    @NotNull
    private String quizMarks;
    
    @Column(name = "quiz_Time")
    @NotNull
    private String quizTime;
    
    private int teacherId;//foreign key
    @OneToMany
    private List<QuizQuestions> questions;
    
    //private Users userId; 
 
	public List<QuizQuestions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuizQuestions> questions) {
		this.questions = questions;
	}

	// no-arg Constructor
    public QuizCredentials(){}
    
    //parametrized constructor
    
    public QuizCredentials(int quizId, String quizTitle, String quizDescription, String quizMarks, String quizTime,
			int teacherId) {
    	
		this.quizId = quizId;
		this.quizTitle = quizTitle;
		this.quizDescription = quizDescription;
		this.quizMarks = quizMarks;
		this.quizTime = quizTime;
		this.teacherId = teacherId;
	}

    
    //getters and setters
	public int getQuizId() {
		return quizId;
	}
	
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	public String getQuizDescription() {
		return quizDescription;
	}
	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}
	public String getQuizMarks() {
		return quizMarks;
	}
	public void setQuizMarks(String quizMarks) {
		this.quizMarks = quizMarks;
	}
	public String getQuizTime() {
		return quizTime;
	}
	public void setQuizTime(String quizTime) {
		this.quizTime = quizTime;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	
	/*
	public Users getUserId() {
		return userId;
	}
	public void setUserId(Users userId) {
		this.userId = userId;
	}*/
 
   
}
