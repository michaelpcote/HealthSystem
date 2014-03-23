package frontEnd.patient.connection;

import java.util.Scanner;

public class PatientFindHealthFriend {

	int pid;
	String patientType;
	int[] possibleFriends_pid;
	int friend_pid;
	
	PatientFindHealthFriend(int pid) {
		this.pid = pid;
	}
	
	public void drive() {
		getPatientType();
		getAllPossibleFriends();
		getFriendSelection();
	}

	private void getPatientType() {
		// TODO link with cote
		// patientType = ;
	}
	
	private void getAllPossibleFriends() {
		// TODO link with cote
		// possibleFriends_pid = ;
	}
	
	private void getFriendSelection() {
		System.out.println("Select the health friend you would like to add:\n");
		for (int i=0; i<possibleFriends_pid.length; i++) {
			String name = getFriendName(possibleFriends_pid[i]);
			System.out.println(i + " -- " + name);
		}
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		friend_pid = possibleFriends_pid[choice];
		addHealthFriend();
		scan.close();
	}

	private void addHealthFriend() {
		// TODO link with cote
	}

	private String getFriendName(int i) {
		// TODO link with cote
		return null;
	}
}
