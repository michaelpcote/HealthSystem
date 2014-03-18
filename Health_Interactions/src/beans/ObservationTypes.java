package beans;

public class ObservationTypes {
	private int type_id = -1;
	private int ocid = -1;
	private String description = null;
	
	public ObservationTypes() {
		
	}
	
	public ObservationTypes( String description ) {
		this.description = description;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}

	/**
	 * @param type_id the type_id to set
	 */
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	/**
	 * @return the ocid
	 */
	public int getOcid() {
		return ocid;
	}

	/**
	 * @param ocid the ocid to set
	 */
	public void setOcid(int ocid) {
		this.ocid = ocid;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
