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
	public void testLogInSuccess() {
		boolean success = false;
		success = LogInDAO.allowLogIn(1, "0");
		assertTrue( success);
	}
	
	@Test
	public void testLogInFailure() {
		boolean success = false;
		success = LogInDAO.allowLogIn(1, "0/");
		assertFalse( success);
	}

}
