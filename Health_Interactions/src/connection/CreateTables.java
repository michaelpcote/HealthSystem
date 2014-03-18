package connection;

import java.sql.*;

public class CreateTables {
	 
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
    				
    				//DEPENDENT TABLES
    				//These have to be dropped first
    				statement.executeUpdate("DROP TABLE alerts");
    				statement.executeUpdate("DROP TABLE cond_obser_relationships");
					statement.executeUpdate("DROP TRIGGER observations_trigger");
					statement.executeUpdate("DROP TRIGGER cat_type_trigger");
    				statement.executeUpdate("DROP TRIGGER obs_categories_trigger");
					statement.executeUpdate("DROP TRIGGER conditions_trigger");
					statement.executeUpdate("DROP TRIGGER patient_trigger");
    				statement.executeUpdate("DROP TABLE health_supporter");
    				statement.executeUpdate("DROP TABLE health_friends");
    				statement.executeUpdate("DROP TABLE temperature");
    				statement.executeUpdate("DROP TABLE contraction");
    				statement.executeUpdate("DROP TABLE mood");
    				statement.executeUpdate("DROP TABLE pain");
    				statement.executeUpdate("DROP TABLE oxygen_saturation");
    				statement.executeUpdate("DROP TABLE exercise_tolerance");
    				statement.executeUpdate("DROP TABLE blood_pressure");
    				statement.executeUpdate("DROP TABLE exercise");
    				statement.executeUpdate("DROP TABLE weight");
					statement.executeUpdate("DROP TABLE diet");
    				statement.executeUpdate("DROP TABLE observations");
    				statement.executeUpdate("DROP TABLE patient_conditions");
    				statement.executeUpdate("DROP TABLE patients");
    				statement.executeUpdate("DROP TABLE observation_types");
    				
