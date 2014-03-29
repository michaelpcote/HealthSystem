package frontEnd.socialWorker;

import beans.SocialWorker;
import frontEnd.utility.Utility;

public class SocialDriver {

	public static void drive(SocialWorker sw) {
		System.out.println("What would you like to do: ");
		System.out.println("0 -- Contact Patient");
		System.out.println("1 -- Schedule Meeting With Patient");
		System.out.println("2 -- View Meetings With Patients");
		System.out.println("3 -- View Patient Data");
		System.out.println("4 -- Go back");
		
		int choice = Utility.getValidChoice(5);
		
		if (choice == 0) {
			//TODO SocialContactPatient.drive(sw)
		}
		else if (choice == 1) {
			SocialScheduleMeeting.drive(sw);
		}
		else if (choice == 2) {
			SocialViewMeetings.drive(sw);
		}
		else if (choice == 3) {
			SocialViewPatientData.drive(sw);
		}
		else if (choice == 4) {
			return;
		}
	}
}
