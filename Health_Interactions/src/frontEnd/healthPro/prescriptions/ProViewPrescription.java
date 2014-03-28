package frontEnd.healthPro.prescriptions;

import java.util.List;

import beans.Patient;
import beans.Prescription;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.PrescriptionDAO;
import frontEnd.utility.Utility;

/**
 * Selects the patient to view prescriptions of and then displays
 * the prescriptions for that patient.
 */

public class ProViewPrescription {

	/**
	 * Selects the patient to view prescriptions of and then displays
	 * the prescriptions for that patient.
	 */
	public static void drive() {
		System.out.println("Enter the patient to view prescriptions for: ");
		List<Patient> list = PatientDAO.viewAllPatients();
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		List<Prescription> prescriptions = PrescriptionDAO.getAllPrescriptions(list.get(choice));
		if (prescriptions.size() == 0) {
			System.out.println("Patient has no prescriptions prescribed.");
		}
		printPrescriptions(prescriptions);
	}

	/**
	 * Prints prescriptions passed in.
	 * @param prescription list
	 */
	private static void printPrescriptions(List<Prescription> p) {
		for (int i=0; i<p.size(); i++) {
			Prescription pre = p.get(i);
			System.out.println(pre.getDosage() + " of " + pre.getDrug_name() + " from " +
					pre.getStart() + " to " + pre.getEnd());
		}
	}
}
