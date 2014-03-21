package beans;

public class DoctorAddedObservationType {

	private int table_id = -1;
	private int pid = -1;
	private int ocid = -1;
	private String table_name = null;
	private String display_name = null;
	private String additional_info = null;
	private int number_of_columns = -1;
	private String value_choices = null;
	
	public DoctorAddedObservationType() {
		
	}

	/**
	 * @return the table_id
	 */
	public int getTable_id() {
		return table_id;
	}

	/**
	 * @param table_id the table_id to set
	 */
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
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

	/**
	 * @return the display_name
	 */
	public String getDisplay_name() {
		return display_name;
	}

	/**
	 * @param display_name the display_name to set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
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
	 * @return the number_of_columns
	 */
	public int getNumber_of_columns() {
		return number_of_columns;
	}

	/**
	 * @param number_of_columns the number_of_columns to set
	 */
	public void setNumber_of_columns(int number_of_columns) {
		this.number_of_columns = number_of_columns;
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
	
	
}
