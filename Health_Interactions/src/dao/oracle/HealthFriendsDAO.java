package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Message;
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
            query += "p.dob, p.sex, p.public_status FROM patients p, health_friends hf WHERE p.pid <> ? ";
            query += "AND p.public_status = 'yes' AND hf.pid = ? AND p.pid <> hf.hf_pid AND ";
            query += "p.pid IN ( SELECT pc.pid FROM patient_conditions pc WHERE ";
            query += "pc.cid IN ( SELECT pc2.cid FROM patient_conditions pc2 WHERE pc2.pid = ? ) )";
			ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		ps.setDouble( 2, patient.getPid());
    		ps.setDouble( 3, patient.getPid());
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
        	ps.execute();
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
    		ps = conn.prepareStatement("SELECT COUNT(*) AS friend_count FROM health_friends hf WHERE hf.pid = ? AND hf.date_added > ?");
    		ps.setDouble( 1, patient.getPid());
    		ps.setDate( 2,  date );
    		rs = ps.executeQuery();
    		if ( rs.next() ) {
    			count = rs.getInt("friend_count");
    		}
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
    		ps = conn.prepareStatement("SELECT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, "+ 
					   "p.public_status, p.password FROM patients p, health_friends hf WHERE hf.pid = ? "+
					   "AND p.pid = hf.hf_pid AND p.city = ? AND p.state = ?");
    		ps.setDouble( 1, patient.getPid());
    		ps.setString( 2, patient.getCity());
    		ps.setString( 3, patient.getState());
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
    		ps = conn.prepareStatement("SELECT p.pid, p.fname, p.lname, p.address, p.city, p.state, "+ 
					   "p.zip, p.dob, p.sex, p.public_status, p.password FROM patients p, health_friends "+
    				   "hf WHERE hf.pid = ? AND p.pid = hf.hf_pid ORDER BY hf.date_added");
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
    		String query = "SELECT DISTINCT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status, p.password ";
    		query += "FROM patients p, health_friends hf, alerts a, observations o WHERE hf.pid = ? AND hf.hf_pid = p.pid AND ";
    		query += "o.pid = p.pid AND a.oid = o.oid AND a.alert_active = 1";
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
	
	/**
	 * Return a list of patients with a health friend that has no alerts
	 * @param patient
	 * @return
	 */
	public static List<Patient> viewPatientsWithHealthFriendsNoAlerts() {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		String query = "SELECT DISTINCT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, ";
    		query += "p.public_status, p.password FROM patients p, health_friends hf WHERE hf.pid = p.pid AND 0 = ( SELECT COUNT( a.oid ) ";
    		query += "FROM alerts a, observations o WHERE hf.hf_pid = o.pid AND o.oid = a.oid AND ";
    		query += "a.alert_active = 1 )";
    		ps = conn.prepareStatement(query);
    		rs = ps.executeQuery();
    		return PatientDAO.loadPatients(rs);
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    	return null;
    }
	
	public static void checkHealthFriends( Patient patient ) {
		Connection conn = null;
		Connection conn2 = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		conn2 = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		String query = "SELECT DISTINCT hf.pid FROM health_friends hf ";
    		query += "WHERE hf.hf_pid = ? AND 5 <= ( SELECT ";
    		query += "COUNT(*) FROM observations o, alerts a WHERE hf.pid = o.pid AND ";
    		query += " o.oid = a.oid AND a.alert_active = 1 )";
    		ps = conn.prepareStatement(query);
    		ps.setDouble(1, patient.getPid());
    		rs = ps.executeQuery();
    		while ( rs.next() ) {
    			Message m = new Message();
    			m.setFrom(rs.getInt("pid"));
    			m.setTo((int) patient.getPid());
    			m.setMessage("I have 5 or more alerts that I have not viewed and cleared. Please help me!");
    			MessagesDAO.sendMessageTo(m);
    		}
    		query = "SELECT DISTINCT hf.pid FROM health_friends hf ";
    		query += "WHERE hf.hf_pid = ? AND 0 <> ( SELECT ";
    		query += "COUNT(*) FROM observations o, alerts a WHERE hf.pid = o.pid AND ";
    		query += " o.oid = a.oid AND a.alert_active = 1 AND a.alert_date < ? )";
    		Date date = new Date( System.currentTimeMillis() - 7*24*60*60*1000 );
    		ps2 = conn2.prepareStatement(query);
    		ps2.setDouble(1,  patient.getPid());
    		ps2.setDate( 2, date );
    		rs2 = ps2.executeQuery();
    		while ( rs2.next() ) { 
    			Message m = new Message();
    			m.setFrom(rs2.getInt("pid"));
    			m.setTo((int) patient.getPid());
    			m.setMessage("I have an old alert that I have not viewed and cleared. Please help me!");
    			MessagesDAO.sendMessageTo(m);
    		}
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, rs2);
		}
	}
	
	
}

