package frontEnd.patient.viewData;

import java.util.Scanner;

import dao.oracle.AlertsDAO;
import beans.Patient;

/**
 * Presents to the user their list of un-viewed alerts.
 * @author nelc
 *
 */
public class PatientViewAlerts {

	/**
	 * Presents to the user their list of un-viewed alerts.
	 * @param patient
	 */
	public static void drive(Patient patient) {
		System.out.println("ALERTS: ");
		String alerts = AlertsDAO.viewNonClearedAlerts(patient);
		if (alerts == "") {
			System.out.println("No alerts.");
		}
		else {
			String[] list = alerts.split(",");
			for (int i=0; i<list.length; i++) {
				System.out.println(i + " -- " + list[i]);
			}
		}
	}
}
