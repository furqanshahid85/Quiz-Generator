import static org.junit.Assert.*;

import org.junit.Test;

public class CreateQuizTest {
// this test checks whether the instructor has entered the quiz title, description,
	//total marks and time 
	@Test
	public void test() {
		CreateQuiz obj= new CreateQuiz();
		obj.titleField.setText("title");
		obj.descriptionField.setText("description");
		obj.quizMarksField.setText("10");
		obj.quizTimeField.setText("5mins");
		
		assertEquals("title",obj.titleField.getText());
		assertEquals("description",obj.descriptionField.getText());
		assertEquals("10",obj.quizMarksField.getText());
		assertEquals("5mins",obj.quizTimeField.getText());
	}

}
