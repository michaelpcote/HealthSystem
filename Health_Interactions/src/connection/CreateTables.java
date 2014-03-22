package connection;

import java.sql.*;

public class CreateTables {
	 
	public static void main(String[] args) {
		try {
            // Register JDBC Driver (Oracle Thin)
            Connection connection = null;
            Statement statement = null;
            Connection conn2 = null;
            PreparedStatement ps2 = null;
            ResultSet rs = null;
            //Statement stat2 = null;
            
            	try {
            		// Get a connection to the specified JDBC URL.
    				connection = JDBCConnection.getConnection();
                    
    		 		// Create a Statement object for sending SQL statements to the database.
    				// Statement: The object used for executing a static SQL statement and returning the results it produces.
    				statement = connection.createStatement();
    				conn2 = JDBCConnection.getConnection();
    				ps2 = conn2.prepareStatement("SELECT DISTINCT table_name FROM observation_types");
    				rs = ps2.executeQuery();
    				while ( rs.next() ) {
    					String query = "DROP TABLE " + rs.getString("table_name");
    					System.out.println(query);
    					statement.executeUpdate(query);
    				}
    				
    				//DEPENDENT TABLES
    				//These have to be dropped first
    				
    				statement.executeUpdate("DROP TABLE alerts");
    				statement.executeUpdate("DROP TABLE cond_obser_relationships"); 
					statement.executeUpdate("DROP TABLE health_supporter");
    				statement.executeUpdate("DROP TABLE health_friends");
    				statement.executeUpdate("DROP TABLE observations");
    				statement.executeUpdate("DROP TABLE patient_conditions");
    				statement.executeUpdate("DROP TABLE patients");
    				statement.executeUpdate("DROP TABLE observation_types");
    				
    				//INDEPENDENT TABLES TO DROP
    				statement.executeUpdate("DROP SEQUENCE observations_seq");
					statement.executeUpdate("DROP SEQUENCE obs_type_seq");
    				statement.executeUpdate("DROP SEQUENCE observation_categories_seq");
					statement.executeUpdate("DROP SEQUENCE conditions_cid_seq");
    				statement.executeUpdate("DROP TABLE health_supporter_type");
					statement.executeUpdate("DROP SEQUENCE patients_pid_seq");
    				statement.executeUpdate("DROP TABLE sex");
    				statement.executeUpdate("DROP TABLE condition_types");
    				statement.executeUpdate("DROP TABLE observation_categories");
    				statement.executeUpdate("DROP TABLE social_workers");
					statement.executeUpdate("DROP TABLE physicians");
    				
    				statement.executeUpdate("alter session set NLS_DATE_FORMAT='yyyy-MM-dd HH24:MI:SS'");
    				
    				statement.executeUpdate("CREATE TABLE sex ("+
						"sex_id NUMBER(3),"+
						"description VARCHAR(7) NOT NULL,"+
						"PRIMARY KEY( sex_id )"+
					")");
					
					
					statement.executeUpdate("CREATE TABLE patients ("+
						"pid NUMBER(19),"+
						"password varchar(50) NOT NULL,"+
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
					
					statement.executeUpdate("CREATE TABLE physicians ("+
							"pid NUMBER(19),"+
							"password varchar(50) NOT NULL,"+
							"fname VARCHAR(30) NOT NULL,"+
							"lname VARCHAR(30) NOT NULL,"+
							"clinic VARCHAR(150) NOT NULL,"+
							"PRIMARY KEY (pid)"+
						")");
					
					statement.executeUpdate("CREATE TABLE social_workers ("+
							"sid NUMBER(19),"+
							"password varchar(50) NOT NULL,"+
							"fname VARCHAR(30) NOT NULL,"+
							"lname VARCHAR(30) NOT NULL,"+
							"PRIMARY KEY (pid)"+
						")");
					
					statement.executeUpdate("CREATE TABLE social_w_patients ("+
							"sid NUMBER(19),"+
							"pid NUMBER(19),"+
							"PRIMARY KEY (sid, pid),"+
							"FOREIGN KEY (sid) REFERENCES social_workers( sid ),"+
							"FOREIGN KEY (pid) REFERENCES patients( pid )"+
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
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER physicians_trigger "+
							"BEFORE INSERT ON physicians "+
							"FOR EACH ROW "+
							"BEGIN "+
							"IF :new.pid IS NULL THEN "+
							"SELECT patients_pid_seq.nextval INTO :new.pid FROM DUAL; "+
							"END IF; " +
							"END;");
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER social_workers_trigger "+
							"BEFORE INSERT ON social_workers "+
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
						"table_name varchar(50),"+
						"display_name VARCHAR(50) NOT NULL,"+
						"additional_info varchar(250),"+
						"column_names_types varchar(300),"+
						"value_choices varchar(300) DEFAULT '',"+
						"PRIMARY KEY (type_id),"+
						"FOREIGN KEY (ocid) REFERENCES Observation_Categories(ocid)"+
					")");
					
					statement.executeUpdate("CREATE SEQUENCE obs_type_seq "+
							"START WITH 1 "+
							"INCREMENT BY 1"); 
					
					statement.executeUpdate("CREATE OR REPLACE TRIGGER obs_type_trigger "+
								"BEFORE INSERT ON observation_types "+
								"FOR EACH ROW "+
								"BEGIN "+
								"IF :new.type_id IS NULL THEN "+
								"SELECT obs_type_seq.nextval INTO :new.type_id FROM DUAL; "+
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
									
					FillDatabase.main(null);
					
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
							"viewed int,"+
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
