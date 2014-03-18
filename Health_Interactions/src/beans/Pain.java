package beans;

public class Pain {

	private int oid = -1;
	private int type_id = -1;
	private int rating = -1;
	
	public Pain( int rating ) {
		this.rating = rating;
		type_id = 7;
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
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	
	
}
