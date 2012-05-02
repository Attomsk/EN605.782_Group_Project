package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Customer;
/**
 * Gets Customer Data from the database
 * @author Dave Knopp
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
	 * @return
	 */
	public List<Customer> validate(String email, String password) {
		List <Customer> outputData = new ArrayList<Customer>();
		try
		{
			ResultSet res = DAO.validate(email, password);
			if(null != res)
			{
				while (res.next()) {
					outputData.add(new Customer(res.getString("firstName"), res.getString("lastName"),  res.getString("email"), res.getString("password"),
							res.getString("address1"), res.getString("address2") , res.getString("city"), res.getString("zip"), res.getString("state")));
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
}
