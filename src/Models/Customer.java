package Models;

/**
 * Represents a single customer in the PC Wizard
 * @author Ben Morlok
 */
public class Customer implements java.io.Serializable{

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 4175088273076211921L;
	
	/**
	 * Members
	 */
	private String firstName;
	private String lastName;
	private String email;
	private String address1;
	private String address2;
	private String zipcode;
	private String city;
	private String state;
	
	/**
	 * Default Constructor
	 */
	public Customer()
	{
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.address1 = "";
		this.address2 = "";
		this.zipcode = "";
		this.city = "";
		this.state = "";
	}
	/**
	 * Getters and Setters
	 */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
