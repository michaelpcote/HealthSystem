import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Diet;
import beans.Exercise;
import beans.ObservationTypes;
import beans.Patient;
import beans.Weight;
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
	public void testWeightAverageAmount() {
		int weight = ObservationReports.weightAverage(1, 2, 3, 4);
		System.out.println( weight );
	}
	
	@Test
	public void testHighestBloodPressure() {
		List<Patient> patients = ObservationReports.highestBloodPressure(1, 2, 3, 4);
		System.out.println( patients.size() );
	}
	
	@Test
	public void testLowestWeightForPatients() {
		List<Patient> patients = ObservationReports.lowestWeightPatients(1, 2, 3, 4);
		System.out.println( patients.size() );
		System.out.println( patients.get(0).getPid());
	}
	
	@Test
	public void testLowestWeight() {
		int weight = ObservationReports.lowestWeight(1, 2, 3, 4);
		System.out.println( weight );
	}
	
	@Test
	public void testHighesetWeight() {
		int weight = ObservationReports.highestWeight(1, 2, 3, 4);
		System.out.println( weight );
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
	
	@Test
	public void testGetWeightObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(5);
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		ObservationTypes weight = ot.get(1);
		List<Weight> weights = ObservationReports.getWeightObservationsBetween( patient, weight, "2000-01-01", "2015-01-01" );
		System.out.println( "Weight "+ weights.size());
	}
	
	@Test
	public void testGetExerciseObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(8);
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		ObservationTypes exercise_type = ot.get(2);
		List<Exercise> exer = ObservationReports.getExerciseObservationsBetween( patient, exercise_type, "2000-01-01", "2015-01-01" );
		System.out.println( "Exercise "+ exer.size());
	}

}
