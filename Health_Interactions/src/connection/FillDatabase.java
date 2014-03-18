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
		addObservationTypes();
		addSex();
		addConditionCategoryRelationships();
		System.out.println("Database tables inserted");
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
    				statement.executeUpdate("INSERT INTO condition_types ( description ) VALUES ( 'HIV' )");
    				statement.executeUpdate("INSERT INTO condition_types ( description ) VALUES ( 'Obesity' )");
    				statement.executeUpdate("INSERT INTO condition_types ( description ) VALUES ( 'High Risk Pregnancy' )");
    				statement.executeUpdate("INSERT INTO condition_types ( description ) VALUES ( 'COPD' )");
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
    				statement.executeUpdate("INSERT INTO observation_categories ( description ) VALUES ( 'Behavioral' )");
    				statement.executeUpdate("INSERT INTO observation_categories ( description ) VALUES ( 'Physiological' )");
    				statement.executeUpdate("INSERT INTO observation_categories ( description ) VALUES ( 'Psychological' )");
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
	
	private static void addObservationTypes() {
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
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 1, 'Diet' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 1, 'Weight' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 1, 'Exercise' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 2, 'Blood Pressure' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 2, 'Exercise Tolerance' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 2, 'Oxygen Saturation' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 2, 'Pain' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 3, 'Mood' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 2, 'Contraction' )");
    				statement.executeUpdate("INSERT INTO observation_types ( ocid, description ) VALUES ( 2, 'Temperature' )");
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
	
	private static void addConditionCategoryRelationships() {
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
    				statement.executeUpdate("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( 1, 10 )");
    				statement.executeUpdate("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( 2, 4 )");
    				statement.executeUpdate("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( 3, 4 )");
    				statement.executeUpdate("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( 3, 7 )");
    				statement.executeUpdate("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( 3, 9 )");
    				statement.executeUpdate("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( 4, 5 )");
    				statement.executeUpdate("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( 4, 6 )");
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

