import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.oracle.PatientDAO;
import beans.Patient;


public class PatientDAOTest {
	
	private Patient patient = null;
	
	@Before
	public void setUp() throws Exception {
		patient = new Patient();
		patient.setAddress("1409 Kennon Road");
		patient.setCity("Garner");
		patient.setState("NC");
		patient.setFname("Michael");
		patient.setLname("Cote'");
		patient.setSex("Male");
		patient.setDob("1979-03-03");
		patient.setPublicStatus("yes");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertPatient() {
		PatientDAO pdao = new PatientDAO();
		pdao.insertPatient(patient);
	}

}
