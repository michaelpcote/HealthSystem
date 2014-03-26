package frontEnd.healthPro;

import frontEnd.utility.Utility;

public class ProViewAgrrReport {
	
	public static void drive() {
		System.out.println("Enter the kind of report you would like to view: ");
		System.out.println("0 -- Average of an observation");
		System.out.println("1 -- Lowest of an observation");
		System.out.println("2 -- Highest of an observation");
		System.out.println("3 -- Get patients with highest of an observation");
		System.out.println("4 -- Get patients with lowest of an observation");
		System.out.println("5 -- Most occuring mood");
		System.out.println("6 -- Least occuring mood");
		System.out.println("7 -- Number of observations made by a patient");
		System.out.println("8 -- Number of observations made of an observation type");
		System.out.println("9 -- Observations during a period");
		System.out.println("10 -- Observations for a patient");
		
		int choice = Utility.getValidChoice(10);
		
		if (choice == 1) {
			
		} else if (choice == 2) {
			
		} else if (choice == 3) {
			
		} else if (choice == 4) {
			
		} else if (choice == 5) {
			
		} else if (choice == 6) {
			
		} else if (choice == 7) {
			
		} else if (choice == 8) {
			
		} else if (choice == 9) {
			
		} else if (choice == 10) {
			
		}
	}
}


