import static org.junit.Assert.*;

import org.junit.Test;

public class StudentLoginTest {
	//test whether the student has entered the correct credentials
	@Test
	public void test() {
		Login object=new Login();
		String Tnam=object.Sname;
		String pass=object.password;
		boolean roleStudent=true;
		assertEquals(true,roleStudent);
		assertEquals("student",Tnam);
		assertEquals("asdf123",pass);
	}
}
