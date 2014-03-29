package frontEnd.patient.viewMeeting;

import java.util.List;

import beans.Patient;
import beans.PhysicianAppt;
import beans.SocialWorkerAppt;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;

public class PatientViewSocialMeetings {

	public static void drive(Patient p) {
		List<SocialWorkerAppt> list = SocialWorkersDAO.viewSocialWorkerApptRequest(p);
		if (list.size() == 0) {
			System.out.println("You have no meetings scheduled with Social Workers.");
			return;
		}
		
		for (int i=0; i<list.size(); i++) {
			SocialWorkerAppt curr = list.get(i);
			System.out.println("Appointment # " + i);
			System.out.println("With: Dr. " + PhysiciansDAO.getPhysician(curr.getSid()).getLname());
			System.out.println("Date:  " + curr.getAppt_date());
			System.out.println("Time:  " + curr.getHour() + ":" + curr.getMinutes());
			System.out.println();
		}
	}

}
