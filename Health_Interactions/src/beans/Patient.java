package beans;

import java.sql.Date;

public class Patient {
	
	double pid = -1;
	String fname = null;
	String lname = null;
	String address = null;
	String city = null;
	String state = null;
	String zip = null;
	Date dob = null;
	int sex = -1;
	String publicStatus = null;
	String password = null;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	public Patient() {
	}


	public double getPid() {
		return pid;
	}


	public void setPid(double pid) {
		this.pid = pid;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getDobAsString() {
		return dob.toString();
	}
	
	public Date getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = Date.valueOf(dob);
	}


	public int getSex() {
		return sex;
	}
	
	public String getSexAsString() {
		if ( sex == 1 ) {
			return "Male";
		} else {
			return "Female";
		}
	}


	public void setSex(String sex) {
		if ( sex.equalsIgnoreCase("Male")) {
			this.sex = 1;
		} else {
			this.sex = 2;
		}
	}


	public String getPublicStatus() {
		return publicStatus;
	}


	public void setPublicStatus(String publicStatus) {
		this.publicStatus = publicStatus;
	}


	public void setDob(Date date) {
		this.dob = date;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}
}
