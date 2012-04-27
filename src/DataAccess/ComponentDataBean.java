package DataAccess;
import hw2.common.DataAccessLayer;
import hw2.common.LacrosseTeam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Component;
/**
 * Gets Component Data from the database
 * @author Ben Morlok
 *
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
	 * Returns a list of all the components of a specific type
	 * @param type
	 * @return
	 */
	public List<Component> getAllComponentsOfType(String type)
	{
		List<Component> outputData = new ArrayList<Component>();
		try
		{
			ResultSet res = DAO.getAllCOmponentOfType(type);
			while (res.next()) {
				outputData.add(new Component(res.getString("category"), res.getString("component_name"), "placeholder", res.getDouble("component_price")));
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
