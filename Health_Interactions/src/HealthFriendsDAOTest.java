import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Patient;
import dao.oracle.HealthFriendsDAO;
import dao.oracle.PatientDAO;


public class HealthFriendsDAOTest {
	PatientDAO pdao = new PatientDAO();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindHealthFriend() {
		Patient patient = pdao.getPatient(1);
		List<Patient> patients = HealthFriendsDAO.findHealthFriend(patient);
		System.out.println("1 patient size: " + patients.size());
		patient = pdao.getPatient(10);
		patients = HealthFriendsDAO.findHealthFriend(patient);
		System.out.println("10 patient size: " + patients.size());
		patient = pdao.getPatient(2);
		patients = HealthFriendsDAO.findHealthFriend(patient);
		System.out.println("2 patient size: " + patients.size());
	}

}
