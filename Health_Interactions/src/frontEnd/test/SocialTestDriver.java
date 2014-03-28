package frontEnd.test;

import java.util.List;

import beans.Patient;
import beans.SocialWorker;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;
import frontEnd.socialWorker.SocialViewPatientData;

public class SocialTestDriver {

	public static void main(String[] args) {
		//viewSocial();
		//viewPatients();
	}
	
	private static void viewSocial() {
		List<SocialWorker> list = SocialWorkersDAO.getAllSocialWorkers();
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getSid());
		}
	}

	public static void viewPatients() {
		SocialWorker sw = SocialWorkersDAO.getSocialWorker(112);
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(45);
		PhysiciansDAO.assignPatientToSocialWorker(patient, sw);
		SocialViewPatientData.drive(sw);
	}
}
