package beans;

public class Physicians {
	
	private int pid = -1;
	private String fname = null;
	private String lname = null;
	private String clinic = null;
	private String pw = null;
	
	public Physicians() {
		
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
	 * @return the clinic
	 */
	public String getClinic() {
		return clinic;
	}

	/**
	 * @param clinic the clinic to set
	 */
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	/**
	 * @return the pw
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * @param pw the pw to set
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
