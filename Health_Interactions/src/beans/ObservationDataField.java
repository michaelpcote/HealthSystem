package beans;

public class ObservationDataField {

	private String name;
	private String description;
	private String type;
	private String value_choices;
	
	public ObservationDataField(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	public String getValueChoices() {
		return value_choices;
	}
}
