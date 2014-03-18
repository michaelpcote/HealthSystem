package beans;

public class Diet {

	private String food = null;
	private int calories = -1;
	private int type_id = -1;
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

	public Diet( String food, int calories ) {
		this.food = food;
		this.calories = calories;
		this.type_id = 1;
	}
	
	public Diet() {
		this.type_id = 1;
	}

	/**
	 * @return the food
	 */
	public String getFood() {
		return food;
	}

	/**
	 * @param food the food to set
	 */
	public void setFood(String food) {
		this.food = food;
	}

	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @param calories the calories to set
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	
	
}
