package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Patient;
import connection.JDBCConnection;

public class HealthFriendsDAO {
	
	/**
	 * Find a list of all possible health friends for a specific patient
	 * @param patient
	 * @return
	 */
	public static List<Patient> findHealthFriend( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT p.pid, p.password, p.fname, p.lname, p.address, p.city, p.state, p.zip, ";
    		query += "p.dob, p.sex, p.public_status FROM patients p WHERE p.pid <> ? AND p.public_status = 'yes' AND ";
    		query += "p.pid IN ( SELECT pc.pid FROM patient_conditions pc WHERE pc.cid IN ( SELECT ";
    		query += "pc2.cid FROM patient_conditions pc2 WHERE pc2.pid = ? ) )";
			ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		ps.setDouble( 2, patient.getPid());
    		rs = ps.executeQuery();
    		return PatientDAO.loadPatients(rs);
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
        return null;
    }
	
	/**
	 * Add a health friend for a patient
	 * @param patient
	 * @param healthFriend
	 */
	public static void addHealthFriend( Patient patient, Patient healthFriend ) {
		Connection conn = null;
        PreparedStatement ps = null;
        Date date = new Date( System.currentTimeMillis());
        try {
        	conn = JDBCConnection.getConnection();
        	ps = conn.prepareStatement("INSERT INTO health_friends ( pid, hf_pid, date_added ) VALUES ( ?, ?, ? )");
        	ps.setDouble( 1, patient.getPid());
        	ps.setDouble( 2, healthFriend.getPid());
        	ps.setDate( 3, (java.sql.Date) date );
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    }
	
	/**
	 * Find how many health friends have been added since a given date for a specific patient
	 * @param patient
	 * @param date
	 * @return
	 */
	public static int healthFriendsAddedSince( Patient patient, String fromDate ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;
        Date date = Date.valueOf( fromDate );
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement("SELECT hf.COUNT(*) AS friend_count FROM health_friends hf WHERE hf.pid = ? AND hf.date_added > ?");
    		ps.setDouble( 1, patient.getPid());
    		ps.setDate( 2,  date );
    		rs = ps.executeQuery();
    		count = rs.getInt("friend_count");
    		
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    	return count;
    }
	
	/**
	 * Get a list of all health friends that reside in the same city as a patient
	 * @param patient
	 * @return
	 */
	public static List<Patient> healthFriendsSameCity( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement("SELECT p2.pid, p2.fname, p2.lname, p2.address, p2.city, p2.state, p2.zip, p2.dob, p2.sex, p2.public_status "+ 
					   "FROM patients p, health_friends hf WHERE p.pid = ? AND p.pid = hf.pid AND "+
					   "hf.hf_pid = ( SELECT p2.pid FROM patients p2 WHERE p2.city = p.city AND p2.state = p.state )");
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		return PatientDAO.loadPatients(rs);
    		
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    	return null;
    }
	
	/**
	 * Get a list of all health friends ordered by the date they were added.
	 * @param patient
	 * @return
	 */
	public static List<Patient> listHealthFriendsByDate( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement("SELECT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status "+ 
					   "FROM health_friends hf WHERE hf.pid = ? AND p.pid = hf.pid ORDER BY hf.date_added");
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		return PatientDAO.loadPatients(rs);
    		
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    	return null;
    }
	
	/**
	 * Return a list of health friends at risk
	 * @param patient
	 * @return
	 */
	public static List<Patient> viewHealthFriendsAtRisk( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		String query = "SELECT DISTINCT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status ";
    		query += "FROM patients p, health_friends hf, alerts a, observations o WHERE hf.pid = ? AND hf.hf_pid = p.pid AND ";
    		query += "o.pid = p.pid AND a.oid = o.oid AND a.active_alert = 1";
    		ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		return PatientDAO.loadPatients(rs);
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    	return null;
    }
}

