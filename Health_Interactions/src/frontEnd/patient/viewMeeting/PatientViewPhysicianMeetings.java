package frontEnd.patient.viewMeeting;

import java.util.List;

import dao.oracle.PhysiciansDAO;
import beans.Patient;
import beans.PhysicianAppt;

public class PatientViewPhysicianMeetings {

	public static void drive(Patient p) {
		List<PhysicianAppt> list = PhysiciansDAO.viewPhysicianApptRequest(p);
		if (list.size() == 0) {
			System.out.println("You have no meetings scheduled with Physicians.");
			return;
		}
		
		for (int i=0; i<list.size(); i++) {
			PhysicianAppt curr = list.get(i);
			System.out.println("Appointment # " + i);
			System.out.println("With: Dr. " + PhysiciansDAO.getPhysician(curr.getPhy_id()).getLname());
			System.out.println("Date:  " + curr.getAppt_date());
			System.out.println("Time:  " + curr.getHour() + ":" + curr.getMinutes());
			System.out.println();
		}
	}
}
