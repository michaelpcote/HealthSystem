package frontEnd.healthPro;

import java.util.List;
import java.util.Scanner;

import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import frontEnd.utility.Utility;
import beans.Patient;
import beans.Physician;

/**
 * Prints out information on the patient selected by the physician.
 */

public class ProViewPatientInfo {

	/**
	 * Prints out information on the patient selected by the physician.
	 */
	public static void drive(Physician phy) {
		System.out.println("Enter the patient to view info on: ");
		List<Patient> list = PhysiciansDAO.getMyPatients(phy.getPid());
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		Patient patient = list.get(choice);
		printPatientInfo(patient);
	}

	/**
	 * Prints information on the given patient.
	 * @param patient
	 */
	public static void printPatientInfo(Patient patient) {
		System.out.println(patient.getLname() + ", "+ patient.getFname());
		System.out.println(patient.getSexAsString());
		System.out.println(patient.getDobAsString());
		System.out.println(patient.getAddress());
		System.out.println(patient.getCity() + "," + patient.getState() + " " + patient.getZip());
	}
}
