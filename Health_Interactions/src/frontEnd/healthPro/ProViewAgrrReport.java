package frontEnd.healthPro;

import java.util.ArrayList;
import java.util.List;

import beans.ObservationType;
import beans.Patient;
import beans.PatientCondition;
import dao.oracle.ObservationDAO;
import dao.oracle.ObservationReportsDAO;
import dao.oracle.ObservationTypeDAO;
import dao.oracle.PatientConditionsDAO;
import dao.oracle.PatientDAO;
import frontEnd.utility.Utility;

/**
 * Prompts the user for the kind of aggregate report he wishes to run.
 * Includes averages, maximums, minimums of types; between date ranges; and sorted
 * by patient.
 */

public class ProViewAgrrReport {
	
	/**
	 * Prompts the user for the kind of aggregate report he wishes to run.
	 * Includes averages, maximums, minimums of types; between date ranges; and sorted
	 * by patient.
	 */
	public static void drive() {
		System.out.println("Enter the kind of report you would like to view: ");
		System.out.println("0 -- Average of an observation");
		System.out.println("1 -- Lowest of an observation");
		System.out.println("2 -- Highest of an observation");
		System.out.println("3 -- Get patients with highest of an observation");
		System.out.println("4 -- Get patients with lowest of an observation");
		System.out.println("5 -- Most occuring data field");
		System.out.println("6 -- Least occuring data field");
		System.out.println("7 -- Number of observations made by a patient");
		System.out.println("8 -- Number of observations made of an observation type");
		System.out.println("9 -- Number Observations during a period");
		System.out.println("10 -- Observations for a patient during a period");
		
		int choice = Utility.getValidChoice(10);
		
		if (choice == 0) {
			avgObs();
		} else if (choice == 1) {
			lowestObs();
		} else if (choice == 2) {
			highestObs();
		} else if (choice == 3) {
			patientsWithHighest();
		} else if (choice == 4) {
			patientsWithLowest();
		} else if (choice == 5) {
			mostOccuringString();
		} else if (choice == 6) {
			leastOccuringString();
		} else if (choice == 7) {
			numObsPatient();
		} else if (choice == 8) {
			numObsOfType();
		} else if (choice == 9) {
			obsDuringPeriod();
		} else if (choice == 10) {
			obsForPatient();
		}
	}

	/**
	 * All observations of a Observation Type for a patient during a time period.
	 */
	private static void obsForPatient() {
		System.out.println("Select the patient you would like to view: ");
		List<Patient> list = PatientDAO.viewAllPatients();
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() +","+ list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		Patient patient = list.get(choice);
		ObservationType ot = getObsType();
		System.out.println("Enter the start date: ");
		String start = Utility.getInput();
		System.out.println("Enter the end date: ");
		String end = Utility.getInput();
		printOutput(ObservationReportsDAO.getObservationsBetween(patient, ot, start, end));
	}

	/**
	 * Number of observations made by everyone during a time period.
	 */
	private static void obsDuringPeriod() {
		System.out.println("Enter the start date: ");
		String start = Utility.getInput();
		System.out.println("Enter the end date: ");
		String end = Utility.getInput();
		//TODO ObservationReportsDAO.getNumObservationsInPeriod(start, end);
	}

	/**
	 * Number of observations made of an observation type
	 */
	private static void numObsOfType() {
		System.out.println("Select the Observation Type you would like to view: ");
		List<ObservationType> list = ObservationTypeDAO.getAllObservationTypes();
		for (int i=0; i<list.size(); i++) {
			System.out.println(i +" -- "+ list.get(i).getDisplay_name());
		}
		int choice = Utility.getValidChoice(list.size());
		System.out.println(ObservationReportsDAO.numberOfObservationsByObsType(list.get(choice).getType_id()) + " observations were made.");
	}

