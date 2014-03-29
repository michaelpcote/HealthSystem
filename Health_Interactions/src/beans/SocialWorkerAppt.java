package beans;

import java.sql.Date;

public class SocialWorkerAppt {

	private int sid = -1;
	private int pid = -1;
	private Date appt_date = null;
	private int hour = -1;
	private int minutes = -1;
	
	public SocialWorkerAppt() {
		
	}

	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
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
	 * @return the appt_date
	 */
	public String getAppt_date() {
		return appt_date.toString();
	}

	/**
	 * @param appt_date2 the appt_date to set
	 */
	public void setAppt_date(String appt_date2) {
		this.appt_date = Date.valueOf(appt_date2);
	}

	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
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

	public void setTime(String string) {
		String[] time = string.split(":");
		hour = Integer.valueOf(time[0]);
		minutes = Integer.valueOf(time[1]);
	}

	public Date geDatetAppt_date() {
		return appt_date;
	}
	
	public Date setDateAppt_date(Date date) {
		return appt_date = date;
	}
	
	
}
