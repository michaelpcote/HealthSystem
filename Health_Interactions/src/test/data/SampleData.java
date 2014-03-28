package test.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.JDBCConnection;
import beans.Observation;
import beans.ObservationType;
import beans.Patient;
import beans.Physician;
import beans.SocialWorker;
import dao.oracle.ObservationDAO;
import dao.oracle.ObservationTypeDAO;
import dao.oracle.PatientConditionsDAO;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;

public class SampleData {

	public static void main(String[] args ) {
		/*addPatients();
		givePatientsConditions();
		addPhysicians();
		addHealthFriends();
		addSheldonObservations();
		addGeorgeObservation();
		addAkaziObservation();
		addNshettyObservations();
		addMTranObservations();
		addSocialWorkers();*/
		additionalObservations();
	}
	
	public static void addPatients() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = new Patient();
		
		//Add Gary George
		patient.setCity("Raleigh");
		patient.setPublicStatus("yes");
		patient.setPassword("geo123");
		patient.setAddress("2806 Conifer Drive");
		patient.setState("NC");
		patient.setZip("27606");
		patient.setFname("Gary");
		patient.setLname("George");
		patient.setSex(1);
		Date date = Date.valueOf("1989-12-12");
		patient.setDob(date);
		int p = pdao.insertPatient(patient);
		System.out.println(String.valueOf(p) + " inserted");
		
		//Add Adnan Kazi
		patient = new Patient();
		patient.setCity("Raleigh");
		patient.setPublicStatus("yes");
		patient.setPassword("kazi123");
		patient.setAddress("1234 Capability Drive");
		patient.setState("NC");
		patient.setZip("27655");
		patient.setFname("Adnan");
		patient.setLname("Kazi");
		patient.setSex(2);
		date = Date.valueOf("1983-12-12");
		patient.setDob(date);
		p = pdao.insertPatient(patient);
		System.out.println(String.valueOf(p) + " inserted");
		
		//Add Neha Shetty
		patient = new Patient();
		patient.setCity("Chapel Hill");
		patient.setPublicStatus("yes");
		patient.setPassword("shetty123");
		patient.setAddress("440 Sullivan Drive");
		patient.setState("NC");
		patient.setZip("27517");
		patient.setFname("Neha");
		patient.setLname("Shetty");
		patient.setSex(2);
		date = Date.valueOf("1974-12-12");
		patient.setDob(date);
		p = pdao.insertPatient(patient);
		System.out.println(String.valueOf(p) + " inserted");
		
		//Add Sheldon Cooper
		patient = new Patient();
		patient.setCity("Raleigh");
		patient.setPublicStatus("yes");
		patient.setPassword("cooper123");
		patient.setAddress("2808 Avent Ferry Road");
		patient.setState("NC");
		patient.setZip("27616");
		patient.setFname("Sheldon");
		patient.setLname("Cooper");
		patient.setSex(1);
		date = Date.valueOf("1981-12-12");
		patient.setDob(date);
		p = pdao.insertPatient(patient);
		System.out.println(String.valueOf(p) + " inserted");
		
		//Add Michael Watson
		patient = new Patient();
		patient.setCity("Raleigh");
		patient.setPublicStatus("yes");
		patient.setPassword("watson123");
		patient.setAddress("2222 Gorman Street");
		patient.setState("NC");
		patient.setZip("27678");
		patient.setFname("Michael");
		patient.setLname("Watson");
		patient.setSex(1);
		date = Date.valueOf("1967-12-12");
		patient.setDob(date);
		p = pdao.insertPatient(patient);
		System.out.println(String.valueOf(p) + " inserted");
		
		//Add Tom Kerr
		patient = new Patient();
		patient.setCity("Durham");
		patient.setPublicStatus("yes");
		patient.setPassword("tkerr123");
		patient.setAddress("1430 Collegeview Ave");
		patient.setState("NC");
		patient.setZip("27701");
		patient.setFname("Tom");
		patient.setLname("Kerr");
		patient.setSex(1);
		date = Date.valueOf("1974-12-12");
		patient.setDob(date);
		p = pdao.insertPatient(patient);
		System.out.println(String.valueOf(p) + " inserted");	
		
