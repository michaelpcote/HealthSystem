package frontEnd.patient.viewData;

import java.util.Scanner;

public class PatientViewAlerts {

	int pid;
	String[] alerts;
	
	PatientViewAlerts(int pid) {
		this.pid = pid;
	}
	
	public void drive() {
		displayAlerts();
	}
	
	private void displayAlerts() {
		//TODO link with cote
		//alerts = ;
		for (int i=0; i<alerts.length; i++) {
			System.out.println(i + " -- " + alerts[i]);
		}		
	}
}
