package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Customer;
/**
 * Gets Customer Data from the database
 * @author Dave Knopp & Ben Morlok
 *
 */
public class CustomerDataBean {

	private DataAccessLayer DAO;
	/**
	 * Default Constructor
	 */
	public CustomerDataBean()
	{
		DAO = DataAccessLayer.getInstance();
	}
	
	
	/**
	 * Returns a list of all the customers with that email/password combo
	 * @param email
	 * @param password
	 * @return List<Customer>
	 */
	public List<Customer> validate(String email, String password) {
		List <Customer> outputData = new ArrayList<Customer>();
		try
		{
			// Query DB, add results to output list
			ResultSet res = DAO.validate(email, password);
			if(null != res)
			{
				while (res.next()) {
					Integer zip = res.getInt("zip");
					String zipcode = "";
					if(zip >= 0)
					{
						zipcode = zip.toString();
					}
					outputData.add(new Customer(res.getString("firstName"), res.getString("lastName"),  res.getString("email"), res.getString("password"),
							res.getString("address1"), res.getString("address2") , zipcode,  res.getString("city"), res.getString("state")));
				}
				//clean up database resources
				res.close();
			}
			DAO.closeStatement();
		}
		catch(SQLException ex)
		{
			DataAccessLayer.handleSqlExceptions(ex);
		}
		return outputData;
	}
	
	/**
	 * adds a new customer to the database, returns the id if it was successful, -1 if unsuccessful
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param zip
	 * @return int
	 */
	public int addNewUser(String email, String password, String firstName, String lastName, String address1, String address2, String city, String state, String zip)
	{
		int result = -1;
		// Query the DB
		result = DAO.insertNewUser(email, password, firstName, lastName, address1, address2, city, state, zip);
		DAO.closeStatement();
		return result;
	}
}
