package frontEnd.healthPro;

import java.util.List;

import beans.ObservationType;
import beans.PatientCondition;
import dao.oracle.ObservationDAO;
import dao.oracle.ObservationTypeDAO;
import dao.oracle.PatientConditionsDAO;
import dao.oracle.PhysiciansDAO;
import frontEnd.utility.Utility;

public class ProAssignOtToCondition {

	public static void drive() {
		int ot_type = getOt();
		int cid = getCondition();

		updateDB(ot_type, cid);
	}

	private static void updateDB(int ot_type, int cid) {
		PatientConditionsDAO.addNewConditionObservationInteraction(cid, ot_type);
	}

	private static int getOt() {
		List<ObservationType> list = ObservationTypeDAO.getAllObservationTypes();
		System.out.println("Enter the Observation Type to associate with a condition: ");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getDisplay_name());
		}
		int choice = Utility.getValidChoice(list.size());
		return list.get(choice).getType_id();
	}

	private static int getCondition() {
		List<PatientCondition> list = PatientConditionsDAO.getAllConditionTypes();
		System.out.println("Enter the Condition to associate with an observation type: ");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getDescription());
		}
		int choice = Utility.getValidChoice(list.size());
		return list.get(choice).getCondition();
	}
}
