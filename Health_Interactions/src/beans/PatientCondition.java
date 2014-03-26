package beans;

public class PatientCondition {
	private int condition;
	private String description;
	
	/**
	 * An empty constructor
	 */
	public PatientCondition() {
		
	}
	
	public PatientCondition( String description ) {
		this.description = description;
	}

	/**
	 * @return the condition
	 */
	public int getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(int condition) {
		this.condition = condition;
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
