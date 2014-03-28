package frontEnd.socialWorker;

import java.util.List;

import dao.oracle.SocialWorkersDAO;
import frontEnd.healthPro.ProViewPatientInfo;
import beans.Patient;
import beans.SocialWorker;
/**
 * Lists the patients assigned to the social worker.
 * @author cmnelso5
 */
public class SocialViewPatientData {

	/**
	 * Lists the patients assigned to the social worker.
	 * @param social worker logged in
	 */
	public static void drive(SocialWorker sw) {
		List<Patient> list = SocialWorkersDAO.getPatientsForSocialWorker(sw);
		if (list.size() == 0) {
			System.out.println("You have no patients assigned to you.");
			return;
		}
		for (int i=0; i<list.size(); i++) {
			ProViewPatientInfo.printPatientInfo(list.get(i));
			System.out.println();
		}
	}
}
