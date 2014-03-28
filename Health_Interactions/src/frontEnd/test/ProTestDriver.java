package frontEnd.test;

import beans.Patient;
import beans.SocialWorker;
import dao.oracle.PatientDAO;
import frontEnd.healthPro.ProViewAgrrReport;
import frontEnd.healthPro.ProViewPatientInfo;
import frontEnd.healthPro.ProassignSocialWorker;
import frontEnd.healthPro.prescriptions.ProPrescribeMedication;
import frontEnd.healthPro.prescriptions.ProViewPrescription;
import frontEnd.socialWorker.SocialViewPatientData;

public class ProTestDriver {

	public static void main(String[] args) {
		//viewPatientInfo();
		viewPrescription();
		//addPrescription();
		//assignSocial();
		//aggrReports();
	}
	
	private static void aggrReports() {
		while (true) {ProViewAgrrReport.drive();}
	}

	private static void assignSocial() {
		ProassignSocialWorker.drive();
	}
	private static void addPrescription() {
		ProPrescribeMedication.drive();		
	}

	private static void viewPrescription() {
		ProViewPrescription.drive();
		
	}

	private static void viewPatientInfo() {
		ProViewPatientInfo.drive();
	}
}
