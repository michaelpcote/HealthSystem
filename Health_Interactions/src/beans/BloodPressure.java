package beans;

public class BloodPressure {
	private int type_id = -1;
	private int systolic = -1;
	private int diastolic = -1;
	private int oid = -1;
	
	public BloodPressure( int systolic, int diastolic ) {
		this.systolic = systolic;
		this.diastolic = diastolic;
		type_id = 4;
	}

	public BloodPressure() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the systolic
	 */
	public int getSystolic() {
		return systolic;
	}

	/**
	 * @param systolic the systolic to set
	 */
	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}

	/**
	 * @return the diastolic
	 */
	public int getDiastolic() {
		return diastolic;
	}

	/**
	 * @param diastolic the diastolic to set
	 */
	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
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
