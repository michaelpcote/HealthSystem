package frontEnd.patient.connection;

import java.util.List;

import dao.oracle.HealthFriendsDAO;
import dao.oracle.MessagesDAO;
import frontEnd.utility.Utility;
import beans.Message;
import beans.Patient;
/**
 * Sends a message from the logged in user to the user of their choice.
 * @author cmnelso5
*/
public class PatientSendMessage {

	/**
	 * Sends a message from the logged in user to the user of their choice.
	 * @param patient logged in 
	 */
	public static void drive(Patient p) {
		Message m = new Message();
		m.setFrom((int) p.getPid());
		m.setTo(getTo(p));
		if (m.getTo() == -1) {
			System.out.println("Sorry, but you have no friends to send a message to. ");
			return;
		}
		m.setMessage(getMessage());
		
		updateDB(m);
	}

	/**
	 * Gets the message from the user that they wish to send.
	 * @return message
	 */
	private static String getMessage() {
		System.out.println("Enter the message you would like to send: ");
		return Utility.getInput();
	}

	/**
	 * Gets the patient to send the message to, or returns -1 if they don't have any 
	 * friends.
	 * @param patient sending message
	 * @return pid of patient to send to or null if they don't have frinends.
	 */
	private static int getTo(Patient p) {
		List<Patient> list = HealthFriendsDAO.listHealthFriendsByDate(p);
		if (list.size() == 0) {
			return -1;
		}
		System.out.println("Select the Health Friend to send a message to:");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname() + " " + list.get(i).getPid());
		}
		int choice = Utility.getValidChoice(list.size());
		return (int) list.get(choice).getPid();
	}

	private static void updateDB(Message m) {
		MessagesDAO.sendMessageTo(m);
	}
}
