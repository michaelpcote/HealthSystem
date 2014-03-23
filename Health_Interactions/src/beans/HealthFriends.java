package beans;

import java.sql.Date;

/**
 * A class to create a health friend
 * @author SG0214981
 *
 */
public class HealthFriends {
	int patientID = -1;
	int friendID = -1;
	Date dateAdded = null;
	
	/**
	 * The constructor will create the date Added.
	 */
	public HealthFriends() {
		dateAdded = new Date( System.currentTimeMillis());
	}
	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return patientID;
	}
	/**
	 * @param patientID the patientID to set
	 */
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	/**
	 * @return the friendID
	 */
	public int getFriendID() {
		return friendID;
	}
	/**
	 * @param friendID the friendID to set
	 */
	public void setFriendID(int friendID) {
		this.friendID = friendID;
	}
	/**
	 * @return the dateAdded
	 */
	public Date getDateAdded() {
		return dateAdded;
	}
	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public void setDateAdded(String dateAdded) {
		this.dateAdded = Date.valueOf(dateAdded);
	}
	
	public String getDateAddedAsString() {
		return dateAdded.toString();
	}
}
