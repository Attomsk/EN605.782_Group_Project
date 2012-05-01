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
	private String password;
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
		this.password = "";
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
	 * Constructor using fields
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param address1
	 * @param address2
	 * @param zipcode
	 * @param city
	 * @param state
	 */
	public Customer(String firstName, String lastName, String email, String password,
			String address1, String address2, String zipcode, String city,
			String state) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address1 = address1;
		this.address2 = address2;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
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
