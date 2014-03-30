package frontEnd.socialWorker;

import java.util.List;

import beans.Patient;
import beans.SocialWorker;
import dao.oracle.SocialWorkersDAO;
import frontEnd.patient.viewData.PatientViewDataDriver;
import frontEnd.utility.Utility;

/**
 * Views the patient observation data for a social workers patients.
 * @author cmnelso5
 *
 */
public class SocialViewPatientObsData {

	/**
	 * Views the patient observation data for a social workers patients.
	 * @param social worker logged in.
	 */
	public static void drive(SocialWorker sw) {
		List<Patient> list = SocialWorkersDAO.getPatientsForSocialWorker(sw);
		if (list.size() == 0) {
			System.out.println("You do not have any patients asssigned to you.");
			return;
		}
		
		System.out.println("Select the patient to view data for: ");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname());
		}
		
		int choice = Utility.getValidChoice(list.size());
		
		PatientViewDataDriver.drive(list.get(choice));		
	}
}
