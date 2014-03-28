import static org.junit.Assert.*;

import java.util.ArrayList;
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
		ObservationType ot = ObservationTypeDAO.getObservationType(1);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		String diet = ObservationReportsDAO.averageAmount(ot, list);
		System.out.println( "Diet: " + diet );
	}
	
	@Test
	public void testGetNumberOfObservations() {
		int diet = ObservationReportsDAO.numberOfObservationsByObsType(1);
		System.out.println( "Diet observations: " + diet );
	}
	
	@Test
	public void testGetPatientListHighDiet() {
		ObservationType ot = ObservationTypeDAO.getObservationType(1);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		List<Patient> patients = ObservationReportsDAO.getPatientsWithHighest(ot, list);
		System.out.println( "High Diet #: " + patients.size() );
	}
	
	@Test
	public void testGetNumberOfObservationsByPatient() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(8);
		int diet = ObservationReportsDAO.numberOfObservationsMadeByPatient(patient);
		System.out.println( "Observations for patient: " + diet );
	}
	
	@Test
	public void testViewObservationsForPatient() {
		PatientDAO pdao = new PatientDAO();
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		Patient patient = pdao.getPatient(8);
		String bp = ObservationReportsDAO.viewObservationsForPatient(patient, ot);
		System.out.println( "Observations for patient 8: " + bp );
	}
	
	@Test
	public void testMostOccuringValue() {
		PatientDAO pdao = new PatientDAO();
		ObservationType ot = ObservationTypeDAO.getObservationType(8);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		String popular = ObservationReportsDAO.mostOccurringStringValue(ot, list);
		System.out.println( "Most Popular: "+ popular );
		String least = ObservationReportsDAO.leastOccurringStringValue(ot, list);
		System.out.println( "Least Popular: "+ least );
	}
	
	@Test
	public void testWeightAverageAmount() {
		ObservationType ot = ObservationTypeDAO.getObservationType(2);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		String weight = ObservationReportsDAO.averageAmount(ot, list);
		System.out.println("Weight: "+ weight );
	}
	
	
	
	@Test
	public void tesBloodPressureAverageAmount() {
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		String weight = ObservationReportsDAO.averageAmount(ot, list);
		System.out.println("Blood Pressure Average: "+ weight );
	}
	
	@Test
	public void testHighestBloodPressure() {
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		List<Patient> patients = ObservationReportsDAO.getPatientsWithHighest(ot, list);
		System.out.println( "High BP #: "+patients.size() );
	}
	
	@Test
	public void testLowesetBloodPressure() {
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		List<Patient> patients = ObservationReportsDAO.getPatientsWithLowest(ot, list);
		System.out.println( "Low BP #: "+patients.size() );
	}
	/*
	@Test
	public void testLowestWeightForPatients() {
		List<Patient> patients = ObservationReportsDAO.lowestWeightPatients(1, 2, 3, 4);
		System.out.println( "Lowest Weight for patients: "+patients.size() );
		System.out.println( "LW Patient: "+ patients.get(0).getPid());
	}
	*/
	@Test
	public void testLowestWeight() {
		ObservationType ot = ObservationTypeDAO.getObservationType(2);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		String weight = ObservationReportsDAO.lowestAmount(ot, list);
		System.out.println( "THe lowest weight is: "+ weight );
	}
	
	@Test
	public void testLowestBloodPressure() {
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		String bp = ObservationReportsDAO.lowestAmount(ot, list);
		System.out.println( "Lowest BP: " + bp );
	}
	
	@Test
	public void testHighestBloodPressureAmount() {
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		String bp = ObservationReportsDAO.highestAmount(ot, list);
		System.out.println( "Highest BP: " + bp );
	}
	
	@Test
	public void testHighesetWeight() {
		ObservationType ot = ObservationTypeDAO.getObservationType(2);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		String weight = ObservationReportsDAO.highestAmount(ot, list);
		System.out.println("THe highest weight is: "+ weight );
	}
	
	@Test
	public void testGetObservationTypes() {
		List<ObservationType> ot = ObservationTypeDAO.getAllObservationTypes();
		System.out.println("Ob number: " + ot.size() );
		assertTrue( ot.size() == 11 );
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
