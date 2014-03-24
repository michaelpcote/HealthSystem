package beans;

import java.sql.Date;

public class Prescription {
	
	private int prescription_num = -1;
	private String drug_name = null;
	private int pid = -1;
	private int dosage = -1;
	private Date start = null;
	private Date end = null;
	private String fname = null;
	private String lname = null;
	private String phone = null;
	
	public Prescription() {
		
	}
	
	public Prescription( String drug_name, int dosage ) {
		this.drug_name = drug_name;
		this.dosage = dosage;
	}

	/**
	 * @return the prescription_num
	 */
	public int getPrescription_num() {
		return prescription_num;
	}

	/**
	 * @param prescription_num the prescription_num to set
	 */
	public void setPrescription_num(int prescription_num) {
		this.prescription_num = prescription_num;
	}

	/**
	 * @return the drug_name
	 */
	public String getDrug_name() {
		return drug_name;
	}

	/**
	 * @param drug_name the drug_name to set
	 */
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return the dosage
	 */
	public int getDosage() {
		return dosage;
	}

	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start.toString();
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = Date.valueOf(start);
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end.toString();
	}

	/**
	 * @param end the end to set
	 */
	public void setStartDate(Date start) {
		this.start = start;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = Date.valueOf(end);
	}
	
	/**
	 * @param end the end to set
	 */
	public void setEndDate(Date end) {
		this.end = end;
	}

	/**
	 * @param get the start date
	 */
	public Date getStartDate() {
		return start;
	}
	
	/**
	 * @param get the end date
	 */
	public Date getEndDate() {
		return end;
	}
	

	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
