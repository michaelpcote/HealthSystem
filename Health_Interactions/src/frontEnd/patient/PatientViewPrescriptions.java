package frontEnd.patient;

import java.util.List;

import dao.oracle.PrescriptionDAO;
import beans.Patient;
import beans.Prescription;

public class PatientViewPrescriptions {

	public static void drive(Patient p) {
		List<Prescription> list = PrescriptionDAO.getAllPrescriptions(p);
		
		if (list.size() == 0) {
			System.out.println("You have no prescriptions.");
			return;
		}
		
		displayPrescriptions(list);
	}
	
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
