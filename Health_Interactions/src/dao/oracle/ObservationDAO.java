package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.BloodPressure;
import beans.Contractions;
import beans.Diet;
import beans.Exercise;
import beans.ExerciseTolerance;
import beans.Mood;
import beans.Observation;
import beans.OxygenSaturation;
import beans.Pain;
import beans.Patient;
import beans.Temperature;
import beans.Weight;
import connection.JDBCConnection;

public class ObservationDAO {
	
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
	
	public static void addConditionCategoryRelationship(int cid, int type_id) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement("INSERT INTO observation_categories ( description ) VALUES ( ? )");
    		//ps.setString( 1, description );
    		ps.execute();
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    }
	
	public static void addDietObservation( Patient patient, Observation obs, Diet diet ) {
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
    		ps.setInt( 2, diet.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO diet ( oid, food_type, calories ) VALUES ( ?, ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setString(2, diet.getFood());
    			ps2.setInt(3, diet.getCalories());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addWeightObservation( Patient patient, Observation obs, Weight weight ) {
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
    		ps.setInt( 2, weight.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO weight ( oid, pounds ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, weight.getWeight());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addExerciseObservation( Patient patient, Observation obs, Exercise exercise ) {
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
    		ps.setInt( 2, exercise.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO exercise ( oid, minutes ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, exercise.getMinutes());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addBloodPressureObservation( Patient patient, Observation obs, BloodPressure bp ) {
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
    		ps.setInt( 2, bp.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO blood_pressure ( oid, systolic, diastolic ) VALUES ( ?, ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, bp.getSystolic());
    			ps2.setInt(3, bp.getDiastolic());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addExerciseToleranceObservation( Patient patient, Observation obs, ExerciseTolerance et ) {
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
    		ps.setInt( 2, et.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO exercise_tolerance ( oid, steps ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, et.getSteps());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addOxygenSaturationObservation( Patient patient, Observation obs, OxygenSaturation os ) {
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
    		ps.setInt( 2, os.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO oxygen_saturation ( oid, amount ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, os.getAmount());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addPainObservation( Patient patient, Observation obs, Pain pain ) {
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
    		ps.setInt( 2, pain.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO pain ( oid, rating ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, pain.getRating());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addPainObservation( Patient patient, Observation obs, Mood mood ) {
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
    		ps.setInt( 2, mood.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO mood ( oid, mood ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setString(2, mood.getMood());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addContractionsObservation( Patient patient, Observation obs, Contractions cont ) {
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
    		ps.setInt( 2, cont.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO contraction ( oid, frequency ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, cont.getFrequency());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
	public static void addTemperatureObservation( Patient patient, Observation obs, Temperature temp ) {
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
    		ps.setInt( 2, temp.getType_id());
    		ps.setDate( 3, obs.getDate_observed());
    		ps.setString( 4, obs.getHours() +":"+obs.getMinutes());
    		ps.setDate(5, obs.getDate_recorded());
    		ps.setString( 6, obs.getTime_recorded());
    		ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			oid = rs.getDouble(1);
    			query = "INSERT INTO temperature ( oid, temp ) VALUES ( ?, ? )";
    			ps2 = conn2.prepareStatement(query);
    			ps2.setDouble(1, oid);
    			ps2.setInt(2, temp.getType_id());
    			ps2.execute();
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, null);
		}
	}
	
}
