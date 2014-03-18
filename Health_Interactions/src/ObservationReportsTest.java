import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Diet;
import beans.ObservationTypes;
import beans.Patient;
import dao.oracle.ObservationReports;
import dao.oracle.ObservationTypesDAO;
import dao.oracle.PatientDAO;


public class ObservationReportsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDietAverageAmount() {
		int diet = ObservationReports.dietAverageAmount(1, 3);
		System.out.println( diet );
	}
	
	@Test
	public void testGetObservationTypes() {
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		assertTrue( ot.size() == 10 );
	}
	@Test
	public void testGetDietObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(2);
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		ObservationTypes diet = ot.get(0);
		List<Diet> diets = ObservationReports.getDietObservationsBetween( patient, diet, "2000-01-01", "2015-01-01" );
		System.out.println( diets.size());
	}

}
