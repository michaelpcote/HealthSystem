package frontEnd.patient.connection;

public class PatientFindHealthFriendAtRisk {

	int pid;
	int[] friends;
	
	public PatientFindHealthFriendAtRisk(int pid) {
		this.pid = pid;
	}
	
	public void drive() {
		getHealthFriends();
		displayFriends();
	}

	private void getHealthFriends() {
		// TODO link with cote
		// friends = ;
	}
	
	private void displayFriends() {
		for (int i=0; i<friends.length; i++) {
			if (isAtRisk(friends[i])) {
				System.out.println(i + " -- " + getName(i));
			}
		}
	}

	private boolean isAtRisk(int i) {
		// TODO link with cote
		return false;
	}

	private String getName(int i) {
		// TODO link with cote
		return null;
	}
}
