package test.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.JDBCConnection;
import dao.oracle.HealthFriendsDAO;
import dao.oracle.ObservationTypeDAO;
import dao.oracle.ObservationDAO;
import dao.oracle.PatientConditionsDAO;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;
import beans.Observation;
import beans.ObservationType;
import beans.Patient;
import beans.Physician;
import beans.SocialWorker;

public class DummyData {
	
	public static void main(String[] args) {
	
		addPatients();
		addPhysicians();
		addSocialWorkers();
		assignPatientsToSocialWorker(); 
		addPatientConditions(); 
		addPatientDietObservations(); 
		addPatientWeightObservations();
		addPatientExerciseObservations();
		addPatientBloodPressureObservations();
		addOxygenSaturationObservations();
		addPatientExerciseToleranceObservations();
		addPainObservations();
		addContractionObservations();
		addTemperatureObservations(); 
		addLotsOfPatientBloodPressureObservations();
		createPatientTable();
		addTwoPatientsWithHighDiet();
		addPatientHealthFriends();
		additionalHealthFriends(); 
		moreHealthFriends(); 
		anotherHealthFriends(); 
		createAlertsForPatient();
		createOldAlertForPatient();
	}
	
	private static void createAlertsForPatient() {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(2);
		ObservationType ot = ObservationTypeDAO.getObservationType(7);
		for ( int i = 0; i < 5; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			int pain =  10;
			ObservationDAO.addPatientObservation(p, o, ot, "rating:"+pain);
			System.out.println( String.valueOf(i) + " pain observation");
		}
		
	}
	
