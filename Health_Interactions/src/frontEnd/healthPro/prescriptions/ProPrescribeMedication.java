package frontEnd.healthPro.prescriptions;

import java.util.List;
import java.util.Scanner;

import dao.oracle.PatientDAO;
import dao.oracle.PrescriptionDAO;
import frontEnd.utility.Utility;
import beans.Patient;
import beans.Prescription;

/**
 * Adds a prescription for a patient after prompting the user for
 * the appropriate fields.
 */
public class ProPrescribeMedication {

	/**
	 * Adds a prescription for a patient after prompting the user for
	 * the appropriate fields.
	 */
	public static void drive() {
		Patient patient = getPatient();
		String drug_name = getDrugName();
		int dosage = getDosage();
		String start = Utility.getDate("start");
		String end = Utility.getDate("end");
		String phone = getPhone();
		
		Prescription p = new Prescription();
		p.setPid((int) patient.getPid());
		p.setPhone(phone);
		p.setDrug_name(drug_name);
		p.setDosage(dosage);
		p.setEnd(end);
		p.setStart(start);
		
		updateDB(p);
	}

	/**
	 * Gets the phone number.
	 * @return phone number.
	 */
	public static String getPhone() {
		System.out.println("Enter the phone number: ");
		return Utility.getInput();
	}

	/**
	 * Gets the patient.
	 * @return patient
	 */
	private static Patient getPatient() {
		System.out.println("Select the patient you would like to view: ");
		List<Patient> list = PatientDAO.viewAllPatients();
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() +","+ list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		return list.get(choice);
	}

	/**
	 * Gets the date requested.
	 * @param date requested
	 * @return date given
	 */
	public static String getDate(String str) {
		System.out.println("Enter the " + str + " date (");
		return Utility.getInput();
	}

	/**
	 * Gets dosage from the user
	 * @return dosage
	 */
	public static int getDosage() {
		System.out.println("Enter the dosage in mg: ");
		return Utility.getValidChoice(214748364);
	}

	/**
	 * Gets the drug name.
	 * @return drug name
	 */
	public static String getDrugName() {
		System.out.println("Enter the drug name: ");
		return Utility.getInput();
	}

	/**
	 * Updates the database with the new prescription.
	 * @param prescription
	 */
	private static void updateDB(Prescription p) {
		PrescriptionDAO.addPrescription(p);
	}
}
