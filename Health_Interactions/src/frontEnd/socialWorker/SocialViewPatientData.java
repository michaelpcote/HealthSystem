package frontEnd.socialWorker;

import java.util.List;

import dao.oracle.SocialWorkersDAO;
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
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getLname() + ", " + list.get(i).getFname());
		}
	}
}
