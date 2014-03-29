package frontEnd.healthPro;

import java.util.List;

import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import beans.Patient;
import beans.Physician;
import beans.PhysicianAppt;

public class ProViewMeetings {

	public static void drive(Physician p) {
		List<PhysicianAppt> list = PhysiciansDAO.viewApptForPhysician(p.getPid());
		
		if (list.size() == 0) {
			System.out.println("You have no appointments scheduled.");
		}
		displayAppts(list);
	}
	
	public static void displayAppts(List<PhysicianAppt> list) {
		for (int i=0; i<list.size(); i++) {
			PhysicianAppt curr = list.get(i);
			Patient p = PatientDAO.getPatient(curr.getPid());
			System.out.println("Appt # " + i);
			System.out.println("With:  " + p.getLname() + ", " + p.getFname());
			System.out.println("Date:  " + curr.getAppt_date());
			System.out.println("Time:  " + curr.getHour() + ":" + curr.getMinutes());
			System.out.println();
		}
	}
}
