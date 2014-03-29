import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Patient;
import beans.SocialWorker;
import dao.oracle.PatientDAO;
import dao.oracle.SocialWorkersDAO;


public class SocialWorkersDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPossiblePatients() {
		List<SocialWorker> sws = SocialWorkersDAO.getAllSocialWorkers();
		SocialWorker sw = sws.get(0);
		//List<Patient> patients = SocialWorkersDAO.getPossiblePatientsForSocialWorker(sw);
		//System.out.println( patients.size());
	}
	
	@Test
	public void testPatientsForSocialWorker() {
		List<SocialWorker> sws = SocialWorkersDAO.getAllSocialWorkers();
		SocialWorker sw = sws.get(0);
		List<Patient> patients = SocialWorkersDAO.getPatientsForSocialWorker(sw);
		System.out.println( patients.size());
	}

}
