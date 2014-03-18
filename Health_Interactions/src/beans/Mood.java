package beans;

public class Mood {

	private int oid = -1;
	private int type_id = -1;
	String mood = null;
	
	public Mood( String mood ) {
		this.mood = mood;
		type_id = 8;
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
	 * @return the mood
	 */
	public String getMood() {
		return mood;
	}

	/**
	 * @param mood the mood to set
	 */
	public void setMood(String mood) {
		this.mood = mood;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	
	
}