    				//INDEPENDENT TABLES TO DROP
    				statement.executeUpdate("DROP SEQUENCE observations_seq");
					statement.executeUpdate("DROP SEQUENCE cat_type_seq");
    				statement.executeUpdate("DROP SEQUENCE observation_categories_seq");
					statement.executeUpdate("DROP SEQUENCE conditions_cid_seq");
    				statement.executeUpdate("DROP TABLE health_supporter_type");
					statement.executeUpdate("DROP SEQUENCE patients_pid_seq");
    				statement.executeUpdate("DROP TABLE sex");
    				statement.executeUpdate("DROP TABLE condition_types");
    				statement.executeUpdate("DROP TABLE observation_categories");
    				
					
    				statement.executeUpdate("alter session set NLS_DATE_FORMAT='yyyy-MM-dd HH24:MI:SS'");
    				statement.executeUpdate("CREATE TABLE sex ("+
						"sex_id NUMBER(3),"+
						"description VARCHAR(7) NOT NULL,"+
						"PRIMARY KEY( sex_id )"+
					")");
					
					
					statement.executeUpdate("CREATE TABLE patients ("+
						"pid NUMBER(19),"+
						"fname VARCHAR(30) NOT NULL,"+
						"lname VARCHAR(30) NOT NULL,"+
						"address VARCHAR(150) NOT NULL,"+
						"city VARCHAR(50) NOT NULL,"+
						"state VARCHAR(50) NOT NULL,"+
						"zip VARCHAR(10) NOT NULL,"+
						"dob date NOT NULL,"+
						"sex NUMBER(3),"+
						"public_status VARCHAR(10),"+
						"PRIMARY KEY (pid),"+
						"FOREIGN KEY (sex) REFERENCES Sex( sex_id ),"+
						"CHECK ( public_status = 'yes' OR public_status = 'no' )"+
					")");
					
					
					statement.executeUpdate("CREATE SEQUENCE patients_pid_seq "+
							"START WITH 1 "+
							"INCREMENT BY 1"); 
					
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER patient_trigger "+
								"BEFORE INSERT ON patients "+
								"FOR EACH ROW "+
								"BEGIN "+
								"IF :new.pid IS NULL THEN "+
								"SELECT patients_pid_seq.nextval INTO :new.pid FROM DUAL; "+
								"END IF; " +
								"END;");
						
					statement.executeUpdate("CREATE TABLE condition_types ("+
						"cid int,"+
						"description VARCHAR(100),"+
						"PRIMARY KEY( cid )"+
					")");
					
					statement.executeUpdate("CREATE SEQUENCE conditions_cid_seq "+
							"START WITH 1 "+
							"INCREMENT BY 1"); 
					
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER conditions_trigger "+
								"BEFORE INSERT ON condition_types "+
								"FOR EACH ROW "+
								"BEGIN "+
								"IF :new.cid IS NULL THEN "+
								"SELECT conditions_cid_seq.nextval INTO :new.cid FROM DUAL; "+
								"END IF; " +
								"END;");
					
					
					statement.executeUpdate("CREATE Table patient_conditions ("+
						"pid NUMBER(19),"+
						"cid int,"+
						"PRIMARY KEY ( pid, cid ),"+
						"FOREIGN KEY (pid) REFERENCES Patients ( pid ),"+
						"FOREIGN KEY (cid) REFERENCES condition_types (cid)"+
					")");
					
					
					//Observation categories such as Behavorial, Phsych, Physio, etc...
					statement.executeUpdate("CREATE TABLE observation_categories ("+
						"ocid int,"+
						"description VARCHAR(50),"+
						"PRIMARY KEY(ocid)"+
					")");
					
					
					statement.executeUpdate("CREATE SEQUENCE observation_categories_seq "+
							"START WITH 1 "+
							"INCREMENT BY 1"); 
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER obs_categories_trigger "+
								"BEFORE INSERT ON observation_categories "+
								"FOR EACH ROW "+
								"BEGIN "+
								"IF :new.ocid IS NULL THEN "+
								"SELECT observation_categories_seq.nextval INTO :new.ocid FROM DUAL; "+
								"END IF; " +
								"END;");
					
					//This puts a specific category, like Diet, with a category
					statement.executeUpdate("CREATE TABLE observation_types ("+
						"type_id int,"+
						"ocid NUMBER(3),"+
						"description VARCHAR(50) NOT NULL,"+
						"PRIMARY KEY (type_id),"+
						"FOREIGN KEY (ocid) REFERENCES Observation_Categories(ocid)"+
					")");
					
					statement.executeUpdate("CREATE SEQUENCE cat_type_seq "+
							"START WITH 1 "+
							"INCREMENT BY 1"); 
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER cat_type_trigger "+
								"BEFORE INSERT ON observation_types "+
								"FOR EACH ROW "+
								"BEGIN "+
								"IF :new.type_id IS NULL THEN "+
								"SELECT cat_type_seq.nextval INTO :new.type_id FROM DUAL; "+
								"END IF; " +
								"END;");
				
					//This will show the relationship between a patient condition and category type
					statement.executeUpdate("CREATE TABLE cond_obser_relationships ("+
							"cid int,"+
							"type_id int,"+
							"PRIMARY KEY (cid, type_id),"+
							"FOREIGN KEY (cid) REFERENCES condition_types(cid),"+
							"FOREIGN KEY (type_id) REFERENCES observation_types(type_id)"+
						")");
					
					statement.executeUpdate("CREATE TABLE observations ("+
						"oid NUMBER(19),"+
						"pid NUMBER(19),"+
						"type_id int,"+
						"date_observed date NOT NULL,"+
						"time_observed varchar(10) NOT NULL,"+
						"date_recorded date NOT NULL,"+
						"time_recorded varchar(10) NOT NULL,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (pid) REFERENCES patients(pid),"+
						"FOREIGN KEY (type_id) REFERENCES observation_types(type_id)"+
					")");
					
					
					statement.executeUpdate("CREATE SEQUENCE observations_seq "+
							"START WITH 1 "+
							"INCREMENT BY 1"); 
					
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER observations_trigger "+
								"BEFORE INSERT ON observations "+
								"FOR EACH ROW "+
								"BEGIN "+
								"IF :new.oid IS NULL THEN "+
								"SELECT observations_seq.nextval INTO :new.oid FROM DUAL; "+
								"END IF; " +
								"END;");
						
					statement.executeUpdate("CREATE TABLE diet ("+
						"oid NUMBER(19),"+
						"food_type VARCHAR(150),"+
						"calories int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
					
					statement.executeUpdate("CREATE TABLE weight ("+
						"oid NUMBER(19),"+
						"pounds int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
						
					statement.executeUpdate("CREATE TABLE exercise ("+
						"oid NUMBER(19),"+
						"minutes int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
						
					statement.executeUpdate("CREATE TABLE blood_pressure ("+
						"oid NUMBER(19),"+
						"systolic int,"+
						"diastolic int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
					
					statement.executeUpdate("CREATE TABLE exercise_tolerance ("+
						"oid NUMBER(19),"+
						"steps int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
						
					statement.executeUpdate("CREATE TABLE oxygen_saturation ("+
						"oid NUMBER(19),"+
						"amount int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
						
					statement.executeUpdate("CREATE TABLE pain ("+
						"oid NUMBER(19),"+
						"rating int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid),"+
						"CHECK ( rating >= 1 AND rating <= 10 )"+
					")");
					
					statement.executeUpdate("CREATE TABLE mood ("+
						"oid NUMBER(19),"+
						"mood VARCHAR(20),"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid),"+
						"CHECK ( mood = 'Happy' OR mood = 'Sad' OR mood = 'Neutral' )"+
					")");
					
					statement.executeUpdate("CREATE TABLE contraction ("+
						"oid NUMBER(19),"+
						"frequency int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
					
					statement.executeUpdate("CREATE TABLE temperature ("+
						"oid NUMBER(19),"+
						"temp int,"+
						"PRIMARY KEY (oid),"+
						"FOREIGN KEY (oid) REFERENCES observations(oid)"+
					")");
					
					statement.executeUpdate("CREATE TABLE health_friends ("+
						"pid NUMBER(19),"+
						"hf_pid NUMBER(19),"+
						"date_added DATE NOT NULL,"+
						"PRIMARY KEY (pid, hf_pid),"+
						"FOREIGN KEY ( pid ) REFERENCES Patients(pid),"+
						"FOREIGN KEY ( hf_pid ) REFERENCES Patients( pid )"+
					")");
					
					statement.executeUpdate("CREATE TABLE health_supporter_type ("+
						"supporter_type int,"+
						"description VARCHAR (150) NOT NULL,"+
						"PRIMARY KEY ( supporter_type)"+
					")");
					
					statement.executeUpdate("CREATE TABLE health_supporter ("+
						"sid NUMBER(19),"+
						"supporter_type int,"+
						"first_name VARCHAR (50),"+
						"last_name VARCHAR (50),"+
						"clinic VARCHAR (150),"+
						"PRIMARY KEY ( sid ),"+
						"FOREIGN KEY ( supporter_type ) REFERENCES health_supporter_type ( supporter_type )"+
					")");
					
					statement.executeUpdate("CREATE TABLE alerts ("+
							"oid NUMBER(19),"+
							"alert_date date,"+
							"alert_active int,"+
							"PRIMARY KEY ( oid ),"+
							"FOREIGN KEY ( oid ) REFERENCES observations ( oid )"+
						")");
					
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
		System.out.println("Tables added");
	}
}
