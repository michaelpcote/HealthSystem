package frontEnd.test;

import java.util.List;

import beans.Patient;
import beans.Physician;
import beans.SocialWorker;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;
import frontEnd.healthPro.ProAssignOtToCondition;
import frontEnd.healthPro.ProScheduleMeeting;
import frontEnd.healthPro.ProViewAgrrReport;
import frontEnd.healthPro.ProViewPatientInfo;
import frontEnd.healthPro.ProAssignSocialWorker;
import frontEnd.healthPro.prescriptions.ProEditPrescription;
import frontEnd.healthPro.prescriptions.ProPrescribeMedication;
import frontEnd.healthPro.prescriptions.ProViewPrescription;
import frontEnd.socialWorker.SocialViewPatientData;

public class ProTestDriver {

	public static void main(String[] args) {
		//viewPatientInfo();
		//viewPrescription();
		//addPrescription();
		//editPrescription();
		//assignSocial();
		aggrReports();
		//assignObsToCondition();
		//makeAppt();
	}
	
	private static void makeAppt() {
		Physician phys = PhysiciansDAO.getPhysician(1);
		ProScheduleMeeting.drive(phys);	
	}

	private static void editPrescription() {
		ProEditPrescription.drive();
		ProViewPrescription.drive();
	}

	private static void assignObsToCondition() {
		ProAssignOtToCondition.drive();
	}

	private static void aggrReports() {
		while (true) {ProViewAgrrReport.drive();}
	}

	private static void assignSocial() {
		ProAssignSocialWorker.drive();
		System.out.println("\n\nRESULTS:\n\n");
		SocialWorker sw = SocialWorkersDAO.getSocialWorker(112);
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
