import static org.junit.Assert.*;

import org.junit.Test;

public class TeacherLoginTest {

	//test whether the instructor has entered the correct credentials
	@Test
	public void test() {
		Login object=new Login();
		String Tnam=object.Tname;
		String pass=object.password;
		boolean roleInstructor=true;
		assertEquals("instructor",Tnam);
		assertEquals("asdf123",pass);
		assertEquals(true,roleInstructor);
	}
}
