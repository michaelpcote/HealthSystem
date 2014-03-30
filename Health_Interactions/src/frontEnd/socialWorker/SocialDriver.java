package frontEnd.socialWorker;

import beans.SocialWorker;
import frontEnd.utility.Utility;

/**
 * Drives the UI for a social worker upon logging in.
 * @author cmnelso5
 *
 */
public class SocialDriver {

	/**
	 * Drives the UI for a social worker upon logging in.
	 * @param sw
	 */
	public static void drive(SocialWorker sw) {
		while (true) { 
			System.out.println("What would you like to do: ");
			System.out.println("0 -- Schedule Meeting With Patient");
			System.out.println("1 -- View Meetings With Patients");
			System.out.println("2 -- View Patient Data");
			System.out.println("3 -- Go back");
			
			int choice = Utility.getValidChoice(4);
			
			if (choice == 0) {
				SocialScheduleMeeting.drive(sw);
			}
			else if (choice == 1) {
				SocialViewMeetings.drive(sw);
			}
			else if (choice == 2) {
				SocialViewPatientData.drive(sw);
			}
			else if (choice == 3) {
				return;
			}
		}
	}
}
