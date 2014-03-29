package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Category;
import beans.Observation;
import beans.ObservationType;
import beans.Patient;
import connection.JDBCConnection;

/**
 * A class to handle observations
 * @author SG0214981
 *
 */
public class ObservationDAO {
	
	/**
	 * This method will add a category such as behavior, psychological, etc.
	 * @param description - behavior, psychological, etc.
	 */
	public static void addCategoryType(String description) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement("INSERT INTO observation_categories ( description ) VALUES ( ? )");
    		ps.setString( 1, description );
    		ps.execute();
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    }
	
	public static List<Category> getAllCategories() {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Category> gories = new ArrayList<Category>();
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT ocid, description FROM observation_categories";
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement(query);
    		rs = ps.executeQuery();
    		while ( rs.next() ) {
    			Category cat = new Category();
    			cat.setDescription(rs.getString("description"));
    			cat.setOcid(rs.getInt("ocid"));
    			gories.add(cat);
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
        return gories;
	}
	
	/**
	 * This method will add relationships between a observation type and a condition. For example, requiring
	 * that HIV records blood pressure now as well.
	 * @param cid - The patient condition
	 * @param type_id - The observation type
	 */
	public static void addConditionCategoryRelationship(int cid, int type_id) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( ?, ? )");
    		ps.setInt( 1, cid );
    		ps.setInt(2, type_id);
    		ps.execute();
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    }
	
	/**
	 * This method will create an observation for any observation type
	 * @param patient - The patient adding the observation
	 * @param obs - An observation 
	 * @param observations - This is a string in the following format "colname:observation,colname:observation" etc
	 */
	public static void addPatientObservation( Patient patient, Observation obs, ObservationType ot, String observations ) {
		Connection conn = null;
		Connection conn2 = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
        	// Get a connection to the specified JDBC URL.
        	double oid = -1;
    		conn = JDBCConnection.getConnection();
    		conn2 = JDBCConnection.getConnection();
    		String query = "INSERT INTO observations ( pid, type_id, date_observed, time_observed, date_recorded, time_recorded ) ";
    		query += "VALUES ( ?, ?, ?, ?, ?, ? )";
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement(query, new String[] {"oid"} );
    		ps.setDouble( 1, patient.getPid() );
    		ps.setInt( 2, ot.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			String names[] = parseColumnNames( ot.getColumn_names_types() );
    			String types[] = parseColumnTypes( ot.getColumn_names_types() );
    			query = "INSERT INTO "+ ot.getTable_name() +" ( oid";
    			for ( int i = 0; i < names.length; i++ ) {
    				query += ", " + names[i];
    			}
    			query += " ) VALUES ( ?";
    			for ( int i = 0; i < types.length; i++ ) {
    				query += ", ?";
    			}
    			query += " )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			String observationColumn[] = parseColumnNames( observations );
    			String observationValues[] = parseColumnTypes( observations );
    			for ( int i = 0; i < observationColumn.length; i++ ) {
    				for ( int j = 0; j < observationColumn.length; j++ ) {
    					if ( names[i].equals(observationColumn[j])) {
    						if ( types[i].equals("String")) {
    							ps2.setString((i+2), observationValues[j]);
    						} else {
    							ps2.setInt((i+2), Integer.valueOf(observationValues[j]));
    						}
    					}
    				}
    			}
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	/**
	 * A private method to get the column names
	 * @param columnInfo
	 * @return
	 */
	private static String[] parseColumnNames(String columnInfo) {
		String both[] = columnInfo.split(",");
		String names[] = new String[both.length];
		for ( int i = 0; i < names.length; i++ ) {
			String combo[] = both[i].split(":");
			names[i] = combo[0];
		}
		return names;
	}
	
	/**
	 * A private method to get the column types.
	 * @param columnInfo
	 * @return
	 */
	private static String[] parseColumnTypes(String columnInfo) {
		String both[] = columnInfo.split(",");
		String values[] = new String[both.length];
		for ( int i = 0; i < values.length; i++ ) {
			String combo[] = both[i].split(":");
			values[i] = combo[1];
		}
		return values;
	}
}
