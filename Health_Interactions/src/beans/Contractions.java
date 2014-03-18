package beans;

public class Contractions {

	private int type_id = -1;
	private int oid = -1;
	private int frequency = -1;
	
	public Contractions( int frequency ) {
		this.frequency = frequency;
		type_id = 9;
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
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	
	
}
