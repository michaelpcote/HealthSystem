package frontEnd.socialWorker;

import java.util.List;

import dao.oracle.PatientDAO;
import dao.oracle.SocialWorkersDAO;
import beans.Patient;
import beans.SocialWorker;
import beans.SocialWorkerAppt;


public class SocialViewMeetings {

	public static void drive(SocialWorker sw) {
		List<SocialWorkerAppt> list = SocialWorkersDAO.viewApptsForSocialWorker(sw.getSid());
		
		if (list.size() == 0) {
			System.out.println("You have no appointments scheduled.");
		}
		displayAppts(list);
	}
	
	public static void displayAppts(List<SocialWorkerAppt> list) {
		for (int i=0; i<list.size(); i++) {
			SocialWorkerAppt curr = list.get(i);
			Patient p = PatientDAO.getPatient(curr.getPid());
			System.out.println("Appt # " + i);
			System.out.println("With:  " + p.getLname() + ", " + p.getFname());
			System.out.println("Date:  " + curr.getAppt_date());
			System.out.println("Time:  " + curr.getHour() + ":" + curr.getMinutes());
			System.out.println();
		}
	}
}
