package frontEnd.patient.connection;

import java.util.List;

import dao.oracle.HealthFriendsDAO;
import beans.Patient;

/**
 * Shows to the user all the health friends a patient has.
 * @param current patient
 */
public class PatientViewHealthFriends {

	/**
	 * Shows to the user all the health friends a patient has.
	 * @param current patient
	 */
	public static void drive(Patient patient) {
		List<Patient> list = HealthFriendsDAO.listHealthFriendsByDate(patient);
		for (int i=0; i<list.size(); i++) {
			Patient curr = list.get(i);
			System.out.println(i + " -- " + curr.getLname() + "," + curr.getFname());
		}
	}
}
