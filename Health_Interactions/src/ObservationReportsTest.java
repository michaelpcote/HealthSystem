import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.BloodPressure;
import beans.Diet;
import beans.Exercise;
import beans.ExerciseTolerance;
import beans.ObservationTypes;
import beans.Patient;
import beans.Weight;
import dao.oracle.ObservationReportsDAO;
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
		int diet = ObservationReportsDAO.dietAverageAmount(1, 3);
		System.out.println( diet );
	}
	
	@Test
	public void testWeightAverageAmount() {
		int weight = ObservationReportsDAO.weightAverage(1, 2, 3, 4);
		System.out.println( weight );
	}
	
	@Test
	public void testHighestBloodPressure() {
		List<Patient> patients = ObservationReportsDAO.highestBloodPressure(1, 2, 3, 4);
		System.out.println( patients.size() );
	}
	
	@Test
	public void testLowestWeightForPatients() {
		List<Patient> patients = ObservationReportsDAO.lowestWeightPatients(1, 2, 3, 4);
		System.out.println( patients.size() );
		System.out.println( patients.get(0).getPid());
	}
	
	@Test
	public void testLowestWeight() {
		int weight = ObservationReportsDAO.lowestWeight(1, 2, 3, 4);
		System.out.println( weight );
	}
	
	@Test
	public void testHighesetWeight() {
		int weight = ObservationReportsDAO.highestWeight(1, 2, 3, 4);
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
		List<Diet> diets = ObservationReportsDAO.getDietObservationsBetween( patient, diet, "2000-01-01", "2015-01-01" );
		System.out.println( diets.size());
	}
	
	@Test
	public void testGetWeightObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(5);
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		ObservationTypes weight = ot.get(1);
		List<Weight> weights = ObservationReportsDAO.getWeightObservationsBetween( patient, weight, "2000-01-01", "2015-01-01" );
		System.out.println( "Weight "+ weights.size());
	}
	
	@Test
	public void testGetExerciseObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(8);
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		ObservationTypes exercise_type = ot.get(2);
		List<Exercise> exer = ObservationReportsDAO.getExerciseObservationsBetween( patient, exercise_type, "2000-01-01", "2015-01-01" );
		System.out.println( "Exercise "+ exer.size());
	}
	
	@Test
	public void testGetBloodPressureObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(8);
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		ObservationTypes exercise_type = ot.get(3);
		List<BloodPressure> bp = ObservationReportsDAO.getBloodPressureObservationsBetween( patient, exercise_type, "2000-01-01", "2015-01-01" );
		System.out.println( "BloodPressure "+ bp.size());
	}
	
	@Test
	public void testGetExerciseToleranceObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(20);
		List<ObservationTypes> ot = ObservationTypesDAO.getAllObservationTypes();
		ObservationTypes exercise_type = ot.get(4);
		List<ExerciseTolerance> et = ObservationReportsDAO.getExerciseToleranceObservationsBetween( patient, exercise_type, "2000-01-01", "2015-01-01" );
		System.out.println( "ET "+ et.size());
	}

}
