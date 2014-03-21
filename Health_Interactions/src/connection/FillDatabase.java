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
    				String insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 1, 'Diet', 'What  was  consumed, amount'";
    				insert += ", '0')";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 1, 'Weight', 'Amount', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 1, 'Exercise', 'What kind, duration', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 2, 'Blood Pressure', 'Systolic, Diastolic', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 2, 'Exercise Tolerance', 'Number of steps before";  
    				insert += "exhaustion', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 2, 'Oxygen Saturation', 'Amount', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 2, 'Pain', 'Scale [1-10]', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 3, 'Mood', 'One of the values ";
    				insert += "{Happy, Sad, Neutral}', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 2, 'Contraction', 'Frequency - #per ";
    				insert += "half hour', '0' )";
    				statement.executeUpdate(insert);
    				insert = "INSERT INTO observation_types ( ocid, description, additional_info, doctor_created ) VALUES ( 2, 'Temperature', 'Amount', '0' )";
    				statement.executeUpdate(insert);
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

