package Models;

/**
 * This JavaBean represents a single credit card
 * @author Ben Morlok
 *
 */
public class CreditCard implements java.io.Serializable {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -1268181519562286703L;
	
	/**
	 * Members
	 */
	private String number;
	private String expiration;
	private String type;
	private String cvc;
	
	/**
	 * Default constructor
	 */
	public CreditCard()
	{
		this.number = "";
		this.expiration = "";
		this.type = "";
		this.cvc = "";
	}
	
	/**
	 * Optional constructor
	 * @param number
	 * @param expiration
	 * @param type
	 * @param cvc
	 */
	public CreditCard(String number, String expiration, String type, String cvc)
	{
		this.number = number;
		this.expiration = expiration;
		this.type = type;
		this.cvc = cvc;
	}
	
	/**
	 * Getters and setters
	 * @return
	 */
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

}
