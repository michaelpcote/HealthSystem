package test.data;

import java.sql.Date;

import dao.oracle.ObservationDAO;
import dao.oracle.PatientConditionsDAO;
import dao.oracle.PatientDAO;
import beans.BloodPressure;
import beans.Contractions;
import beans.Diet;
import beans.Exercise;
import beans.ExerciseTolerance;
import beans.Observation;
import beans.OxygenSaturation;
import beans.Pain;
import beans.Patient;
import beans.Temperature;
import beans.Weight;

public class DummyData {
	
	public static void main(String[] args) {
		//addPatients();
		//addPatientConditions();
		//addPatientDietObservations();
		addPatientWeightObservations();
		addPatientExerciseObservations();
		addPatientBloodPressureObservations();
		addOxygenSaturationObservations();
		addPatientExerciseToleranceObservations();
		addPainObservations();
		addContractionObservations();
		addTemperatureObservations();
		addLotsOfPatientBloodPressureObservations();
	}
	
	@SuppressWarnings("deprecation")
	public static void addPatients() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 0; i < 100; i++ ) {
			patient = new Patient();
			if ( i % 2 == 0 ) {
				patient.setCity("Garner");
			} else {
				patient.setCity("Raleigh");
			}
			if ( i % 3 == 0 ) {
				patient.setPublicStatus("yes");
			} else {
				patient.setPublicStatus("no");
			}
			patient.setPassword(String.valueOf(i));
			patient.setAddress("1409 Kennon Road");
			patient.setState("NC");
			patient.setZip("27529");
			patient.setFname( String.valueOf(i));
			patient.setLname("Cote'");
			patient.setSex(1);
			Date date = Date.valueOf("1979-03-03");
			date.setMonth(-i);
			patient.setDob(date);
			pdao.insertPatient(patient);
			System.out.println(String.valueOf(i) + " inserted");
		}
		System.out.println("Finished");
	}
	
	public static void addPatientConditions() {
		PatientConditionsDAO pcDAO = new PatientConditionsDAO();
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			if ( i % 2 == 0 ) {
				pcDAO.designatePatient( patient, 1);
				System.out.println( String.valueOf(i) + " linked with 1 condition");
			}
			if ( i % 4 == 0 ) {
				pcDAO.designatePatient( patient, 2);
				System.out.println( String.valueOf(i) + " linked with 2 condition");
			}
			if ( i % 5 == 0 ) {
				pcDAO.designatePatient( patient, 3);
				System.out.println( String.valueOf(i) + " linked with 3 condition");
			}
			if ( i % 10 == 0 ) {
				pcDAO.designatePatient( patient, 4);
				System.out.println( String.valueOf(i) + " linked with 4 condition");
			}
		}
	}
	
	
	public static void addPatientDietObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				Diet diet = new Diet("Shrimp", 500);
				ObservationDAO.addDietObservation(patient, o, diet);
				System.out.println( String.valueOf(i) + " diet observation");
			} /*
			if ( i % 4 == 0 ) {
				pcDAO.designatePatient( patient, 2);
				System.out.println( String.valueOf(i) + " linked with 2 condition");
			} */
			else if ( i % 2 == 0 ) {
				Diet diet = new Diet("Shrimp", 30);
				ObservationDAO.addDietObservation(patient, o, diet);
				System.out.println( String.valueOf(i) + " diet observation");
			} /*
			if ( i % 10 == 0 ) {
				pcDAO.designatePatient( patient, 4);
				System.out.println( String.valueOf(i) + " linked with 4 condition");
			} */
		}
	}
	
	public static void addPatientWeightObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				Weight weight = new Weight( 250 - i );
				ObservationDAO.addWeightObservation(patient, o, weight);
				System.out.println( String.valueOf(i) + " weight observation");
			}
		}
	}
	
	public static void addPatientExerciseObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 8 == 0 ) {
				Exercise exercise = new Exercise( 300 - 2*i );
				ObservationDAO.addExerciseObservation(patient, o, exercise);
				System.out.println( String.valueOf(i) + " exercise observation");
			}
			
		}
	}
	
	public static void addPatientBloodPressureObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 8 == 0 ) {
				BloodPressure bp = new BloodPressure( 100 + 2*i, 70 + i);
				ObservationDAO.addBloodPressureObservation(patient, o, bp);
				System.out.println( String.valueOf(i) + " blood pressure observation");
			} else if ( i % 10 == 0 ) {
				BloodPressure bp = new BloodPressure( 100 + 2*i, 70 + i);
				ObservationDAO.addBloodPressureObservation(patient, o, bp);
				System.out.println( String.valueOf(i) + " blood pressure observation");
			}
		}
	}
	
	public static void addLotsOfPatientBloodPressureObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		patient = pdao.getPatient( 8 );
		for ( int i = 1; i <= 20; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			BloodPressure bp = new BloodPressure( 100 + 2*i, 70 + i);
			ObservationDAO.addBloodPressureObservation(patient, o, bp);
			System.out.println( String.valueOf(i) + " blood pressure observation");
		}
	}
	
	public static void addPatientExerciseToleranceObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 20 == 0 ) {
				ExerciseTolerance et = new ExerciseTolerance( 5000 - 10 * i);
				ObservationDAO.addExerciseToleranceObservation(patient, o, et);
				System.out.println( String.valueOf(i) + " exercise tolerance observation");
			} 
		}
	}
	
	public static void addOxygenSaturationObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 30 == 0 ) {
				OxygenSaturation os = new OxygenSaturation( 300 - 2*i );
				ObservationDAO.addOxygenSaturationObservation(patient, o, os);
				System.out.println( String.valueOf(i) + " oxygen saturation observation");
			} 
		}
	}
	
	public static void addPainObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				Pain pain = new Pain( 7 );
				if ( i % 25 == 0 ) {
					pain.setRating(2);
				} else if ( i % 20 == 0 ) {
					pain.setRating( 9 );
				} else if ( i % 15 == 0 ) {
					pain.setRating( 3 );
				} else if ( i % 10 == 0 ) {
					pain.setRating( 5 );
				}
				ObservationDAO.addPainObservation(patient, o, pain );
				System.out.println( String.valueOf(i) + " pain observation");
			} 
		}
	}
	
	public static void addContractionObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				Contractions cont = new Contractions( 5 + ( i % 5 ));
				ObservationDAO.addContractionsObservation(patient, o, cont );
				System.out.println( String.valueOf(i) + " contractions observation");
			} 
		}
	}
	
	public static void addTemperatureObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			patient = pdao.getPatient( i );
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 4 == 0 ) {
				Temperature temp = new Temperature( i );
				ObservationDAO.addTemperatureObservation(patient, o, temp );
				System.out.println( String.valueOf(i) + " temperature observation");
			} 
		}
	}
	
}
