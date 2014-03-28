package frontEnd.healthPro.prescriptions;

import java.util.List;

import dao.oracle.PatientDAO;
import dao.oracle.PrescriptionDAO;
import frontEnd.utility.Utility;
import beans.Patient;
import beans.Prescription;

public class ProEditPrescription {

	public static void drive() {
		Patient patient = getPatient();
		Prescription p = getPrescription(patient);
		if (p == null){
			System.out.println("The Patient does not have any prescriptions. ");
			return;
		}
		p = editPrescription(p);
		
		updateDB(p);
	}
	
	/**
	 * Edits the prescription.
	 * @param current prescription
	 * @return edited prescription
	 */
	private static Prescription editPrescription(Prescription p) {
		while (true) {
			System.out.println("number -- " +p.getPrescription_num());
			System.out.println("Enter the field you would like to edit: ");
			System.out.println("0 -- Phone");
			System.out.println("1 -- Drug name");
			System.out.println("2 -- Dosage");
			System.out.println("3 -- Start date");
			System.out.println("4 -- End date");
			int choice = Utility.getValidChoice(4);
			if (choice == 0) {
				p.setPhone(ProPrescribeMedication.getPhone());
			} else if (choice == 1) {
				p.setDrug_name(ProPrescribeMedication.getDrugName());
			} else if (choice == 2) {
				p.setDosage(ProPrescribeMedication.getDosage());
			} else if (choice == 3) {
				p.setStart(ProPrescribeMedication.getDate("start"));
			} else if (choice == 4) {
				p.setEnd(ProPrescribeMedication.getDate("end"));
			}
			System.out.println("Enter yes to edit another field: ");
			if (!Utility.getInput().toLowerCase().startsWith("y")) {
				return p;
			}
		}
	}

	/**
	 * Gets the prescription of the passed in patient to be edited
	 * @param patient
	 * @return prescription
	 */
	private static Prescription getPrescription(Patient patient) {
		System.out.println("Select the prescription to edit: ");
		List<Prescription> list = PrescriptionDAO.getAllPrescriptions(patient);
		if (list.size() == 0) {
			return null;
		}
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getDrug_name() + ": " + list.get(i).getDosage());
		}
		return list.get(Utility.getValidChoice(list.size()));
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
	 * Updates the database with the edited prescription.
	 * @param edited presription
	 */
	public static void updateDB(Prescription p) {
		PrescriptionDAO.editPrescription(p);
	}
}
