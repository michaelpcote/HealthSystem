package frontEnd.patient.connection;

import java.util.List;
import java.util.Scanner;

import dao.oracle.HealthFriendsDAO;
import frontEnd.utility.Utility;
import beans.Patient;

/**
 * Displays possible health friends to the patient and asks if they want to add
 * a healthfriend.  If they do, then that health friend is added.
 * @param patient
 */
public class PatientFindHealthFriend {

	/**
	 * Displays possible health friends to the patient and asks if they want to add
	 * a healthfriend.  If they do, then that health friend is added.
	 * @param patient
	 */
	public static void drive(Patient patient) {
		List<Patient> list = HealthFriendsDAO.findHealthFriend(patient);
		for (int i=0; i<list.size(); i++) {
			Patient curr = list.get(i);
			System.out.println(i + " -- " + curr.getLname() + "," + curr.getFname());
		}
		System.out.println("Would you like to add one of these Health Friends (yes/no): ");
		if (Utility.getInput().toLowerCase().startsWith("y")) {
			while (true) {
				System.out.println("Enter the number of the health friend you would like to add: ");
				int choice = Utility.getValidChoice(list.size());
				HealthFriendsDAO.addHealthFriend(patient, list.get(choice));
			}
		}
	}
}
