package beans;

public class ObservationType {
	private int type_id = -1;
	private int ocid = -1;
	private String display_name = null;
	private String additional_info = null;
	private String column_names_types = null;
	private String value_choices = null;
	private String table_name = null;
	
	
	public ObservationType() {
		
	}
	
	public ObservationType( String display_name ) {
		this.display_name = display_name;
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
	public String getDisplay_name() {
		return display_name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDisplay_name(String description) {
		this.display_name = description;
	}

	/**
	 * @return the additional_info
	 */
	public String getAdditional_info() {
		return additional_info;
	}

	/**
	 * @param additional_info the additional_info to set
	 */
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}

	/**
	 * @return the column_names_types
	 */
	public String getColumn_names_types() {
		return column_names_types;
	}

	/**
	 * @param column_names_types the column_names_types to set
	 */
	public void setColumn_names_types(String column_names_types) {
		this.column_names_types = column_names_types;
	}

	/**
	 * @return the value_choices
	 */
	public String getValue_choices() {
		return value_choices;
	}

	/**
	 * @param value_choices the value_choices to set
	 */
	public void setValue_choices(String value_choices) {
		this.value_choices = value_choices;
	}

	/**
	 * @return the table_name
	 */
	public String getTable_name() {
		return table_name;
	}

	/**
	 * @param table_name the table_name to set
	 */
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	

}
