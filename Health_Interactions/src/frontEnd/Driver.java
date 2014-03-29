package frontEnd;

import beans.Patient;
import beans.Physician;
import beans.SocialWorker;
import dao.oracle.LogInDAO;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import dao.oracle.SocialWorkersDAO;
import frontEnd.healthPro.ProDriver;
import frontEnd.patient.PatientDriver;
import frontEnd.socialWorker.SocialDriver;
import frontEnd.utility.Utility;

public class Driver {
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("------WELCOME------");
			System.out.println();
			System.out.println("New User (yes/no)");
			if (Utility.getInput().toLowerCase().startsWith("y")) {
				int id = PatientRegister.drive();
				System.out.println("You are now registered!!!");
				System.out.println("Your login id number is:  " + id);
			}
			System.out.print("User ID number? ");
			int id = Utility.getValidChoice(100000);
			System.out.print("Password? ");
			String pwd = Utility.getInput();
			
			String user = LogInDAO.allowLogIn(id, pwd);
			
			if ( user == null || user.equals("") ) {
				System.out.println("Invalid login. Please try again.");
				
			} else if (user.equals("Patient")) {
				Patient p = PatientDAO.getPatient(id);
				PatientDriver.drive(p);
			}
			else if (user.equals("Physician")) {
				Physician p = PhysiciansDAO.getPhysician(id);
				ProDriver.drive(p);
			}
			else if (user.equals("Social Worker")) {
				SocialWorker sw = SocialWorkersDAO.getSocialWorker(id);
				SocialDriver.drive(sw);
			}
		}
	}
}