package frontEnd.test;

import java.util.List;

import beans.Patient;
import beans.SocialWorker;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;

public class SocialTestDriver {

	public static void main(String[] args) {
		viewPatients();
	}

	public static void viewPatients() {
		SocialWorker sw = SocialWorkersDAO.getSocialWorker(12);
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(5);
		PhysiciansDAO.assignPatientToSocialWorker(patient, sw);
		List<Patient> list = SocialWorkersDAO.getPossiblePatientsForSocialWorker(sw);
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getLname() + ", " + list.get(i).getFname());
		}
	}
}
