package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class will fill the database with values that shall not change.
 * @author SG0214981
 *
 */
public class FillDatabase {

	public static void main(String[] args) {
		addConditions();
		addObservationCategories();
		addCategoryTypes();
		addSex();
		
	}
	
	private static void addConditions() {
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
    				statement.executeUpdate("INSERT INTO condition_types ( cid, description ) VALUES ( 1, 'HIV' )");
    				statement.executeUpdate("INSERT INTO condition_types ( cid, description ) VALUES ( 2, 'Obesity' )");
    				statement.executeUpdate("INSERT INTO condition_types ( cid, description ) VALUES ( 3, 'High Risk Pregnancy' )");
    				statement.executeUpdate("INSERT INTO condition_types ( cid, description ) VALUES ( 4, 'COPD' )");
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
	
	private static void addObservationCategories() {
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
    				statement.executeUpdate("INSERT INTO observation_categories ( ocid, description ) VALUES ( 1, 'Behavioral' )");
    				statement.executeUpdate("INSERT INTO observation_categories ( ocid, description ) VALUES ( 2, 'Physiological' )");
    				statement.executeUpdate("INSERT INTO observation_categories ( ocid, description ) VALUES ( 3, 'Psychological' )");
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
	
	private static void addCategoryTypes() {
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
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 1, 1, 'Diet' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 2, 1, 'Weight' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 3, 1, 'Exercise' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 4, 2, 'Blood Pressure' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 5, 2, 'Exercise Tolerance' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 6, 2, 'Oxygen Saturation' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 7, 2, 'Pain' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 8, 3, 'Mood' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 9, 3, 'Contraction' )");
    				statement.executeUpdate("INSERT INTO category_types ( type_id, ocid, description ) VALUES ( 10, 3, 'Temperature' )");
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
	
	private static void addSex() {
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
    				statement.executeUpdate("INSERT INTO sex ( sex_id, description ) VALUES ( 1, 'Male' )");
    				statement.executeUpdate("INSERT INTO sex ( sex_id, description ) VALUES ( 2, 'Female' )");
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

