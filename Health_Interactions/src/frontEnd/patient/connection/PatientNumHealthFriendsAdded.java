package frontEnd.patient.connection;

import beans.Patient;
import dao.oracle.HealthFriendsDAO;
import frontEnd.utility.Utility;

/**
 * Views the number of health friends added.
 * @author cmnelso5
 *
 */
public class PatientNumHealthFriendsAdded {

	/**
	 * Views the number of health friends added since a date.
	 * @param p
	 */
	public static void drive(Patient p) {
		String date = Utility.getDate("start");
		System.out.println("You have added " + HealthFriendsDAO.healthFriendsAddedSince(p, date) + " Health Friends since " + date);
	}
}
