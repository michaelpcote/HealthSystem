package frontEnd.patient.connection;

import frontEnd.utility.Utility;
import beans.Patient;

/**
 * Drives the UI for a patient viewing connections options.
 * @author cmnelso5
 *
 */
public class PatientConnectionDriver {

	/**
	 * Drives the UI for a patient viewing connection options.
	 * @param p
	 */
	public static void drive(Patient p) {
		System.out.println("What would you like to do: ");
		System.out.println("0 -- View Health Friends");
		System.out.println("1 -- Find New Health Friend");
		System.out.println("2 -- Find Health Friend At Risk");
		System.out.println("3 -- View Messages");
		System.out.println("4 -- View Number Of Health Friends Added Since A Date");
		System.out.println("5 -- Send Health Friend a Message");
		System.out.println("6 -- Find Health Friend in the Same City");
		System.out.println("7 -- Go Back");
		
		int choice = Utility.getValidChoice(8);
		
		if (choice == 0) {
			PatientViewHealthFriends.drive(p);
		}
		else if (choice == 1) {
			PatientFindHealthFriend.drive(p);			
		}
		else if (choice == 2) {
			PatientFindHealthFriendAtRisk.drive(p);
		}
		else if (choice == 3) {
			PatientViewMessages.drive(p);
		}
		else if (choice == 4) {
			PatientNumHealthFriendsAdded.drive(p);
		}
		else if (choice == 5) {
			PatientSendMessage.drive(p);
		}
		else if (choice == 6) {
			PatientViewHealthFriendSameCity.drive(p);
		}
		else if (choice == 7) {
			return;
		}
	}
}