		//Add Maya Tran
		patient = new Patient();
		patient.setCity("Chapel Hill");
		patient.setPublicStatus("yes");
		patient.setPassword("tran123");
		patient.setAddress("100 Brown Circle");
		patient.setState("NC");
		patient.setZip("27516");
		patient.setFname("Maya");
		patient.setLname("Tran");
		patient.setSex(1);
		date = Date.valueOf("1977-12-12");
		patient.setDob(date);
		p = pdao.insertPatient(patient);
		System.out.println(String.valueOf(p) + " inserted");	
	}
	
	public static void givePatientsConditions() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(1);
		PatientConditionsDAO.designatePatient( patient, 1);
		patient = pdao.getPatient(2);
		PatientConditionsDAO.designatePatient( patient, 2);
		PatientConditionsDAO.designatePatient( patient, 3);
		patient = pdao.getPatient(3);
		PatientConditionsDAO.designatePatient( patient, 2);
		PatientConditionsDAO.designatePatient( patient, 3);
		patient = pdao.getPatient(4);
		PatientConditionsDAO.designatePatient( patient, 1);
		PatientConditionsDAO.designatePatient( patient, 4);
		patient = pdao.getPatient(5);
		PatientConditionsDAO.designatePatient( patient, 4);
		patient = pdao.getPatient(6);
		PatientConditionsDAO.designatePatient( patient, 2);
		PatientConditionsDAO.designatePatient( patient, 4);
		patient = pdao.getPatient(7);
		PatientConditionsDAO.designatePatient( patient, 3);
	}
	
	public static void addPhysicians() {
		Physician phy = new Physician();
		phy.setClinic("Dayview");
		phy.setFname("Altaf");
		phy.setLname("Hussain");
		phy.setPw("hussain123");
		int id = PhysiciansDAO.insertPhysician(phy);
		System.out.println("Physician: "+ id );
		
		//Add Manu
		phy = new Physician();
		phy.setClinic("Dayview");
		phy.setFname("Manu");
		phy.setLname("Joseph");
		phy.setPw("joseph123");
		id = PhysiciansDAO.insertPhysician(phy);
		System.out.println("Physician: "+ id );
		
		//Add Shane
		phy = new Physician();
		phy.setClinic("Huntington");
		phy.setFname("Shane");
		phy.setLname("Lee");
		phy.setPw("lee123");
		id = PhysiciansDAO.insertPhysician(phy);
		System.out.println("Physician: "+ id );
		
		//Add Shyam
		phy = new Physician();
		phy.setClinic("Huntington");
		phy.setFname("Shyam");
		phy.setLname("Prasad");
		phy.setPw("prasad123");
		id = PhysiciansDAO.insertPhysician(phy);
		System.out.println("Physician: "+ id );
	}
	
	public static void addHealthFriends() {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = JDBCConnection.getConnection();
        	ps = conn.prepareStatement("INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 6, 2, ? )");
        	Date date = Date.valueOf("2013-04-01");
        	ps.setDate(1, date);
        	ps.execute();
        	ps = conn.prepareStatement("INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 6, 5, ? )");
        	date = Date.valueOf("2011-03-04");
        	ps.setDate(1, date);
        	ps.execute();
        	ps = conn.prepareStatement("INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 4, 1, ? )");
        	date = Date.valueOf("2012-10-12");
        	ps.setDate(1, date);
        	ps.execute();
        	ps = conn.prepareStatement("INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 4, 5, ? )");
        	date = Date.valueOf("2013-01-02");
        	ps.setDate(1, date);
        	ps.execute();
        	ps = conn.prepareStatement("INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( 6, 1, ? )");
        	date = Date.valueOf("2011-05-04");
        	ps.setDate(1, date);
        	ps.execute();
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	public static void addSheldonObservations() {
		int index = 1;
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(4);
		ObservationType ot = ObservationTypeDAO.getObservationType(1);
		Observation o = new Observation();
		Date date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(8);
		o.setMinutes(15);
		ObservationDAO.addPatientObservation(patient, o, ot, "food_type:egg;orange;toast;margrine,calories:425");
		System.out.println( String.valueOf(index++) + " diet observation");
		
		ot = ObservationTypeDAO.getObservationType(2);
		o = new Observation();
		date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(8);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "weight:100");
		System.out.println( String.valueOf(index++) + " weight observation");
		
		ot = ObservationTypeDAO.getObservationType(3);
		o = new Observation();
		date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(6);
		o.setMinutes(30);
		ObservationDAO.addPatientObservation(patient, o, ot, "minutes:30,exercise_type:walking");
		System.out.println( String.valueOf(index++) + " exercise observation");
		
		ot = ObservationTypeDAO.getObservationType(8);
		o = new Observation();
		date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(21);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "mood:Neutral");
		System.out.println( String.valueOf(index++) + " mood observation");
		
		
		ot = ObservationTypeDAO.getObservationType(10);
		o = new Observation();
		date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(6);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "temp:98");
		System.out.println( String.valueOf(index++) + " temp observation");
		
		ot = ObservationTypeDAO.getObservationType(5);
		o = new Observation();
		date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(11);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "steps:20");
		System.out.println( String.valueOf(index++) + " temp observation");
		
		ot = ObservationTypeDAO.getObservationType(6);
		o = new Observation();
		date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(10);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "percentage:78");
		System.out.println( String.valueOf(index++) + " oxygen saturation observation");
		
		ot = ObservationTypeDAO.getObservationType(2);
		o = new Observation();
		date = Date.valueOf("2013-04-06");
		o.setDate_Observed(date);
		o.setHours(8);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "weight:102");
		System.out.println( String.valueOf(index++) + " weight observation");
	}
	
	public static void addGeorgeObservation() {
		int index = 9;
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(1);
		ObservationType ot = ObservationTypeDAO.getObservationType(2);
		Observation o = new Observation();
		Date date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(7);
		o.setMinutes(50);
		ObservationDAO.addPatientObservation(patient, o, ot, "weight:150");
		System.out.println( String.valueOf(index++) + " weight observation");
		
		o = new Observation();
		date = Date.valueOf("2013-04-06");
		o.setDate_Observed(date);
		o.setHours(8);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "weight:156");
		System.out.println( String.valueOf(index++) + " weight observation");		
	}
	
	//Add Akazi
	public static void addAkaziObservation() {
		int index = 11;
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(1);
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		Observation o = new Observation();
		Date date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(7);
		o.setMinutes(50);
		ObservationDAO.addPatientObservation(patient, o, ot, "systolic:150,diastolic:96");
		System.out.println( String.valueOf(index++) + " blood pressure observation");
		
		o = new Observation();
		date = Date.valueOf("2013-04-08");
		o.setDate_Observed(date);
		o.setHours(8);
		o.setMinutes(05);
		ObservationDAO.addPatientObservation(patient, o, ot, "systolic:170,diastolic:90");
		System.out.println( String.valueOf(index++) + " blood pressure observation");
	}
	
	//Add Nshetty observations
	public static void addNshettyObservations() {
		int index = 13;
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(3);
		ObservationType ot = ObservationTypeDAO.getObservationType(4);
		Observation o = new Observation();
		Date date = Date.valueOf("2013-04-06");
		o.setDate_Observed(date);
		o.setHours(7);
		o.setMinutes(50);
		ObservationDAO.addPatientObservation(patient, o, ot, "systolic:162,diastolic:110");
		System.out.println( String.valueOf(index++) + " blood pressure observation");
	}
	

	//Add mtran observations
	public static void addMTranObservations() {
		int index = 13;
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(7);
		ObservationType ot = ObservationTypeDAO.getObservationType(7);
		Observation o = new Observation();
		Date date = Date.valueOf("2013-04-06");
		o.setDate_Observed(date);
		o.setHours(13);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "rating:8");
		System.out.println( String.valueOf(index++) + " pain observation");
		
		ot = ObservationTypeDAO.getObservationType(8);
		o = new Observation();
		date = Date.valueOf("2013-04-05");
		o.setDate_Observed(date);
		o.setHours(21);
		o.setMinutes(00);
		ObservationDAO.addPatientObservation(patient, o, ot, "mood:Happy");
		System.out.println( String.valueOf(index++) + " mood observation");
	}
	
	//Add mtran observations
		public static void additionalObservations() {
			int index = 13;
			PatientDAO pdao = new PatientDAO();
			Patient patient = pdao.getPatient(7);
			ObservationType ot = ObservationTypeDAO.getObservationType(7);
			Observation o = new Observation();
			Date date = Date.valueOf("2013-04-06");
			o.setDate_Observed(date);
			o.setHours(13);
			o.setMinutes(00);
			ObservationDAO.addPatientObservation(patient, o, ot, "rating:8");
			System.out.println( String.valueOf(index++) + " pain observation");
			
			ot = ObservationTypeDAO.getObservationType(8);
			o = new Observation();
			date = Date.valueOf("2013-04-05");
			o.setDate_Observed(date);
			o.setHours(21);
			o.setMinutes(00);
			ObservationDAO.addPatientObservation(patient, o, ot, "mood:Happy");
			System.out.println( String.valueOf(index++) + " mood observation");
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
	
}
