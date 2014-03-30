package frontEnd.patient;

import java.util.List;

import dao.oracle.PrescriptionDAO;
import beans.Patient;
import beans.Prescription;

/**
 * Patient views prescriptions
 * @author cmnelso5
 *
 */
public class PatientViewPrescriptions {

	/**
	 * Patient views prescriptions.
	 * @param p
	 */
	public static void drive(Patient p) {
		List<Prescription> list = PrescriptionDAO.getAllPrescriptions(p);
		
		if (list.size() == 0) {
			System.out.println("You have no prescriptions.");
			return;
		}
		
		displayPrescriptions(list);
	}
	
	/**
	 * DFisplays to the user the prescriptions they have.
	 * @param list
	 */
	public static void displayPrescriptions(List<Prescription> list) {
		for (int i=0; i<list.size(); i++) {
			Prescription curr = list.get(i);
			System.out.println("Prescription # " +i);
			System.out.println(curr.getDosage() + " mg of " + curr.getDrug_name() + " from "
								+ curr.getStart() + " to " + curr.getEnd());
			System.out.println();
		}
	}
}
