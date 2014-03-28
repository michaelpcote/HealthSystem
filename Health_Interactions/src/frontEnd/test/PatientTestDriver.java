package frontEnd.test;

import java.util.List;

import dao.oracle.PatientDAO;
import beans.Patient;
import frontEnd.healthPro.ProViewAgrrReport;
import frontEnd.patient.clearAlerts.PatientClearAlerts;
import frontEnd.patient.connection.PatientFindHealthFriend;
import frontEnd.patient.connection.PatientFindHealthFriendAtRisk;
import frontEnd.patient.connection.PatientSendMessage;
import frontEnd.patient.connection.PatientViewHealthFriends;
import frontEnd.patient.connection.PatientViewMessages;
import frontEnd.patient.enterData.PatientAddObservationType;
import frontEnd.patient.enterData.PatientEnterData;
import frontEnd.patient.viewData.PatientViewAlerts;
import frontEnd.patient.viewData.PatientViewObservations;

public class PatientTestDriver {

	public static void main(String[] args) {
		//clearAlerts();
		//viewObservations();
		//viewHealthFriends();
		findNewHealthFriend();
		//findHealthFriendAtRisk();
		//enterData();
		//newObsType();
		//sendViewMessage();
	}

	private static void sendViewMessage() {
		PatientDAO pdao = new PatientDAO();
		Patient p1 = pdao.getPatient(4);	
		Patient p2 = pdao.getPatient(1);
		
		//PatientSendMessage.drive(p1);
		PatientViewMessages.drive(p2);
	}

	private static void newObsType() {
		PatientAddObservationType.drive();
		enterData();
	}

	private static void enterData() {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(2);	
		PatientEnterData.drive(p);
	}

	private static void findHealthFriendAtRisk() {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(2);	
		PatientFindHealthFriendAtRisk.drive(p);
	}

	private static void findNewHealthFriend() {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(2);	
		PatientViewHealthFriends.drive(p);
		System.out.println();
		PatientFindHealthFriend.drive(p);
		PatientViewHealthFriends.drive(p);
	}

	private static void viewHealthFriends() {
		PatientDAO pdao = new PatientDAO();
		Patient p = pdao.getPatient(7);
		PatientViewHealthFriends.drive(p);
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