	/**
	 * Number of observations a patient has made.
	 */
	private static void numObsPatient() {
		System.out.println("Select the patient you would like to view: ");
		List<Patient> list = PatientDAO.viewAllPatients();
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getLname() +","+ list.get(i).getFname());
		}
		int choice = Utility.getValidChoice(list.size());
		System.out.println("Patient has made " +ObservationReportsDAO.numberOfObservationsMadeByPatient(list.get(choice)) +" observations.");		
	}

	/**
	 * Least occurring mood.
	 */
	private static void leastOccuringString() {
		System.out.println("Select the following for the least occuring values of an observation type:");
		List<Integer> conditions = getPatientConditions();
		ObservationType ot = getObsType();
		String output = ObservationReportsDAO.leastOccurringStringValue(ot, conditions);
		printOutput(output);

	}

	/**
	 * Most occurring mood.
	 */
	private static void mostOccuringString() {
		System.out.println("Select the following for the most occuring values of an observation type:");
		List<Integer> conditions = getPatientConditions();
		ObservationType ot = getObsType();
		String output = ObservationReportsDAO.mostOccurringStringValue(ot, conditions);
		printOutput(output);
	}

	/**
	 * Patients with the lowest of an observation.
	 */
	private static void patientsWithLowest() {
		System.out.println("Select the following for patients with the lowest of an observation");
		List<Integer> conditions = getPatientConditions();
		ObservationType ot = getObsType();
		List<Patient> list = ObservationReportsDAO.getPatientsWithLowest(ot, conditions);
		printPatients(list);				
	}

	/**
	 * Patients with the highest of an observation.
	 */
	private static void patientsWithHighest() {
		System.out.println("Select the following for patients with the highest of an observation");
		List<Integer> conditions = getPatientConditions();
		ObservationType ot = getObsType();
		List<Patient> list = ObservationReportsDAO.getPatientsWithHighest(ot, conditions);
		printPatients(list);				
	}

	/**
	 * Highest values of an observation.
	 */
	private static void highestObs() {
		System.out.println("Select the following for the highest values of an observation.");
		List<Integer> conditions = getPatientConditions();
		ObservationType ot = getObsType();
		String output = ObservationReportsDAO.highestAmount(ot, conditions);
		printOutput(output);				
	}

	/**
	 * Lowest values of an observation.
	 */
	private static void lowestObs() {
		System.out.println("Select the following for the lowest values of an observation.");
		List<Integer> conditions = getPatientConditions();
		ObservationType ot = getObsType();
		String output = ObservationReportsDAO.lowestAmount(ot, conditions);
		printOutput(output);		
	}

	/**
	 * Average values of an observation.
	 */
	private static void avgObs() {
		System.out.println("Select the following for the average values of an observation.");
		List<Integer> conditions = getPatientConditions();
		ObservationType ot = getObsType();
		String output = ObservationReportsDAO.averageAmount(ot, conditions);
		printOutput(output);
	}

	/**
	 * Prints the output given to it in the form: 
	 * colname:value,colname:value
	 * @param output
	 */
	private static void printOutput(String output) {
		if (output == null || output == "") {
			System.out.println("No data was found for those parameters.");
			return;
		}
		String[] lines = output.split(",");
		for (int i=0; i<lines.length; i++) {
			String[] fields = lines[i].split(":");
			System.out.println(fields[0] + ":  " + fields[1]);
		}
	}
	
	/**
	 * Prints out the names of the patients passed in.
	 * @param list
	 */
	private static void printPatients(List<Patient> list) {
		if (list == null) {
			System.out.println("No data was found for those parameters.");
			return;
		}
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getLname()+ "," + list.get(i).getFname());
		}
	}

	/**
	 * Gets a list of patient conditions from the user.
	 * @return list of patient condition ids
	 */
	private static List<Integer> getPatientConditions() {
		System.out.println("Select a patient condition: ");
		List<PatientCondition> list = PatientConditionsDAO.getAllConditionTypes();
		List<Integer> conditions = new ArrayList<Integer>();
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getDescription());
		}
		while (true) {
			int choice = Utility.getValidChoice(list.size());
			conditions.add(list.get(choice).getCondition());
			System.out.println("Enter yes if you would like to select another condition: ");
			String ans = Utility.getInput();
			if (!ans.toLowerCase().startsWith("y")) {
				return conditions;
			}
			System.out.println("Enter the condition: ");
		}
	}
	
	/**
	 * Gets an Observation Type from the user.
	 * @return Observation Type
	 */
	private static ObservationType getObsType() {
		System.out.println("Select an observation type: ");
		List<ObservationType> obstypes = ObservationTypeDAO.getAllObservationTypes();
		for (int i=0; i<obstypes.size(); i++) {
			System.out.println(i + " -- " + obstypes.get(i).getDisplay_name());
		}
		int choice = Utility.getValidChoice(obstypes.size());
		return obstypes.get(choice);
	}
}


