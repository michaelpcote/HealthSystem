package frontEnd.healthPro;

import java.util.Scanner;

public class ProassignSocialWorker {

	int pid;
	int[] social_pids;
	int social_choice;
	int[] patient_pids;
	int patient_choice;
	
	public ProassignSocialWorker(int pid) {
		this.pid = pid;
	}
	
	public void drive() {
		getSocialWorkers();
		getSocialChoice();
		getPatients();
		getPatientChoice();
		update();
	}

	private void getSocialWorkers() {
		// TODO link with cote
		// workers = ;
	}
	
	private void getSocialChoice() {
		System.out.println("Select the social worker assign:");
		for (int i=0; i<social_pids.length; i++) {
			System.out.println(i + " -- " + getSocialName(social_pids[i]));
		}
		Scanner scan = new Scanner(System.in);
		social_choice = scan.nextInt();
		scan.close();
	}
	
	private String getSocialName(int social_id) {
		// TODO link with cote
		return null;
	}
	
	private void getPatients() {
		// TODO link with cote
		// patients_pid = ;
	}
	
	private void getPatientChoice() {
		System.out.println("Select the patient to assign " + getSocialName(social_choice) + " to:");
		for (int i=0; i<patient_pids.length; i++) {
			System.out.println(i + " -- " + getPatientName(i));
		}
		Scanner scan = new Scanner(System.in);
		patient_choice = scan.nextInt();
		scan.close();
	}

	private String getPatientName(int i) {
		// TODO link with cote
		return null;
	}
	
	private void update() {
		// TODO link with cote
	}
}
