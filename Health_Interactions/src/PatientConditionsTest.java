import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.oracle.PatientConditionsDAO;
import beans.Patient;
import beans.PatientConditions;

public class PatientConditionsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllPatientConditions() {
		List<PatientConditions> pcs = PatientConditionsDAO.getAllConditionTypes();
		assertTrue( pcs.size() == 4 );
	}
	
	@Test
	public void testGetAllPatientsWithConditions() {
		List<PatientConditions> pcs = PatientConditionsDAO.getAllConditionTypes();
		List<Patient> patients = PatientConditionsDAO.getAllPatientsWithCondition(pcs.get(0).getCondition());
		assertTrue( patients.size() == 50 );
		patients = PatientConditionsDAO.getAllPatientsWithCondition(pcs.get(1).getCondition());
		assertTrue( patients.size() == 25 );
	}

}
