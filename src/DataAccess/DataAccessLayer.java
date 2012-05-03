package DataAccess;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * Singleton pattern class that handles executing queries and directly interfacing with the database
 * @author Ben Morlok & Inshan Singh
 */
public class DataAccessLayer {
		/************************Variables************************/
		//the singleton instance of this class.
		private static DataAccessLayer instance = null;
		
		//database connection info
		private static String HOST = "localhost";
		private static int PORT = 3306;
		private static String USERNAME = "root";
		private static String PASSWORD = "";
		private static String DB_NAME = "db_build_a_pc";
		private static String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
		private static Connection CONN = null;
		
		//going to keep one statement to ensure it gets closed properly
		private PreparedStatement stmnt;
		
		/************************Constructors************************/
		protected DataAccessLayer(){
			//ensure that the JDBC connector exists.
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not locate JDBC driver");
			}
			//attempt database connection
			dBConnect();
		}
		
		protected void finalize() throws Throwable {
			closeDbConnection();
		}
		/************************Private Methods************************/
		
		//attempts to connect to the database
		private static void dBConnect(){
			try {
				CONN = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException ex) {
				handleSqlExceptions(ex);
			} catch (Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
		}
		
		/** This code was found at:
		 * http://aquaryus.wordpress.com/2012/01/19/step-up-your-password-protection-in-java-with-sha1-encryption/
		 * SHA1 encrypts a string
		 * @param input
		 * @return
		 * @throws NoSuchAlgorithmException
		 */
		private String makeSHA1Hash(String input) throws NoSuchAlgorithmException {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.reset();
			byte[] buffer = input.getBytes();
			md.update(buffer);
			byte[] digest = md.digest();
			String hexStr = "";
			for (int i = 0; i < digest.length; i++) {
				hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
			}
			return hexStr;
		}
		
		/************************Public Methods************************/
		// handle any SQL errors (copied from your sample code - it wasn't worth re-writing)
		public static void handleSqlExceptions(SQLException ex)
		{
			while (ex != null) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
				ex = ex.getNextException();
			}
		}
		
		//returns the instance of this class
		public static synchronized DataAccessLayer getInstance(){
				if (null == instance)
				{
					instance = new DataAccessLayer();
				}
				return instance;
		}
		
		//closes the connection to to the database.  Must be called before this class's final use.
		public void closeDbConnection()
		{
			try {
				CONN.close();
			} catch (Exception e) {
				CONN = null;
			}
		}
		
		//closes the currently opened statement
		//this should be called after any database action is performed with this class
		public void closeStatement(){
			try
			{
				stmnt.close();
			}
			catch (SQLException ex)
			{
				handleSqlExceptions(ex);
			}
		}
		
		/************************Queries************************/
		
		// This will return all components that are of a certain category compatible with a certain processor type
		public ResultSet getAllComponentOfType(String category, int processorType){
			ResultSet res = null;
			try{
				String query = "Select componentId as 'Id', componentDescription as 'Component', componentPrice as 'Price',"+ 
						" componentCategoryName as 'Device', componentBrandName as 'Brand' from components" +
						" inner join component_category on" +
						" components.componentCategoryID = component_category.componentCategoryID" +
						" inner join component_brand on" +
						" components.componentBrandID = component_brand.componentBrandID" +
						" where componentCategoryName=?" +
						" and (compatibleWithID =? OR compatibleWithID = 3) ORDER BY componentPrice";
				stmnt = CONN.prepareStatement(query);
				// Bind Parameters
				stmnt.setString(1, category);
				stmnt.setInt(2, processorType);
				res = stmnt.executeQuery();
			}
			catch (SQLException ex) {
				handleSqlExceptions(ex);			
			}
			return res;
		}
		
		// This will return all processors that are of a certain type
		public ResultSet getAllProcessorsOfType(int processorType)
		{
			ResultSet res = null;
			try{
				String query = "SELECT * FROM processor_types WHERE processorBrandID=?";
				stmnt = CONN.prepareStatement(query);
				// Bind Parameters
				stmnt.setInt(1, processorType);
				res = stmnt.executeQuery();
			}
			catch (SQLException ex) {
				handleSqlExceptions(ex);			
			}
			return res;
		}	
		
		// This will return all users matching the email and password combination
		public ResultSet validate(String email, String password) {
			ResultSet res = null;
			try {
				// hash password with SHA1 algorithm
				String hashPass = makeSHA1Hash(password);
				String query = "SELECT * FROM customer where email=? and password=?";
				stmnt = CONN.prepareStatement(query);
				// Bind Parameters
				stmnt.setString(1, email);
				stmnt.setString(2, hashPass);
				res = stmnt.executeQuery();
			} catch (SQLException ex) {
				
				handleSqlExceptions(ex);			
			} catch (NoSuchAlgorithmException nsae){}
			return res;
		}
		
		// Adds a new user to the database
		public int insertNewUser(String email, String password, String firstName, String lastName, String address1, String address2, String city, String state, String zip)
		{
			int newId = -1;
			try {
				// hash password with SHA1 algorithm
				String hashPass = makeSHA1Hash(password);
				// Convert zip code to integer
				int zipcode = -1;
				try{
					zipcode = Integer.parseInt(zip);
				} catch(NumberFormatException nfe){
					zipcode = -1;
				}
				String query = "INSERT INTO customer(firstName, lastName, email, password, address1, address2, city, zip, state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
				// Bind Parameters
				stmnt = CONN.prepareStatement(query);
				stmnt.setString(1, firstName);
				stmnt.setString(2, lastName);
				stmnt.setString(3, email);
				stmnt.setString(4, hashPass);
				stmnt.setString(5, address1);
				stmnt.setString(6, address2);
				stmnt.setString(7, city);
				stmnt.setInt(8, zipcode);
				stmnt.setString(9, state);
				newId = stmnt.executeUpdate();
			} catch (SQLException ex) {
				handleSqlExceptions(ex);			
			} catch (NoSuchAlgorithmException nsae){}
			return newId;
		}
}
	
	