	private static void createOldAlertForPatient() {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		String query = "UPDATE alerts a SET a.alert_date = ? WHERE a.oid = 235";
    		Date date = Date.valueOf("2014-01-01");
    		ps = conn.prepareStatement(query);
    		ps.setDate(1, date);
    		ps.execute();
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
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
			patient.setPassword("pw");
			patient.setAddress("1409 Kennon Road");
			patient.setState("NC");
			patient.setZip("27529");
			patient.setFname( String.valueOf(i));
			patient.setLname("Cote");
			patient.setSex(1);
			Date date = Date.valueOf("1979-03-03");
			date.setMonth(-i);
			patient.setDob(date);
			pdao.insertPatient(patient);
			System.out.println(String.valueOf(i) + " inserted");
		}
		System.out.println("Finished");
	}
	
	public static void addPhysicians() {
		Physician phy = new Physician();
		for ( int i = 0; i < 10; i++ ) {
			phy.setClinic("WakeMed");
			phy.setFname("Scooter");
			phy.setLname("Zooey");
			phy.setPw("pw");
			int id = PhysiciansDAO.insertPhysician(phy);
			System.out.println("Physician: "+ id );
		}
	}
	
	public static void addSocialWorkers() {
		SocialWorker sw = new SocialWorker();
		for ( int i = 0; i < 5; i++ ) {
			sw.setFname("Laura");
			sw.setLname("Cote");
			sw.setPw("pw");
			int id = SocialWorkersDAO.insertSocialWorker(sw);
			System.out.println("SocialWorker: "+ id );
		}
	}
	
	public static void assignPatientsToSocialWorker() {
		List<SocialWorker> sws = SocialWorkersDAO.getAllSocialWorkers();
		PatientDAO pdao = new PatientDAO();
		SocialWorker sw = sws.get(0);
		for ( int i = 1; i < 6; i++ ) {
			Patient p = pdao.getPatient(i);
			PhysiciansDAO.assignPatientToSocialWorker(p, sw);
			System.out.println("SW Assigned Patient");
		}
	}
	
	public static void addPatientConditions() {
		PatientConditionsDAO pcDAO = new PatientConditionsDAO();
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		for ( int i = 1; i <= 100; i++ ) {
			
			if ( i % 2 == 0 ) {
				patient = pdao.getPatient( i );
				pcDAO.designatePatient( patient, 1);
				System.out.println( String.valueOf(i) + " linked with 1 condition");
			}
			if ( i % 4 == 0 ) {
				patient = pdao.getPatient( i );
				pcDAO.designatePatient( patient, 2);
				System.out.println( String.valueOf(i) + " linked with 2 condition");
			}
			if ( i % 5 == 0 ) {
				patient = pdao.getPatient( i );
				pcDAO.designatePatient( patient, 3);
				System.out.println( String.valueOf(i) + " linked with 3 condition");
			}
			if ( i % 10 == 0 ) {
				patient = pdao.getPatient( i );
				pcDAO.designatePatient( patient, 4);
				System.out.println( String.valueOf(i) + " linked with 4 condition");
			}
		}
	}
	
	
	public static void addPatientDietObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		int calories = 750;
		ObservationType ot = ObservationTypeDAO.getObservationType(1);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				patient = pdao.getPatient( i );
				ObservationDAO.addPatientObservation(patient, o, ot, "food_type:Shrimp,calories:"+ ( calories + 3*i ));
				System.out.println( String.valueOf(i) + " diet observation");
			} /*
			if ( i % 4 == 0 ) {
				pcDAO.designatePatient( patient, 2);
				System.out.println( String.valueOf(i) + " linked with 2 condition");
			} */
			else if ( i % 2 == 0 ) {
				patient = pdao.getPatient( i );
				ObservationDAO.addPatientObservation(patient, o, ot, "food_type:Shrimp,calories:"+ ( calories + 3*i ));
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
		ObservationType ot = ObservationTypeDAO.getObservationType(2);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				patient = pdao.getPatient( i );
				int weight = 250 - i;
				ObservationDAO.addPatientObservation(patient, o, ot, "weight:"+ weight);
				System.out.println( String.valueOf(i) + " weight observation");
			}
		}
	}
	
	public static void addPatientExerciseObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		ObservationType ot = ObservationTypeDAO.getObservationType(3);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 8 == 0 ) {
				patient = pdao.getPatient( i );
				int exercise = 300 - 2*i;
				ObservationDAO.addPatientObservation(patient, o, ot, "minutes:"+ exercise+",exercise_type:Running");
				System.out.println( String.valueOf(i) + " exercise observation");
			}
			
		}
	}
	
	public static void addPatientBloodPressureObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			int systolic = 100 + 2*i;
			int diastolic = 70+i;
			if ( i % 8 == 0 ) {
				patient = pdao.getPatient( i );
				ObservationDAO.addPatientObservation(patient, o, ot, "systolic:"+ systolic+",diastolic:"+diastolic);
				System.out.println( String.valueOf(i) + " blood pressure observation");
			} else if ( i % 10 == 0 ) {
				patient = pdao.getPatient( i );
				ObservationDAO.addPatientObservation(patient, o, ot, "systolic:"+ systolic+",diastolic:"+diastolic);
				System.out.println( String.valueOf(i) + " blood pressure observation");
			}
		}
	}
	
	public static void addLotsOfPatientBloodPressureObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		patient = pdao.getPatient( 8 );
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		for ( int i = 1; i <= 20; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			int systolic = 100 + 2*i;
			int diastolic = 70+i;
			ObservationDAO.addPatientObservation(patient, o, ot, "systolic:"+ systolic+",diastolic:"+diastolic);
			System.out.println( String.valueOf(i) + " blood pressure observation");
		}
	}
	
	public static void addTwoPatientsWithHighDiet() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		patient = pdao.getPatient( 8 );
		ObservationType ot = ObservationTypeDAO.getObservationType(1);
		Observation o = new Observation();
		Date date = Date.valueOf("2014-05-16");
		o.setDate_Observed(date);
		o.setHours(23);
		o.setMinutes(47);
		patient = pdao.getPatient( 12 );
		ObservationDAO.addPatientObservation(patient, o, ot, "food_type:Shrimp,calories:1576");
		System.out.println( String.valueOf(12) + " diet observation");
		patient = pdao.getPatient( 14 );
		ObservationDAO.addPatientObservation(patient, o, ot, "food_type:Shrimp,calories:1576");
		System.out.println( String.valueOf(14) + " diet observation");
	}
	
	public static void addPatientExerciseToleranceObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		ObservationType ot = ObservationTypeDAO.getObservationType(5);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 20 == 0 ) {
				patient = pdao.getPatient( i );
				int steps =  5000 - 10 * i;
				ObservationDAO.addPatientObservation(patient, o, ot, "steps:"+steps);
				System.out.println( String.valueOf(i) + " exercise tolerance observation");
			} 
		}
	}
	
	public static void addOxygenSaturationObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		ObservationType ot = ObservationTypeDAO.getObservationType(6);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 30 == 0 ) {
				patient = pdao.getPatient( i );
				int percent =  300 - 2*i;
				ObservationDAO.addPatientObservation(patient, o, ot, "percentage:"+percent);
				System.out.println( String.valueOf(i) + " oxygen saturation observation");
			} 
		}
	}
	
	public static void addPainObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		ObservationType ot = ObservationTypeDAO.getObservationType(7);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				patient = pdao.getPatient( i );
				int pain =  7;
				if ( i % 25 == 0 ) {
					pain = 2;
				} else if ( i % 20 == 0 ) {
					pain = 9;
				} else if ( i % 15 == 0 ) {
					pain = 3;
				} else if ( i % 10 == 0 ) {
					pain = 5;
				}
				ObservationDAO.addPatientObservation(patient, o, ot, "rating:"+pain);
				System.out.println( String.valueOf(i) + " pain observation");
			} 
		}
	}
	
	public static void addContractionObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		ObservationType ot = ObservationTypeDAO.getObservationType(9);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 5 == 0 ) {
				patient = pdao.getPatient( i );
				int cont = 5 + ( i % 5 );
				ObservationDAO.addPatientObservation(patient, o, ot, "frequency:"+cont);
				System.out.println( String.valueOf(i) + " contractions observation");
			} 
		}
	}
	
	public static void createPatientTable() {
		ObservationType ot = new ObservationType();
		ot.setAdditional_info("How I am sleeping");
		ot.setValue_choices("sleep:String:Trouble Sleeping,Little Sleep,Moderate Sleep,Fine Sleep");
		ot.setDisplay_name("Sleep Patterns");
		ot.setTable_name("sleep_patterns");
		ot.setOcid(1);
		ot.setColumn_names_types("sleep:String");
		ObservationTypeDAO.addNewObservationType(ot);
	}
	
	public static void addTemperatureObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		ObservationType ot = ObservationTypeDAO.getObservationType(10);
		for ( int i = 1; i <= 100; i++ ) {
			Observation o = new Observation();
			Date date = Date.valueOf("2014-05-16");
			date.setMonth(-i);
			o.setDate_Observed(date);
			o.setHours(23);
			o.setMinutes(47);
			if ( i % 4 == 0 ) {
				patient = pdao.getPatient( i );
				int temp = i;
				ObservationDAO.addPatientObservation(patient, o, ot, "temp:"+i);
				System.out.println( String.valueOf(i) + " temperature observation");
			} 
		}
	}
	
	public static void addPatientHealthFriends() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(2);
		List<Patient> patients = HealthFriendsDAO.findHealthFriend(patient);
		System.out.println("2 patient size: " + patients.size());
		for ( int i = 0; i < patients.size(); i ++ ) {
			if ( i % 4 == 0 ) {
				HealthFriendsDAO.addHealthFriend(patient, patients.get(i));
				System.out.println("Added healthfriend "+ patients.get(i).getPid());
			}
		}
	}
	
	public static void additionalHealthFriends() {
		Connection conn = null;
        PreparedStatement ps = null;
         try {
        	 conn = JDBCConnection.getConnection();
        	 for ( int i = 3; i < 21; i++ ) {
        		 String query = null;
        		 Date date = null;
        		 String d = null;
        		 if ( i < 10 ) {
        			 d = "2014-02-0"+Integer.valueOf(i);
        		 } else {
        			 d = "2014-02-"+Integer.valueOf(i);
        		 }
        		 date = Date.valueOf(d);
        		 query = "INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 2, ?, ? )";
        		 System.out.println(query);
        		 ps = conn.prepareStatement(query);
        		 ps.setInt( 1, i );
        		 ps.setDate( 2, date );
        		 ps.execute();
        	}
         } catch(SQLException e) {
            	e.printStackTrace();
         } finally {
 			JDBCConnection.closeConnection(conn, ps, null);
 		}
		System.out.println("Tables added");
	}
	
	public static void moreHealthFriends() {
		Connection conn = null;
        PreparedStatement ps = null;
         try {
        	 conn = JDBCConnection.getConnection();
        	 String query = null;
        	 Date date = null;
    		 String d = null;
    		 d = "2014-01-03";
    		 date = Date.valueOf(d);
    		 query = "INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 3, 5, ? )";
    		 System.out.println(query);
    		 ps = conn.prepareStatement(query);
    		 ps.setDate( 1, date );
    		 ps.execute();	 
         } catch(SQLException e) {
            	e.printStackTrace();
         } finally {
 			JDBCConnection.closeConnection(conn, ps, null);
 		}
		System.out.println("More health friends added");
	}
	
	public static void anotherHealthFriends() {
		Connection conn = null;
        PreparedStatement ps = null;
         try {
        	 conn = JDBCConnection.getConnection();
        	 String query = null;
        	 Date date = null;
    		 String d = null;
    		 d = "2014-01-03";
    		 date = Date.valueOf(d);
    		 query = "INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 7, 79, ? )";
    		 System.out.println(query);
    		 ps = conn.prepareStatement(query);
    		 ps.setDate( 1, date );
    		 ps.execute();	 
         } catch(SQLException e) {
            	e.printStackTrace();
         } finally {
 			JDBCConnection.closeConnection(conn, ps, null);
 		}
		System.out.println("another More health friends added");
	}
	
	
}
