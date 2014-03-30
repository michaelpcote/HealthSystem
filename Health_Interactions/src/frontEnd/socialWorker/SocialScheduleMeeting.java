package frontEnd.socialWorker;

import java.sql.Date;
import java.util.List;

import dao.oracle.SocialWorkersDAO;
import frontEnd.utility.Utility;
import beans.Patient;
import beans.SocialWorker;
import beans.SocialWorkerAppt;

/**
 * Schedules a meeting for a social worker with a patient.
 * @author cmnelso5
 *
 */
public class SocialScheduleMeeting {

	public static void drive(SocialWorker sw) {
		SocialWorkerAppt swa = getAppointment(sw);
		if (swa == null) {
			System.out.println("You do not have any patients assigned to you.");
			return;
		}
		
		updateDB(swa);
	}

	/**
	 * Returns the appointment, or null if the social worker does not have any patients
	 * assigned to them.
	 * @return social worker appointment
	 */
	private static SocialWorkerAppt getAppointment(SocialWorker sw) {
		SocialWorkerAppt swa = new SocialWorkerAppt();
		
		int pid = getPid(sw);
		if (pid == -1) {
			return null;
		}
		String appt_date = getDate();
		int hour = getTime("hour", 23);
		int minutes = getTime("minute", 59);
		
		swa.setSid(sw.getSid());
		swa.setPid(pid);
		swa.setAppt_date(appt_date);
		swa.setHour(hour);
		swa.setMinutes(minutes);
		return swa;
	}

	private static int getTime(String when, int limit) {
		System.out.println("Enter the " + when + " you would like to meet at (in military form): ");
		return Utility.getValidChoice(limit);
	}

	private static String getDate() {
		return Utility.getDate("appointment");
	}

	private static int getPid(SocialWorker sw) {
		List<Patient> list = SocialWorkersDAO.getPatientsForSocialWorker(sw);
		if (list.size() == 0) {
			return -1;
		}
		System.out.println("Select the Patient you would like to make an appointment with: ");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		return (int) list.get(choice).getPid();
	}

	private static void updateDB(SocialWorkerAppt swa) {
		SocialWorkersDAO.createAppt(swa);
	}
}
