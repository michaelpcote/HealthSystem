package frontEnd.healthPro;

import frontEnd.healthPro.prescriptions.ProPrescriptionDriver;
import frontEnd.utility.Utility;
import beans.Physician;

/**
 * Driver class for the UI presented to a physician upon logging in.
 * @author cmnelso5
 *
 */
public class ProDriver {

	/**
	 * Drives the interaction.
	 * @param physician logged in
	 */
	public static void drive(Physician p) {
		while (true) {
			System.out.println("What would you like to do: ");
			System.out.println("0 -- Prescription Options");
			System.out.println("1 -- Assign Observation Type to Condition");
			System.out.println("2 -- Assign Social Worker to Patient");
			System.out.println("3 -- Enter New Observation Type");
			System.out.println("4 -- Schedule Meeting With Patient");
			System.out.println("5 -- View Meetings");
			System.out.println("6 -- Aggregate Report Options");
			System.out.println("7 -- View Patient Info");
			System.out.println("8 -- Go back");
			
			int choice = Utility.getValidChoice(9);
			
			if (choice == 0) {
				ProPrescriptionDriver.drive(p);
			}
			else if (choice == 1) {
				ProAssignOtToCondition.drive();
			}
			else if (choice == 2) {
				ProAssignSocialWorker.drive(p);
			}
			else if (choice == 3) {
				ProEnterNewObsType.drive();
			}
			else if (choice == 4) {
				ProScheduleMeeting.drive(p);
			}
			else if (choice == 5) {
				ProViewMeetings.drive(p);
			}
			else if (choice == 6) {
				ProViewAgrrReport.drive();
			}
			else if (choice == 7) {
				ProViewPatientInfo.drive(p);
			}
			else if (choice == 8) {
				return;
			}
		}
	}
}
