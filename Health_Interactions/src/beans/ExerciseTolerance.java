package beans;

public class ExerciseTolerance {
	private int type_id = -1;
	private int oid = -1;
	private int steps = -1;
	
	public ExerciseTolerance( int steps ) {
		this.steps = steps;
		type_id = 5;
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
	 * @return the steps
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
}
