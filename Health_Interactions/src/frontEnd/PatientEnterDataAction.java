package frontEnd;

import java.util.Hashtable;
import java.util.Scanner;

public class PatientEnterDataAction {

	private int pid;
	private String obsChoice;
	private Hashtable<String, String> values;
	
	/**
	 * Constructs the action object
	 * @param pid of the patient
	 */
	public PatientEnterDataAction(int pid) {
		this.pid = pid;
		values = new Hashtable<String, String>();
	}
	
	/**
	 * Main driver of this class.  Gets the observation type to be added, the parameters
	 * needed to add it, and then adds it to the database.
	 */
	public void drive() {
		getObsTypeChoice();
		enterData();
	}
		

	/**
	 * Presents to the patient the observation types he is tracking, and sets the obsChoice variable
	 * to the string of the observation type selected.  Loops until a valid selection is made.
	 */
	private void getObsTypeChoice() {
		String[] obs_types = getObservationTypes();
		Scanner scan = new Scanner(System.in);

		System.out.println("Select one of the following Observation Types:");
		for (int i = 0; i < obs_types.length; i++) {
			System.out.println(i + " -- " + obs_types[i]);
		}
		int choice;
		while (true) {
			choice = scan.nextInt();
			if (choice < 0 || choice >= obs_types.length) {
				System.out.println("Invalid choice.  Choose again.");
			}
			else {
				break;
			}
		}
		scan.close();
		obsChoice = obs_types[choice];
	}
	
	/**
	 * 
	 */
	private void enterData() {
		String[] fields = getObsFields();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter information for the following fields:");
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i] + ":  ");
			String ans = scan.next();
			//TODO ERROR CHECK ANS AGAINST VALUE
			values.put(fields[i], ans);
		}
		updateDB();
	}


	private void updateDB() {
		// use values to update db using cote method
		// TODO Auto-generated method stub
		
	}

	private String[] getObsFields() {
		// use obschoice and cote method to get fields
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns string array of the observation types the patient is tracking.
	 * @return String[]
	 */
	private String[] getObservationTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	
}