package frontEnd;

import java.util.List;

import beans.Patient;
import beans.Physician;
import dao.oracle.PatientDAO;
import dao.oracle.PhysiciansDAO;
import frontEnd.utility.Utility;

public class PatientRegister {

	public static int drive() {
		Patient p = getPatientInfo();
		return PatientDAO.insertPatient(p);
	}
	// password, fname, lname, address, city, state, zip, dob, sex, public_status, primary_physician
	public static Patient getPatientInfo() {
		Patient p = new Patient();
		System.out.println("Enter your password: ");
		p.setPassword(Utility.getInput());
		System.out.println("Enter your first name: ");
		p.setFname(Utility.getInput());
		System.out.println("Enter your last name: ");
		p.setLname(Utility.getInput());
		System.out.println("Enter your street address: ");
		p.setAddress(Utility.getInput());
		System.out.println("Enter your city: ");
		p.setCity(Utility.getInput());
		System.out.println("Enter your state: ");
		p.setState(Utility.getInput());
		System.out.println("Enter your zip code: ");
		p.setZip(Utility.getInput());
		System.out.println("Are you male?");
		if (Utility.getInput().toLowerCase().startsWith("y")) {
			p.setSex(1);
		} //
		else {
			p.setSex(2);
		}
		p.setDob(Utility.getDate("birth"));
		System.out.println("Would you like to socialize with Health Friends? ");
		if (Utility.getInput().toLowerCase().startsWith("y")) {
			p.setPublicStatus("yes");
		}
		else {
			p.setPublicStatus("no");
		}
		p.setPrimaryPhysician(getPhysician());
	
		return p;
	}
	
	public static int getPhysician() {
		List<Physician> list = PhysiciansDAO.getAllPhysicians();
		System.out.println("Select your primary physician: ");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() + ", " + list.get(i).getFname());
		}
		Physician phy = list.get(Utility.getValidChoice(list.size()));
		return phy.getPid();
	}
}
