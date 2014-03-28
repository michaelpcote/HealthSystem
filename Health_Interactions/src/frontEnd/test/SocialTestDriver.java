package frontEnd.test;

import java.util.List;

import beans.Patient;
import beans.SocialWorker;
import dao.oracle.SocialWorkersDAO;

public class SocialTestDriver {

	public static void main(String[] args) {
		viewPatients();
	}

	public static void viewPatients() {
		SocialWorker sw = SocialWorkersDAO.getSocialWorker(111);
	}
}
