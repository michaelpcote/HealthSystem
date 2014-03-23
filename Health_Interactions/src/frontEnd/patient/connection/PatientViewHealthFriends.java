package frontEnd.patient.connection;

public class PatientViewHealthFriends {

	int pid;
	int friends[];
	
	public PatientViewHealthFriends(int pid) {
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
				System.out.println(i + " -- " + getName(i));
		}
	}
	
	private String getName(int i) {
		// TODO link with cote
		return null;
	}
}
