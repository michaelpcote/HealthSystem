package frontEnd.patient.enterData;

import java.util.ArrayList;
import java.util.List;

import dao.oracle.ObservationTypeDAO;
import dao.oracle.PatientConditionsDAO;
import frontEnd.utility.Utility;
import beans.ObservationDataField;
import beans.ObservationType;
import beans.PatientCondition;

/**
 * Patient adds an observation type
 * @author cmnelso5
 *
 */
public class PatientAddObservationType {

	/**
	 * Driver to allow a patient to add an observation type.
	 */
	public static void drive() {
		ObservationType ot = getInfo();
		
		updateDB(ot);
	}

	/**
	 * Updates the database with the newly created observation type.
	 * @param Observation Type to be added.
	 */
	private static void updateDB(ObservationType ot) {
		ObservationTypeDAO.addNewObservationType(ot);
	}

	/**
	 * Gets information from the user to build the ObservationType object.
	 * @return ObservationType that is created.
	 */
	private static ObservationType getInfo() {
		String display_name; 			//user view 	"Diet"
		String table_name; 				//db view 		"diet"
		String additional_info; 		//user view 	"What food,amount"
		String column_names_types;		//db_view 		"food_type:String,calories:int"
		String value_choices = "";		//threshold  	"calories:int:0,200"
		int ocid;						//obs. category
		
		//display name, table name
		display_name = getDisplayName();
		table_name = getDbName(display_name);
		
		//Observation category
		ocid = getCategory();
		
		//field info
		List<ObservationDataField> fields = getFieldInfo();		//additional_info, col_names_types
		
		//value_choices
		for (int i=0; i<fields.size(); i++) {
			if (fields.get(i).getValueChoices() != null) {
				value_choices = fields.get(i).getValueChoices();
				break;
			}
		}
		
		//col_name_types, add_info
		column_names_types = getNamesTypes(fields);
		additional_info = getAddInfo(fields);
		
		ObservationType ot = new ObservationType();
		ot.setAdditional_info(additional_info);
		ot.setColumn_names_types(column_names_types);
		ot.setDisplay_name(display_name);
		ot.setTable_name(table_name);
		ot.setValue_choices(value_choices);
		ot.setOcid(ocid);
		
		System.out.println("add_info -- " +additional_info);
		System.out.println("value_choices -- " + value_choices);
		System.out.println("display_name -- " + display_name);
		System.out.println("table_name -- " + table_name);
		System.out.println("cols_names_types -- " + column_names_types);
		
		return ot;
	}

	private static int getCategory() {
		List<PatientCondition> list = PatientConditionsDAO.getAllConditionTypes();
		System.out.println("Enter the Patient Conditoin you would like to add the Observation Type under: ");
		for (int i=0; i<list.size(); i++) {
			System.out.println(i +" -- "+ list.get(i).getDescription());
		}
		return Utility.getValidChoice(list.size());		
	}

	/**
	 * Gets the additional_info ("What was eaten,amount") from the list of ObservationDataFields passed in.
	 * @param list of ObservationDataFields of the ObservationType.
	 * @return String to be put in the Observation Type to be added.
	 */
	private static String getAddInfo(List<ObservationDataField> fields) {
		String add_info = "";
		for (int i=0; i<fields.size(); i++) {
			ObservationDataField field = fields.get(i);
			String info = field.getDescription();
			if (i != 0) {
				add_info += ",";
			}
			add_info += info;
		}
		return add_info;
	}

	/**
	 * Gets the col_name_type ("food_type:String,calories:int") from the list of ObservationDataFields passed in.
	 * @param list of ObservationDataFields of the Observation Type
	 * @return String to be put in Observation Type to be added.
	 */
	private static String getNamesTypes(List<ObservationDataField> fields) {
		String col_name_type = "";
		for (int i=0; i<fields.size(); i++) {
			ObservationDataField field = fields.get(i);
			String col_name = field.getName();
			String type = field.getType();
			if (i != 0) {
				col_name_type += ",";
			}
			col_name_type += col_name;
			col_name_type += ":";
			col_name_type += type;
		}
		return col_name_type;
	}

	/**
	 * Gets info from the patient on what kind of fields should be added along with the 
	 * Observation Type.
	 * @return list of ObservationDataFields
	 */
	private static List<ObservationDataField> getFieldInfo() {
		List<ObservationDataField> fields = new ArrayList<ObservationDataField>();
		
		int i=0;
		while (true) {
			if (i != 0) {
				System.out.println("Enter yes to enter another field for this observation type: ");
				String answer = Utility.getInput();
				if (!answer.toLowerCase().startsWith("y")) {
					return fields;
				}
			}
			System.out.println("Enter the field name");
			String add_info_field = Utility.getInput();
			String col_name_field = getDbName(add_info_field);
			
			System.out.println("Enter yes if this is an integer input (else will be String): ");
			String line = Utility.getInput();
			String type;
			boolean isInt;
			if (line.toLowerCase().startsWith("y")) {
				type = "int";
				isInt = true;
			}
			else {
				type = "String";
				isInt = false;
			}
			
			String value_choice = "";
			System.out.println("Does this field have a threshhold value? ");
			if (Utility.getInput().toLowerCase().startsWith("y")) {
				value_choice += col_name_field;
				value_choice += ":";
				value_choice += type;
				value_choice += ":";
				value_choice += getValueChoice(isInt);
			}
			
			ObservationDataField field = new ObservationDataField(col_name_field, add_info_field, type, value_choice);
			fields.add(field);
			i++;
		}
	}

	/**
	 * Gets the 3rd section of "calories:int:0,200" or "mood:String:Happy,Sad,Confused"
	 * @param isInt if the type is an integer, else is string
	 * @return 3rd section of string.
	 */
	private static String getValueChoice(boolean isInt) {
		String ret = "";
		if (isInt) {
			System.out.println("Enter the minimum: ");
			ret += Utility.getValidChoice(214748364);
			ret +=",";
			System.out.println("Enter the maximum: ");
			ret += Utility.getValidChoice(214748364);
			return ret;
		}
		else {
			System.out.println("Enter a comma seperated list of possible values without spaces after the comma (Happy,Sad,Confused)");
			return Utility.getInput();
		}
	}

	/**
	 * Turns a user-entered string into a String all lower case and without whitespace.
	 * @param display_name
	 * @return String good for db usage.
	 */
	private static String getDbName(String display_name) {
		String name = display_name.replaceAll(" ", "_").toLowerCase();
		return name;
	}

	/**
	 * Gets from the user the name of the Observation Type to be added.
	 * @return the String.
	 */
	private static String getDisplayName() {
			System.out.println("Enter the name of the observation: ");
			return Utility.getInput();
	}
}