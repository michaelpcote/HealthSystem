package connection;

import java.sql.*;

public class JDBCConnection {

	// Specify JDBC URL
	private static final String JDBC_URL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	// Specify database login details
	private static String userName = "mpcote";
	private static String password = "001008199";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection( JDBC_URL, userName, password );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * Close the prepared statement, the connection, and the result set properly	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs ) {
		try {
			if (ps != null)
				ps.close();
			if ( rs != null ) {
				rs.close();
			}
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.err.println("Error closing connections");
			
		}
	}
}
