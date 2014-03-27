import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.Patient;
import dao.oracle.AlertsDAO;
import dao.oracle.HealthFriendsDAO;
import dao.oracle.PatientDAO;


public class HealthFriendsDAOTest {
	PatientDAO pdao = new PatientDAO();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindHealthFriend() {
		Patient patient = pdao.getPatient(1);
		List<Patient> patients = HealthFriendsDAO.findHealthFriend(patient);
		System.out.println("1 patient size: " + patients.size());
		patient = pdao.getPatient(10);
		patients = HealthFriendsDAO.findHealthFriend(patient);
		System.out.println("10 patient size: " + patients.size());
		patient = pdao.getPatient(2);
		patients = HealthFriendsDAO.findHealthFriend(patient);
		System.out.println("2 patient size: " + patients.size());
	}
	
	@Test
	public void testFindHealthFriendsAddedSince() {
		Patient patient = pdao.getPatient(2);
		int count = HealthFriendsDAO.healthFriendsAddedSince(patient, "2014-03-01");
		System.out.println("Patient 2 HF Since: " + count);
		
	}
	
	@Test
	public void testCheckHealthFriends() {
		Patient patient = pdao.getPatient(82);
		HealthFriendsDAO.checkHealthFriends(patient);
		System.out.println("Check DONE");
		patient = pdao.getPatient(82);
		HealthFriendsDAO.checkHealthFriends(patient);
		System.out.println("Check DONE");
		
	}
	
	@Test
	public void testFindHealthFriendsSameCity() {
		Patient patient = pdao.getPatient(5);
		List<Patient> patients = HealthFriendsDAO.healthFriendsSameCity(patient);
		for ( int i = 0; i < patients.size(); i++ ) {
			System.out.println("Patient 2 HF same city: " + patients.get(i).getPid());
		}
	}
	
	@Test
	public void testFindHealthFriendsByDate() {
		Patient patient = pdao.getPatient(2);
		List<Patient> patients = HealthFriendsDAO.listHealthFriendsByDate(patient);
		for ( int i = 0; i < patients.size(); i++ ) {
			System.out.println("Patient 2 HF by Date: " + patients.get(i).getPid());
		}
	}
	
	@Test
	public void testFindPatientsWithNoHealthFriendAlerts() {
		List<Patient> patients = HealthFriendsDAO.viewPatientsWithHealthFriendsNoAlerts();
		for ( int i = 0; i < patients.size(); i++ ) {
			System.out.println("Patient with no hf alerts: " + patients.get(i).getPid());
		}
	}
	
	@Test
	public void testAlertNumber() {
		Patient patient = pdao.getPatient(2);
		int num = AlertsDAO.lingeringAlertCount(patient);
		System.out.println("Lingering alerts: " + num );
	}

}
