package frontEnd.healthPro.prescriptions;

import beans.Physician;
import frontEnd.utility.Utility;

/**
 * Drives the UI with the phyician when editing prescriptions.
 * @author cmnelso5
 *
 */
public class ProPrescriptionDriver {

	/**
	 * Drives the UI with the physician when editing prescriptions.
	 * @param p
	 */
	public static void drive(Physician p) {
		System.out.println("What Prescription action would you like to do: ");
		System.out.println("0 -- Edit");
		System.out.println("1 -- View");
		System.out.println("2 -- Prescribe");
		System.out.println("3 -- Go back");
		
		int choice = Utility.getValidChoice(4);
		
		if (choice == 0) {
			ProEditPrescription.drive(p);
		}
		else if (choice == 1) {
			ProViewPrescription.drive(p);
		}
		else if (choice == 2) {
			ProPrescribeMedication.drive(p);
		}
		else if (choice == 3) {
			return;
		}
	}
}
