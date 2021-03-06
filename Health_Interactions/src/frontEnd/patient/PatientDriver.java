package frontEnd.patient;

import dao.oracle.PatientDAO;
import frontEnd.patient.clearAlerts.PatientClearAlerts;
import frontEnd.patient.connection.PatientConnectionDriver;
import frontEnd.patient.enterData.PatientEnterDataDriver;
import frontEnd.patient.viewData.PatientViewDataDriver;
import frontEnd.patient.viewMeeting.PatientViewMeetingDriver;
import frontEnd.utility.Utility;
import beans.Patient;

/**
 * Drives the UI for a patient upon logging in.
 * @author nelc
 *
 */
public class PatientDriver {

	/**
	 * Drives the UI for a patient upon logging in.
	 * @param patient logged in
	 */
	public static void drive(Patient p) {
		while (true) {
			System.out.println("Hi " + p.getFname() + " " + p.getLname());
			System.out.println();
			System.out.println("Enter your selection: ");
			System.out.println("0 -- View Data");
			System.out.println("1 -- Enter Data");
			System.out.println("2 -- Connection");
			System.out.println("3 -- Clear Alerts");
			System.out.println("4 -- View Prescriptions");
			System.out.println("5 -- View Meetings");
			System.out.println("6 -- Logout");
			
			int choice = Utility.getValidChoice(7);
			
			if (choice == 0) {
				PatientViewDataDriver.drive(p);
			} else if (choice == 1) {
				PatientEnterDataDriver.drive(p);
			} else if (choice == 2) {
				PatientConnectionDriver.drive(p);
			} else if (choice == 3) {
				PatientClearAlerts.drive(p);
			} else if (choice == 4) {
				PatientViewPrescriptions.drive(p);
			} else if (choice == 5) {
				PatientViewMeetingDriver.drive(p);
			}
			else if (choice == 6) {
				return;
			}
		}
	}
}
