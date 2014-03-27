import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Patient;
import dao.oracle.AlertsDAO;
import dao.oracle.PatientDAO;


public class AlertDAOTest {
	PatientDAO pdao = new PatientDAO();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testViewAlerts() {
		Patient patient = pdao.getPatient(30);
		String alerts = AlertsDAO.viewNonClearedAlerts(patient);
		System.out.println(alerts);
		patient = pdao.getPatient(30);
		AlertsDAO.clearViewedAlerts(patient);
		alerts = AlertsDAO.viewNonClearedAlerts(patient);
		System.out.println(alerts);
	}

}
