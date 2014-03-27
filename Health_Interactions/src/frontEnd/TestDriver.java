package frontEnd;

import java.util.List;

import dao.oracle.PatientDAO;
import beans.Patient;
import frontEnd.patient.enterData.PatientAddObservationType;
import frontEnd.patient.enterData.PatientEnterData;
import frontEnd.patient.viewData.PatientViewObservations;

public class TestDriver {

	public static void main(String[] args) {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(2);
		PatientEnterData.drive(p);
		
		PatientViewObservations.drive(p);
		
	}
}
