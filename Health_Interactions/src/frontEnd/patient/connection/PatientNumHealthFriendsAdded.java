package frontEnd.patient.connection;

import beans.Patient;
import dao.oracle.HealthFriendsDAO;
import frontEnd.utility.Utility;

public class PatientNumHealthFriendsAdded {

	public static void drive(Patient p) {
		String date = Utility.getDate("start");
		System.out.println("You have added " + HealthFriendsDAO.healthFriendsAddedSince(p, date) + " Health Friends since " + date);
	}
}
