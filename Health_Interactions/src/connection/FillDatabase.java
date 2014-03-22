package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dao.oracle.ObservationTypeDAO;
import beans.ObservationType;

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
		System.out.println("Database tables filled");
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
	
	public static void addObservationTypes() {
		
		ObservationType ot = new ObservationType();
    	ot.setAdditional_info("What  was  consumed, amount");
    	ot.setColumn_names_types("food_type:String,calories:int");
    	ot.setOcid(1);
    	ot.setDisplay_name("Diet");
    	ot.setTable_name("diet");
    	ObservationTypeDAO.addNewObservationType(ot);
    				
    	//Add a weight observation
		ot = new ObservationType();
		ot.setAdditional_info("Amount");
		ot.setColumn_names_types("weight:int");
		ot.setOcid(1);
		ot.setDisplay_name("Weight");
		ot.setTable_name("weight");
		ObservationTypeDAO.addNewObservationType(ot);			
    				
		//Add an exercise observation
		ot = new ObservationType();
		ot.setAdditional_info("What kind, duration");
		ot.setColumn_names_types("exercise_type:String,minutes:int");
		ot.setOcid(1);
		ot.setDisplay_name("Exercise");
		ot.setTable_name("exercise");
		ObservationTypeDAO.addNewObservationType(ot);
		
		//Add a blood pressure observation
		ot = new ObservationType();
		ot.setAdditional_info("Systolic, Diastolic");
		ot.setColumn_names_types("systolic:int,diastolic:int");
		ot.setOcid(2);
		ot.setDisplay_name("Blood Pressure");
		ot.setTable_name("blood_pressure");
		ObservationTypeDAO.addNewObservationType(ot);
		
		//Add a exercise tolerance observation
		ot = new ObservationType();
		ot.setAdditional_info("Number of steps before exhaustion");
		ot.setColumn_names_types("steps:int");
		ot.setOcid(2);
		ot.setDisplay_name("Exercise Tolerance");
		ot.setTable_name("exercise_tolerance");
		ObservationTypeDAO.addNewObservationType(ot);
		
		//Add an oxygen saturation observation
		ot = new ObservationType();
		ot.setAdditional_info("Amount");
		ot.setColumn_names_types("percentage:int");
		ot.setOcid(2);
		ot.setDisplay_name("Oxygen Saturation");
		ot.setTable_name("oxygen_saturation");
		ObservationTypeDAO.addNewObservationType(ot);
		
		//Add a pain observation
		ot = new ObservationType();
		ot.setAdditional_info("Amount");
		ot.setColumn_names_types("rating:int");
		ot.setOcid(2);
		ot.setDisplay_name("Pain");
		ot.setTable_name("pain");
		ot.setValue_choices("rating:int:1,10");
		ObservationTypeDAO.addNewObservationType(ot);
		
		//Add a mood observation
		ot = new ObservationType();
		ot.setAdditional_info("One of the values {Happy, Sad, Neutral}");
		ot.setColumn_names_types("mood:String");
		ot.setOcid(3);
		ot.setDisplay_name("Mood");
		ot.setTable_name("mood");
		ot.setValue_choices("mood:String:Happy,Sad,Neutral");
		ObservationTypeDAO.addNewObservationType(ot);
		
		//Add a contraction observation
		ot = new ObservationType();
		ot.setAdditional_info("Frequency - #per half hour");
		ot.setColumn_names_types("frequency:int");
		ot.setOcid(2);
		ot.setDisplay_name("Contraction");
		ot.setTable_name("contraction");
		ObservationTypeDAO.addNewObservationType(ot);
		
		//Add a temperature observation
		ot = new ObservationType();
		ot.setAdditional_info("Amount");
		ot.setColumn_names_types("temp:int");
		ot.setOcid(2);
		ot.setDisplay_name("Temperature");
		ot.setTable_name("temperature");
		ObservationTypeDAO.addNewObservationType(ot);			
    				
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

