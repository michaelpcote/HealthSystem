package frontEnd.patient.viewData;

import java.sql.Date;
import java.util.Scanner;

public class PatientViewObservations {

	int pid;
	String obsType;
	Date start;
	Date end;
	String[] observations;
	
	PatientViewObservations(int pid) {
		this.pid = pid;
	}
	
	public void drive() {
		getDatesObsType();
		getObservations();
		displayObservations();
	}

	private void getDatesObsType() {
		//TODO link with cote
		String[] observationTypes = null; // = what
		System.out.println("Select the observation type:");
		for (int i=0; i<observationTypes.length; i++) {
			System.out.println(i + " -- " + observationTypes[i]);
		}
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		obsType = observationTypes[decision];
		
		System.out.println("Enter the start date");
		//TODO date shit
		// start = ;
		
		System.out.println("Enter the end date");
		//TODO date shit
		// end = ;
	}

	private void getObservations() {
		// TODO link with cote
		// observations = ;
		
	}

	private void displayObservations() {
		for (int i=0; i<observations.length; i++) {
			System.out.println(i + " -- " + observations[i]);
		}
	}
	
	
}
