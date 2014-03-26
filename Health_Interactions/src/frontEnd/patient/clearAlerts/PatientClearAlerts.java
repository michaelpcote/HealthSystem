package frontEnd.patient.clearAlerts;

import dao.oracle.AlertsDAO;
import beans.Patient;

/**
 * Clears alerts for a patient
 * @author cmnelso5
 *
 */
public class PatientClearAlerts {

	/**
	 * clears alerts for a given patient.
	 * @param patient
	 */
	public static void drive(Patient patient) {
		AlertsDAO.clearViewedAlerts(patient);
	}
}
