package DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

	public class DataAccessLayer {
		/************************Variables************************/
		//the singleton instance of this class.
		private static DataAccessLayer instance = null;
		
		//database connection info
		private static String HOST = "localhost";
		private static int PORT = 3306;
		private static String USERNAME = "root";
		private static String PASSWORD = "";
		private static String DB_NAME = "build_a_pc_db";
		private static String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
		private static Connection CONN = null;
		
		//going to keep one statement to ensure it gets closed properly
		private Statement stmnt;
		
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
}
