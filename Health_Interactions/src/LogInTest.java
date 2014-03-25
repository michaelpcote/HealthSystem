import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.oracle.LogInDAO;


public class LogInTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogInPatient() {
		String success = null;
		success = LogInDAO.allowLogIn(1, "pw");
		System.out.println(success);
	}
	
	@Test
	public void testLogInPhysician() {
		String success = null;
		success = LogInDAO.allowLogIn(110, "pw");
		System.out.println(success);
	}
	
	@Test
	public void testLogInSocialWorker() {
		String success = null;
		success = LogInDAO.allowLogIn(115, "pw");
		System.out.println(success);
	}
	
	@Test
	public void testLogInFailure() {
		String success = null;
		success = LogInDAO.allowLogIn(37, "pw1");
		assertTrue( success == null );
	}

}
