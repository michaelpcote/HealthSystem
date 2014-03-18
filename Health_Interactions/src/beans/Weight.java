package beans;

public class Weight {
	private int type_id = -1;
	private int weight = -1;
	private int oid = -1;
	
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

	public Weight( int weight ) {
		this.weight = weight;
		type_id = 2;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	
	
}
