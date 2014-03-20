package frontEnd.patient.enterData;

import java.util.Scanner;

public class PatientAddObservationType {

	private int pid;
	String[] choices;
	String choice;
	
	/**
	 * Constructs the object.
	 */
	PatientAddObservationType(int pid) {
		this.pid = pid;
	}
	
	public void drive() {
		getObsTypeList();
		getObsTypeChoice();
		subscribeToChoice();
	}

	/**
	 * Gets the list of all available observation types, minus the ones that the
	 * patient is already subscribed to.  Sets "choices" to that list.
	 */
	private void getObsTypeList() {
		//TODO link with cote
	}
	
	/**
	 * Displays to the user "choices" and gets input on which one the patient would
	 * like to prescribe to.  Sets "choice".
	 */
	private void getObsTypeChoice() {
		for (int i=0; i<choices.length; i++) {
			System.out.println(i + " -- " + choices[i]);
		}
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		choice = choices[decision];
	}
	
	/**
	 * Subscribes the patient to "choice"
	 */
	private void subscribeToChoice() {
		// TODO link with cote
	}
}
