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
		String startdate = Utility.getDate("start");
		String enddate = Utility.getDate("end");
		
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
		String info = getObservations(patient, ot, startdate, enddate);
		String[] list = info.split(",");
		if (list.length == 1) {
			System.out.println("No observations recorded.");
		}
		else {
			for (int i=0; i<list.length; i++) {
				String[] split = list[i].split(":");
				for (int j=0; j<split.length; j++) {
					System.out.print(split[j] + " -- ");
				}
				System.out.println();
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
	public static String getObservations(Patient patient, ObservationType ot, String startdate, String enddate) {
		return ObservationReportsDAO.getObservationsBetween(patient, ot, startdate, enddate);
	}
	
}
