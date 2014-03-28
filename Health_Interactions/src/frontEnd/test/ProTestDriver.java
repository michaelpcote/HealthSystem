package frontEnd.test;

import java.util.List;

import beans.Patient;
import beans.SocialWorker;
import dao.oracle.PatientDAO;
import dao.oracle.SocialWorkersDAO;
import frontEnd.healthPro.ProAssignOtToCondition;
import frontEnd.healthPro.ProViewAgrrReport;
import frontEnd.healthPro.ProViewPatientInfo;
import frontEnd.healthPro.ProassignSocialWorker;
import frontEnd.healthPro.prescriptions.ProPrescribeMedication;
import frontEnd.healthPro.prescriptions.ProViewPrescription;
import frontEnd.socialWorker.SocialViewPatientData;

public class ProTestDriver {

	public static void main(String[] args) {
		//viewPatientInfo();
		//viewPrescription();
		//addPrescription();
		//assignSocial();
		//aggrReports();
		assignObsToCondition();
	}
	
	private static void assignObsToCondition() {
		ProAssignOtToCondition.drive();
	}

	private static void aggrReports() {
		while (true) {ProViewAgrrReport.drive();}
	}

	private static void assignSocial() {
		ProassignSocialWorker.drive();
		System.out.println("\n\nRESULTS:\n\n");
		SocialWorker sw = SocialWorkersDAO.getSocialWorker(111); // assigned 5
		List<Patient> list = SocialWorkersDAO.getPatientsForSocialWorker(sw);
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getLname() + ", " + list.get(i).getFname());
		}
		
	}
	private static void addPrescription() {
		viewPrescription();
		ProPrescribeMedication.drive();
		viewPrescription();
	}

	private static void viewPrescription() {
		ProViewPrescription.drive();
	}

	private static void viewPatientInfo() {
		ProViewPatientInfo.drive();
	}
}
