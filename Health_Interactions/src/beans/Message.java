package beans;

public class Message {
	private int mid = -1;
	private int from = -1;
	private int to = -1;
	private String message = null;
	private String fromFirstName = null;
	private String fromLastName = null;

	/**
	 * @return the fromFirstName
	 */
	public String getFromFirstName() {
		return fromFirstName;
	}

	/**
	 * @return the mid
	 */
	public int getMid() {
		return mid;
	}

	/**
	 * @param mid the mid to set
	 */
	public void setMid(int mid) {
		this.mid = mid;
	}

	/**
	 * @param fromFirstName the fromFirstName to set
	 */
	public void setFromFirstName(String fromFirstName) {
		this.fromFirstName = fromFirstName;
	}

	/**
	 * @return the fromLastName
	 */
	public String getFromLastName() {
		return fromLastName;
	}

	/**
	 * @param fromLastName the fromLastName to set
	 */
	public void setFromLastName(String fromLastName) {
		this.fromLastName = fromLastName;
	}

	public Message(String message) {
		this.message = message;
	}

	public Message() {

	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public int getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(int to) {
		this.to = to;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
