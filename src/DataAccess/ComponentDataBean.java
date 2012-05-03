package DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Build;
import Models.Component;
/**
 * Gets Component Data from the database
 * @author Ben Morlok
 */
public class ComponentDataBean {
	
	private DataAccessLayer DAO;
	
	/**
	 * Default Constructor
	 */
	public ComponentDataBean()
	{
		DAO = DataAccessLayer.getInstance();
	}
	
	
	/**
	 * Returns a list of all the components of a specific type compatible with a specific processor
	 * @param type
	 * @return List<Component>
	 */
	public List<Component> getAllComponentsOfType(String componentType, int processorType)
	{
		// If this is the first build state, we should be querying for processors,
		// which require a special query
		if(componentType.equals(Build.buildStates[0]))
		{
			return getAllProcessorsOfType(processorType);
		}
		
		// Not a processor, just a regular component
		List<Component> outputData = new ArrayList<Component>();
		try
		{
			// Query DB, gather results into output list
			ResultSet res = DAO.getAllComponentOfType(componentType, processorType);
			while (res.next()) {
				outputData.add(new Component(res.getString("Device"), res.getString("Component"), res.getString("Brand"), res.getDouble("Price"), res.getInt("Id")));
			}
			//clean up database resources
			res.close();
			DAO.closeStatement();
		}
		catch(SQLException ex)
		{
			DataAccessLayer.handleSqlExceptions(ex);
		}
		return outputData;
	}
	
	/**
	 * Returns a list of all the processors of a specific type
	 * @param type
	 * @return List<Component>
	 */
	public List<Component> getAllProcessorsOfType(int processorType)
	{
		List<Component> outputData = new ArrayList<Component>();
		try
		{
			// Query DB, gather results into output list
			ResultSet res = DAO.getAllProcessorsOfType(processorType);
			while (res.next()) {
				Component newCmp = new Component();
				newCmp.setId(res.getInt("processorTypeID"));
				newCmp.setBrand(Build.processorTypes[res.getInt("processorBrandID")]);
				newCmp.setName(res.getString("processorDescription"));
				newCmp.setCategory(Build.buildStates[0]);
				newCmp.setPrice(res.getInt("processorPrice"));
				outputData.add(newCmp);
			}
			//clean up database resources
			res.close();
			DAO.closeStatement();
		}
		catch(SQLException ex)
		{
			DataAccessLayer.handleSqlExceptions(ex);
		}
		return outputData;
	}
}
