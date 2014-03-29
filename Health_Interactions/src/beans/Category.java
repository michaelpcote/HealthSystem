package beans;

public class Category {

	private int ocid = -1;
	private String description = null;
	
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

	public Category() {
		
	}
	
	
}
