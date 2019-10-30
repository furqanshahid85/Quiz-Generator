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
@Table(name = "quiz_questions") // name of the table
@Inheritance(strategy = InheritanceType.JOINED)
public class QuizQuestions { // class name
	// attributes
	
	private int quizId;

	@Id
	@GeneratedValue
	@Column(name = "question_id", unique = true) // column name
	@NotNull
	private int questionId;

	@Column(name = "question_type") // column name
	@NotNull
	private String questionType;
	
	@Column(name = "question_statement") // column name
	@NotNull
	private String questionStatement;
	
	@Column(name = "question_optA")
	private String questionOptA;
	
	@Column(name = "question_optB")
	private String questionOptB; // attributes
	
	@Column(name = "question_optC")
	private String questionOptC;
	
	@Column(name = "question_optD")
	private String questionOptD;
	
	@Column(name = "correct_ans")
	private String correctAnswer;
	
	
	/*@Column(name = "questionmarks") // column name
	private int questionmarks;
*/
	
	// no arggument Constructor
	public QuizQuestions() {
	}

	// parameterized constructor
	public QuizQuestions(int questionid, String statement, String optA, String optB, String optC,
			String optD, String correctAns) {
		
		this.questionId = questionid;
		this.questionStatement = statement;
		this.questionOptA = optA;
		this.questionOptB = optB;
		this.questionOptC = optC;
		this.questionOptD = optD;
		this.correctAnswer = correctAns;
	//	this.questionmarks = questionmarks;
		
	}
	
	
	//getters and setters
	
	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionStatement() {
		return questionStatement;
	}

	public void setQuestionStatement(String questionStatement) {
		this.questionStatement = questionStatement;
	}

	public String getQuestionOptA() {
		return questionOptA;
	}

	public void setQuestionOptA(String questionOptA) {
		this.questionOptA = questionOptA;
	}

	public String getQuestionOptB() {
		return questionOptB;
	}

	public void setQuestionOptB(String questionOptB) {
		this.questionOptB = questionOptB;
	}

	public String getQuestionOptC() {
		return questionOptC;
	}

	public void setQuestionOptC(String questionOptC) {
		this.questionOptC = questionOptC;
	}

	public String getQuestionOptD() {
		return questionOptD;
	}

	public void setQuestionOptD(String questionOptD) {
		this.questionOptD = questionOptD;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}
