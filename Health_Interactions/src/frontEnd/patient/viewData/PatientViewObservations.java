package frontEnd.patient.viewData;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.oracle.ObservationReportsDAO;
import dao.oracle.ObservationTypeDAO;
import frontEnd.utility.Utility;
import beans.ObservationType;
import beans.Patient;

/**
 * Presents to the user list of Observations made by them of a given type in 
 * a given range.
 * @author cmnelso5
 *
 */
public class PatientViewObservations {

	/**
	 * Drives the patient viewing Observations made by them of a given type in
	 * a given range.
	 * @param current patient
	 */
	public static void drive(Patient patient) {
		ObservationType ot = getObservationType(patient);
		String startdate = getDate("start");
		String enddate = getDate("end");
		
		showObservations(patient, ot, startdate, enddate);
	}
	
	/**
	 * Shows the observations to the user
	 * @param patient
	 * @param ot
	 * @param startdate
	 * @param enddate
	 */
	private static void showObservations(Patient patient, ObservationType ot,
			String startdate, String enddate) {
		String info = updateDB(patient, ot, startdate, enddate);
		String[] list = info.split(",");
		for (int i=0; i<list.length; i++) {
			System.out.println(list[i]);
		}
	}

	/**
	 * Gets a date from the user.
	 * @param the date the user wants
	 * @return
	 */
	private static String getDate(String when) {
		while (true) {
			System.out.println("Enter the " + when + " date of the Observation window (in form yyyy-mm-dd");
			String date = Utility.getInput();
			if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return date;
			}
			else {
				System.out.println("Incorrect date format \""+date+"\" (need yyyy-mm-dd).");
			}
		}
	}

	/**
	 * Gets the observation that the patient wants to view a report for.  The patient
	 * picks from the Types that he is currently subscribed to.
	 * @param patient to grab Type from.
	 * @return Observation Type chosen
	 */
	private static ObservationType getObservationType(Patient patient) {
		System.out.println("Enter the observation type you would like to view: ");
		List<ObservationType> list = ObservationTypeDAO.getObservationTypesForPatient(patient);
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getDisplay_name());
		}
		int choice = Utility.getValidChoice(list.size());
		return list.get(choice);
	}

	/**
	 * Returns a list of observations from the database with the given parameters.
	 * Returns string in form "header:header:header,value:value:value,value:value:value".
	 * @param patient to get observations of
	 * @param ObservationType
	 * @param startdate of report
	 * @param enddate of reporrt
	 */
	public static String updateDB(Patient patient, ObservationType ot, String startdate, String enddate) {
		return ObservationReportsDAO.getObservationsBetween(patient, ot, startdate, enddate);
	}
	
}
