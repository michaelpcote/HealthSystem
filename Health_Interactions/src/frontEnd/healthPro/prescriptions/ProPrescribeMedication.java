package frontEnd.healthPro.prescriptions;

import java.util.Scanner;

public class ProPrescribeMedication {

	int did;
	int[] pids; //patients assigned to doctor
	int pid; //patient selected
	
	
	public ProPrescribeMedication(int did) {
		this.did = did;
	}
	
	public void drive() {
		getPatients();
		selectPatient();
		getPrescriptions();
		addPrescription();
	}
	
	private void getPatients() {
		// TODO link with cote
		// pids = ;
	}
	
	private void selectPatient() {
		for (int i=0; i<pids.length; i++) {
			System.out.println(i + " -- " + getName(pids[i]));
		}
		Scanner scan = new Scanner(System.in);
		pid = scan.nextInt();
		scan.close();
	}

	private String getName(int i) {
		// TODO link with cote
		return null;
	}
	
	private void getPrescriptions() {
		// TODO how the fuck do prescriptions even work?
	}
	
	private void addPrescription() {
		// TODO how the fuck do prescriptions even work?
	}
}
