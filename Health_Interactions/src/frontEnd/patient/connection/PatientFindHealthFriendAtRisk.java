package frontEnd.patient.connection;

import java.util.List;

import dao.oracle.HealthFriendsDAO;
import beans.Patient;

/**
 * Shows to the user all the at risk health friends he has.
 * @author cmnelso5
 */
public class PatientFindHealthFriendAtRisk {

	/**
	 * Shows to the user all the at risk health friends he has.
	 * @param current patient
	 */
	public static void drive(Patient patient) {
		List<Patient> list = HealthFriendsDAO.viewHealthFriendsAtRisk(patient);
		for (int i=0; i<list.size(); i++) {
			Patient curr = list.get(i);
			System.out.println(i + " -- " + curr.getLname() + "," + curr.getFname());
		}
	}
}
