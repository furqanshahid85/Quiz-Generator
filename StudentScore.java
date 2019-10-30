package hello;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "student_score")                        //name of the table
@Inheritance(strategy=InheritanceType.JOINED)
public class StudentScore {                       //class name
                                              //attributes
	@Id
    @GeneratedValue
    @Column(name = "result_id" , unique = true)
	@NotNull
	private int resultId;
    
	//@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "STUDENTID")
   // private RegisteredUsers studentId;                           			//Users object to use for foreign key
    
	//@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "QUIZID")
 //   private QuizCredentials quizId;                             //quiz object to use for foreign key
    
    
    @Column(name = "score")
    @NotNull
    private int score;
    
    // no-arg Constructor
    public StudentScore(){}
    
  //parameterized constructor
    public StudentScore(RegisteredUsers studentid,QuizCredentials quizid,int score){                
    //	this.studentId=studentid;
      //  this.quizId=quizid; 
    	this.score = score;
    }
    
    //getters and setters

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/*
	public RegisteredUsers getStudentId() {
		return studentId;
	}

	public void setStudentId(RegisteredUsers studentId) {
		this.studentId = studentId;
	}

	public QuizCredentials getQuizId() {
		return quizId;
	}

	public void setQuizId(QuizCredentials quizId) {
		this.quizId = quizId;
	}
	
	*/

    
    
    
}
