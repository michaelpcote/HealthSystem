package frontEnd.healthPro;

import java.util.Scanner;

public class ProViewPatientInfo {

	int did;
	int[] patients_pid;
	int choice_pid;
	
	public ProViewPatientInfo(int did) {
		this.did = did;
	}
	
	public void drive() {
		getPatients();
		getChoice();
		displayPatientInfo();		
	}
	
	private void getPatients() {
		// TODO link with cote
		// patients = ;
	}
	
	private void getChoice() {
		for (int i=0; i<patients_pid.length; i++) {
			System.out.println(i + " -- " + getName(patients_pid[i]));
		}
		Scanner scan = new Scanner(System.in);
		choice_pid = scan.nextInt();
	}

	private String getName(int i) {
		// TODO link with cote
		return null;
	}
	
	private void displayPatientInfo() {
		// TODO link with cote
	}
}
