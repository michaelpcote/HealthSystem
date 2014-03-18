package beans;

public class Temperature {
	private int temp = -1;
	private int oid = -1;
	private int type_id = -1;
	
	/**
	 * Constructor for Temperature
	 * @param temp must be a double with no more than one decimal places.
	 */
	public Temperature( double temp ) {
		this.temp = (int) temp*10;
		type_id = 10;
	}

	/**
	 * @return the temp
	 */
	public double getTemp() {
		return temp / 10;
	}

	/**
	 * @param temp the temp to set
	 */
	public void setTemp(int temp) {
		this.temp = temp * 10;
	}

	/**
	 * @return the oid
	 */
	public int getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(int oid) {
		this.oid = oid;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	
	
}
