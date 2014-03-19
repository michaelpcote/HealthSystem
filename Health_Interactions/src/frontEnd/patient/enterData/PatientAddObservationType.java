package frontEnd.patient.enterData;

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
	 * Gets the list of all available observation types, minus the ones that this
	 * patient is already subscribed to.  Sets "choices" to that list.
	 */
	private void getObsTypeList() {
		//TODO 
	}
	
	/**
	 * Displays to the user "choices" and gets input on which one the patient would
	 * like to prescribe to.
	 */
	private void getObsTypeChoice() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Subscribes the patient to "choice"
	 */
	private void subscribeToChoice() {
		// TODO 
	}
}
