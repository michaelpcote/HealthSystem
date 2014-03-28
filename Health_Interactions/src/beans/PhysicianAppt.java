package beans;

import java.sql.Date;

public class PhysicianAppt {

	private int phy_id = -1;
	private int pid = -1;
	private String appt_date = null;
	private int hour = -1;
	private int minutes = -1;
	
	public PhysicianAppt() {
		
	}

	/**
	 * @return the phy_id
	 */
	public int getPhy_id() {
		return phy_id;
	}

	/**
	 * @param sid the phy_id to set
	 */
	public void setPhy_id(int phy_id) {
		this.phy_id = phy_id;
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
		return appt_date;
	}

	/**
	 * @param appt_date2 the appt_date to set
	 */
	public void setAppt_date(String appt_date2) {
		this.appt_date = appt_date2;
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
	
	
}