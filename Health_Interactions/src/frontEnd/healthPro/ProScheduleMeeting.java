package frontEnd.healthPro;

import java.util.List;

import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;
import frontEnd.utility.Utility;
import beans.Patient;
import beans.Physician;
import beans.PhysicianAppt;
import beans.SocialWorker;
import beans.SocialWorkerAppt;

public class ProScheduleMeeting {

	public static void drive(Physician phys) {
		PhysicianAppt pha = getAppointment(phys);
		
		updateDB(pha);
	}

	/**
	 * Returns the appointment
	 * assigned to them.
	 * @return social worker appointment
	 */
	private static PhysicianAppt getAppointment(Physician phys) {
		PhysicianAppt pha = new PhysicianAppt();
		
		int pid = getPid(phys);
		String appt_date = getDate();
		int hour = getTime("hour", 23);
		int minutes = getTime("minute", 59);
		
		pha.setPhy_id(phys.getPid());
		pha.setPid(pid);
		pha.setAppt_date(appt_date);
		pha.setHour(hour);
		pha.setMinutes(minutes);
		return pha;
	}

	private static int getTime(String when, int limit) {
		System.out.println("Enter the " + when + " you would like to meet at (in military form): ");
		return Utility.getValidChoice(limit);
	}

	private static String getDate() {
		return Utility.getDate("appointment");
	}

	private static int getPid(Physician phys) {
		List<Patient> list = PhysiciansDAO.getMyPatients(phys.getPid());
		System.out.println("Select the Patient you would like to make an appointment with: ");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		return (int) list.get(choice).getPid();
	}

	private static void updateDB(PhysicianAppt pha) {
		PhysiciansDAO.createAppt(pha);
	}

}
