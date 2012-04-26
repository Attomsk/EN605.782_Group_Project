package Models;

/**
 * This JavaBean represents a single computer component
 * @author Ben Morlok
 *
 */
public class Component implements java.io.Serializable{

	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -435324491161792848L;
	
	/**
	 * Members
	 */
	private String category;
	private String name;
	private String brand;
	private double price;
	
	/**
	 * Default Constructor
	 */
	public Component()
	{
		this.category = "";
		this.name = "";
		this.brand = "";
		this.price = 0;
	}
	
	/**
	 * Optional constructor
	 * @param category
	 * @param name
	 * @param brand
	 * @param price
	 */
	public Component(String category, String name, String brand, double price)
	{
		this.category = category;
		this.name = name;
		this.brand = brand;
		this.price = price;
	}
	
	/**
	 * Getters and Setters
	 */
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
