package frontEnd;

import java.util.List;

import dao.oracle.PatientDAO;
import beans.Patient;
import frontEnd.patient.clearAlerts.PatientClearAlerts;
import frontEnd.patient.enterData.PatientAddObservationType;
import frontEnd.patient.enterData.PatientEnterData;
import frontEnd.patient.viewData.PatientViewAlerts;
import frontEnd.patient.viewData.PatientViewObservations;

public class TestDriver {

	public static void main(String[] args) {
		clearAlerts();
		//viewObservations();
	}
	
	public static void viewObservations() {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(30);
		PatientViewObservations.drive(p);
	}
	
	public static void clearAlerts() {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(30);
		PatientViewAlerts.drive(p);
		PatientClearAlerts.drive(p);
		PatientViewAlerts.drive(p);

		
	}
}
