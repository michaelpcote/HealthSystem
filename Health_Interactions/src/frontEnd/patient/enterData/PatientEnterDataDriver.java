package frontEnd.patient.enterData;

import frontEnd.utility.Utility;
import beans.Patient;

/**
 * Drives the UI for a patient entering data.
 * @author cmnelso5
 *
 */
public class PatientEnterDataDriver {

	/**
	 * Drives the UI for a patient entering data.
	 * @param patient logged in.
	 */
	public static void drive(Patient curr) {
		System.out.println("What would you like to add: ");
		System.out.println("0 -- New observation");
		System.out.println("1 -- New observation type");
		System.out.println("2 -- Go back");
		
		int choice = Utility.getValidChoice(3);
		
		if (choice == 0) {
			PatientEnterData.drive(curr);
		}
		else if (choice == 1) {
			PatientAddObservationType.drive();
		}
		else if (choice == 2) {
			return;
		}
	}
}