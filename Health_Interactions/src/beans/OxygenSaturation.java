package beans;

public class OxygenSaturation {

	private int oid = -1;
	private int type_id = -1;
	private int amount = -1;
	
	public OxygenSaturation( int amount ) {
		this.amount = amount;
		type_id = 6;
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
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	
	
}
