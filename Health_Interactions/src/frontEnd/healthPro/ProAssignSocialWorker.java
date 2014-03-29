package frontEnd.healthPro;

import java.util.List;
import java.util.Scanner;

import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;
import frontEnd.utility.Utility;
import beans.Patient;
import beans.Physician;
import beans.SocialWorker;

public class ProAssignSocialWorker {

	public static void drive() {
		SocialWorker sw = getWorker();
		Patient patient = getPatient(sw);
		
		updateDB(sw, patient);
	}

	/**
	 * Updates the database with the newly selected assignment of the social worker
	 * with the patient.
	 * @param sw
	 * @param patient
	 */
	private static void updateDB(SocialWorker sw, Patient patient) {
		PhysiciansDAO.assignPatientToSocialWorker(patient, sw);;
	}

	/**
	 * Gets the patient the physician will assign the social worker to.
	 * @return Patient to be assigned
	 */
	private static Patient getPatient(SocialWorker sw) {
		System.out.println("Select the Patient to assign the social worker to: ");
		List<Patient> list = SocialWorkersDAO.getPossiblePatientsForSocialWorker(sw);
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + "," + list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		return list.get(choice);
	}

	/**
	 * Gets the social worker the physician wants to assign.
	 * @return Social Worker
	 */
	private static SocialWorker getWorker() {
		System.out.println("Select the Social Worker to assign a patient to: ");
		List<SocialWorker> list = SocialWorkersDAO.getAllSocialWorkers();
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + "," + list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		return list.get(choice);
	}
}
