package frontEnd.patient.viewData;

import frontEnd.utility.Utility;
import beans.Patient;

/**
 * Drives the UI for a patient viewing data.
 * @author nelc
 *
 */
public class PatientViewDataDriver {

	/**
	 * Drives the UI for a patient viewing data.
	 * @param curr
	 */
	public static void drive(Patient curr) {
		System.out.println("What would you like to do: ");
		System.out.println("0 -- View Alerts");
		System.out.println("1 -- View Observations");
		System.out.println("2 -- Go back");
		
		int choice = Utility.getValidChoice(3);
		
		if (choice == 0) {
			PatientViewAlerts.drive(curr);
		} else if (choice == 1) {
			PatientViewObservations.drive(curr);
		} else if (choice == 2) {
			return;
		}
	}
}
