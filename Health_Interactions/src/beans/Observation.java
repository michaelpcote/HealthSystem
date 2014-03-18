package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;

public class Observation {
	private double oid = -1;
	private double pid = -1;
	private Date date_observed = null;
	private Date date_recorded = null;
	private int hours = -1;
	private int minutes = -1;
	private String time_recorded = null;
	
	/**
	 * @return the time_recorded
	 */
	public String getTime_recorded() {
		return time_recorded;
	}

	public Observation() {
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		DateFormat formater = new SimpleDateFormat("hh:mm");
		date_recorded = new Date( System.currentTimeMillis());
		time_recorded = formater.format(date);
	}

	/**
	 * @return the date_recorded
	 */
	public Date getDate_recorded() {
		return date_recorded;
	}

	/**
	 * @return the oid
	 */
	public double getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(double oid) {
		this.oid = oid;
	}

	/**
	 * @return the pid
	 */
	public double getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(double pid) {
		this.pid = pid;
	}

	/**
	 * @return the date_observed
	 */
	public Date getDate_observed() {
		return date_observed;
	}

	/**
	 * @param dateObserved the date_observed to set
	 */
	public void setDate_Observed(Date dateObserved) {
		this.date_observed = dateObserved;
	}
	
	public void setStringDateObservered(String date) {
		this.date_observed = Date.valueOf(date);
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setDate_Observed(String date) {
		this.date_observed = Date.valueOf(date);
		
	}

	
}
