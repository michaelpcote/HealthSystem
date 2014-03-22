import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.ObservationType;
import beans.Patient;
import dao.oracle.ObservationReportsDAO;
import dao.oracle.ObservationTypeDAO;
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
		System.out.println( "Diet: " + diet );
	}
	
	@Test
	public void testWeightAverageAmount() {
		int weight = ObservationReportsDAO.weightAverage(1, 2, 3, 4);
		System.out.println("Weight: "+ weight );
	}
	
	@Test
	public void testHighestBloodPressure() {
		List<Patient> patients = ObservationReportsDAO.highestBloodPressure(1, 2, 3, 4);
		System.out.println( "High BP: "+patients.size() );
	}
	
	@Test
	public void testLowestWeightForPatients() {
		List<Patient> patients = ObservationReportsDAO.lowestWeightPatients(1, 2, 3, 4);
		System.out.println( "Lowest Weight for patients: "+patients.size() );
		System.out.println( "LW Patient: "+ patients.get(0).getPid());
	}
	
	@Test
	public void testLowestWeight() {
		int weight = ObservationReportsDAO.lowestWeight(1, 2, 3, 4);
		System.out.println( "THe lowest weight is: "+ weight );
	}
	
	@Test
	public void testHighesetWeight() {
		int weight = ObservationReportsDAO.highestWeight(1, 2, 3, 4);
		System.out.println("THe highest weight is: "+ weight );
	}
	
	@Test
	public void testGetObservationTypes() {
		List<ObservationType> ot = ObservationTypeDAO.getAllObservationTypes();
		System.out.println("Ob number: " + ot.size() );
		assertTrue( ot.size() == 10 );
	}
	@Test
	public void testGetDietObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(2);
		List<ObservationType> ot = ObservationTypeDAO.getAllObservationTypes();
		ObservationType diet = ot.get(0);
		String diets = ObservationReportsDAO.getObservationsBetween( patient, diet, "2000-01-01", "2015-01-01" );
		String d[] = diets.split(",");
		for ( int i = 0; i < d.length; i++ ) {
			System.out.println( "Diet Test: " + d[i]);
		}
		
	}
	
	
	@Test
	public void testGetWeightObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(5);
		List<ObservationType> ot = ObservationTypeDAO.getAllObservationTypes();
		ObservationType weight = ot.get(1);
		String weights = ObservationReportsDAO.getObservationsBetween( patient, weight, "2000-01-01", "2015-01-01" );
		String w[] = weights.split(",");
		for ( int i = 0; i < w.length; i++ ) {
			System.out.println( "Weight Test:"+ w[i]);
		}
		
	}
	
	/*
	@Test
	public void testGetExerciseObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(8);
		List<ObservationType> ot = ObservationTypeDAO.getAllObservationTypes();
		ObservationType exercise_type = ot.get(2);
		List<Exercise> exer = ObservationReportsDAO.getExerciseObservationsBetween( patient, exercise_type, "2000-01-01", "2015-01-01" );
		System.out.println( "Exercise "+ exer.size());
	}
	
	@Test
	public void testGetBloodPressureObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(8);
		List<ObservationType> ot = ObservationTypeDAO.getAllObservationTypes();
		ObservationType exercise_type = ot.get(3);
		List<BloodPressure> bp = ObservationReportsDAO.getBloodPressureObservationsBetween( patient, exercise_type, "2000-01-01", "2015-01-01" );
		System.out.println( "BloodPressure "+ bp.size());
	}
	
	@Test
	public void testGetExerciseToleranceObservationsBetween() {
		Patient patient = new Patient();
		PatientDAO pdao = new PatientDAO();
		patient = pdao.getPatient(20);
		List<ObservationType> ot = ObservationTypeDAO.getAllObservationTypes();
		ObservationType exercise_type = ot.get(4);
		List<ExerciseTolerance> et = ObservationReportsDAO.getExerciseToleranceObservationsBetween( patient, exercise_type, "2000-01-01", "2015-01-01" );
		System.out.println( "ET "+ et.size());
	} */

}
