package frontEnd.healthPro.prescriptions;

import beans.Physician;
import frontEnd.utility.Utility;

public class ProPrescriptionDriver {

	public static void drive(Physician p) {
		System.out.println("What Prescription action would you like to do: ");
		System.out.println("0 -- Edit");
		System.out.println("1 -- View");
		System.out.println("2 -- Prescribe");
		System.out.println("3 -- Go back");
		
		int choice = Utility.getValidChoice(4);
		
		if (choice == 0) {
			ProEditPrescription.drive();
		}
		else if (choice == 1) {
			ProViewPrescription.drive();
		}
		else if (choice == 2) {
			ProPrescribeMedication.drive();
		}
		else if (choice == 3) {
			return;
		}
	}
}
