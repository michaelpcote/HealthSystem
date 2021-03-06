package frontEnd.patient.connection;

import java.util.List;

import dao.oracle.HealthFriendsDAO;
import dao.oracle.MessagesDAO;
import frontEnd.utility.Utility;
import beans.Message;
import beans.Patient;

/**
 * Patient views messages.
 * @author cmnelso5
 *
 */
public class PatientViewMessages {

	/**
	 * Patient views messages.
	 * @param patient logged in
	 */
	public static void drive(Patient p) {
		HealthFriendsDAO.checkHealthFriends(p);
		List<Message> list;
		System.out.println("Enter yes to view only unread messages:");
		if (Utility.getInput().toLowerCase().startsWith("y")) {
			list = MessagesDAO.viewUnreadMessagesForPatient(p);
			if (list.size() == 0) {
				System.out.println("You have no unread messages. ");
				return;
			}
		}
		else {
			list = MessagesDAO.viewAllMessagesForPatient(p);
			if (list.size() == 0) {
				System.out.println("You have no messages. ");
				return;
			}
		}
		for (int i=0; i<list.size(); i++) {
			Message m = list.get(i);
			System.out.println("Message #" + i);
			System.out.println("From:  " + m.getFromLastName() + ", " + m.getFromFirstName());
			System.out.println("Message:  "+m.getMessage());
			System.out.println();
		}
	}
}
