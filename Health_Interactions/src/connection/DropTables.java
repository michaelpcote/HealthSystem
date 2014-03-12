package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTables {
	
	public static void main(String[] args) {
		try {
	        // Register JDBC Driver (Oracle Thin)
	        Connection connection = null;
	        Statement statement = null;
	        
	        	try {
	        		// Get a connection to the specified JDBC URL.
					connection = JDBCConnection.getConnection();
	                
			 		// Create a Statement object for sending SQL statements to the database.
					// Statement: The object used for executing a static SQL statement and returning the results it produces.
					statement = connection.createStatement();
					statement.executeUpdate("DROP TABLE IF EXISTS sex");
					statement.executeUpdate("DROP SEQUENCCE IF EXISTS sex_id_seq");
					statement.executeUpdate("DROP TABLE IF EXISTS sex_trigger");
					statement.executeUpdate("DROP TABLE IF EXISTS class_type");
					statement.executeUpdate("DROP TABLE IF EXISTS patient_conditions");
					statement.executeUpdate("DROP TABLE IF EXISTS observation_categories");
					statement.executeUpdate("DROP TABLE IF EXISTS category_types");
					statement.executeUpdate("DROP TABLE IF EXISTS observations");
					statement.executeUpdate("DROP TABLE IF EXISTS diet");
					statement.executeUpdate("DROP TABLE IF EXISTS weight");
					statement.executeUpdate("DROP TABLE IF EXISTS exercise");
					statement.executeUpdate("DROP TABLE IF EXISTS blood_pressure");
					statement.executeUpdate("DROP TABLE IF EXISTS oxygen_saturation");
					statement.executeUpdate("DROP TABLE IF EXISTS exercise_tolerance");
					statement.executeUpdate("DROP TABLE IF EXISTS sex_trigger");
					statement.executeUpdate("DROP TABLE IF EXISTS pain");
					statement.executeUpdate("DROP TABLE IF EXISTS mood");
					statement.executeUpdate("DROP TABLE IF EXISTS contraction");
					statement.executeUpdate("DROP TABLE IF EXISTS temperature");
					statement.executeUpdate("DROP TABLE IF EXISTS health_supporter_type");
					statement.executeUpdate("DROP TABLE IF EXISTS health_friends");
				} catch(SQLException e) {
	        		e.printStackTrace();
	    		} catch(Exception e) {
	        		e.printStackTrace();
	   	 	} finally {
				// Close resultSet, statement and connection.
				try{
	                if(statement!=null)
	                				statement.close();
					if(connection!=null)
	                				connection.close();
				} catch (Exception e){
					e.printStackTrace();
				}
	    	}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
