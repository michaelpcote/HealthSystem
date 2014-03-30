package frontEnd.patient.viewMeeting;

import frontEnd.utility.Utility;
import beans.Patient;

/**
 * Drives the UI for a patient viewing meetings.
 * @author cmnelso5
 *
 */
public class PatientViewMeetingDriver {
	
	/**
	 * Drives the UI for a patient viewing meetings.
	 * @param p
	 */
	public static void drive(Patient p) {
		System.out.println("Which schedule would you like to view: ");
		System.out.println("0 -- Physician Meetings");
		System.out.println("1 -- Social Worker Meeting");
		System.out.println("2 -- Go back.");
		
		int choice = Utility.getValidChoice(2);
		
		if (choice == 0) {
			PatientViewPhysicianMeetings.drive(p);
		}
		else if (choice == 1) {
			PatientViewSocialMeetings.drive(p);
		}
		else if (choice == 2) {
			return;
		}
	}
}
