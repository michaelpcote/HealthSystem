package frontEnd.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.ObservationDataField;
import beans.ObservationType;

public class Utility {

	static Scanner scan = new Scanner(System.in);
	
	public static int getValidChoice(int size) {
		while (true) {
			try {
				int choice = scan.nextInt();
				scan.nextLine();
				if (choice >= 0 && choice < size) {
					return choice;
				}
				else {
					System.out.println("Invalid number... please select again.");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid input... please select again.");
			}
		}
	}
	
	public static String getInput() {
		return scan.nextLine();
	}
		
	public static List<ObservationDataField> getFields(ObservationType ot) {
		String[] cols_types = ot.getColumn_names_types().split(",");
		String[] descriptions = ot.getAdditional_info().split(",");
		List<ObservationDataField> list = new ArrayList<ObservationDataField>();
		for (int i=0; i<cols_types.length; i++) {
			String temp[] = cols_types[i].split(":");
			String name = temp[0];
			String type = temp[1];
			String description = descriptions[i];
			ObservationDataField field = new ObservationDataField(name, description, null, type);
			list.add(field);
		}
		return list;
	}

	public static boolean isInt(String input) {
		try {
			Scanner scan = new Scanner(input);
			scan.nextInt();
			scan.close();
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets a date from the user.
	 * @param the date the user wants
	 * @return
	 */
	public static String getDate(String when) {
		while (true) {
			System.out.println("Enter the " + when + " date (in form yyyy-mm-dd): ");
			String date = getInput();
			if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return date;
			}
			else {
				System.out.println("Incorrect date format \""+date+"\" (need yyyy-mm-dd).");
			}
		}
	}
}
