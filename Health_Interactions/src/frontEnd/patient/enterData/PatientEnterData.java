package frontEnd.patient.enterData;

import java.util.List;

import dao.oracle.ObservationDAO;
import dao.oracle.ObservationTypeDAO;
import beans.Observation;
import beans.ObservationDataField;
import beans.ObservationType;
import beans.Patient;
import frontEnd.utility.Utility;

/**
 * Patient enters data.
 * @author nelc
 *
 */
public class PatientEnterData {

	/**
	 * Prompts the user for and adds to the database:  the ObservationType requested 
	 * and the Observation data. 
	 * @param Patient that is logged and will be prompted.
	 */
	public static void drive(Patient patient) {
		ObservationType ot = getObservationType(patient);
		Observation obs = createObservation(ot, patient.getPid());
		String observations = makeObsString(obs, ot);
		
		updateDB(patient, obs, ot, observations);
	}
		
	// when creating an observation to be passed in, what fields need to be added by me via setters?
	// right now, only pid and type_id
	/**
	 * Creates the basics of the Observation to be added.  This includes using the constructor
	 * to input all meta information, and adding the pid and the type id.
	 * @param ObservationType of observation being made
	 * @param pid of patient
	 * @return skeleton Observation, needs only field data
	 */
	private static Observation createObservation(ObservationType ot, double pid) {
		Observation obs = new Observation();
		obs.setPid(pid);
		obs.setType_id(ot.getType_id());
		obs.setDate_Observed(Utility.getDate("observation"));
		return obs;
	}

	/**
	 * Creates the observation value string that contains all the field data
	 * information.
	 * @param obs
	 * @param ot
	 * @return the observation value string
	 */
	private static String makeObsString(Observation obs, ObservationType ot) {
		System.out.println("Please enter the following data in the correct form:");
		String ret = "";
		List<ObservationDataField> fields = Utility.getFields(ot);
		for (int i=0; i<fields.size(); i++) {
			ObservationDataField field = fields.get(i);
			System.out.println(field.getDescription() + "(" + field.getType() + "):  ");
			
			// gets input, loops if requesting an int but did not get one.
			while (true) {
				String input = Utility.getInput();
				if (!(field.getType() == "int" && !Utility.isInt(input))) {
					if (i != 0) {
						ret += ",";
					}
					ret += field.getName();
					ret += ":";
					ret += input;
					break;
				}
			}
		}
		return ret;
	}

	/**
	 * Interacts with user and returns the ObservationType of his choosing.
	 * @return ObservationType of the observation to be added.
	 */
	private static ObservationType getObservationType(Patient patient) {
		List<ObservationType> list = ObservationTypeDAO.getObservationTypesForPatient(patient);
		System.out.println("Please select the Observation Type of the observation you would like to add.\n");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i + " -- " + list.get(i).getDisplay_name());
		}
		int choice = Utility.getValidChoice(list.size());
		return list.get(choice);
	}

	/**
	 * After the Observation has been successfully created, inputs it into the database.
	 * @param patient
	 * @param obs
	 * @param ot
	 * @param observations
	 */
	private static void updateDB(Patient patient, Observation obs, ObservationType ot, String observations) {
		ObservationDAO.addPatientObservation(patient, obs, ot, observations);
	}
}