package frontEnd.patient.connection;

import java.util.List;

import dao.oracle.HealthFriendsDAO;
import beans.Patient;

public class PatientViewHealthFriendSameCity {

	public static void drive(Patient p) {
		List<Patient> list = HealthFriendsDAO.healthFriendsSameCity(p);
		
		if (list.size() == 0) {
			System.out.println("You have no Health Friends in the same city as you.");
			return;
		}
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname());
		}
	}
}
