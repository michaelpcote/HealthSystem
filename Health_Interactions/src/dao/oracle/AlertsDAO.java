package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.sql.DATE;
import beans.Patient;
import connection.JDBCConnection;

/**
 * This class will handle all sql queries for alerts
 * @author SG0214981
 *
 */
public class AlertsDAO {

	/**
	 * Returns a list of all non-viewed alerts for a patient
	 * @param patient - The patient to view alerts for
	 * @return A comma delimited string of the alerts
	 */
	public static String viewNonClearedAlerts( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String answer = "";
        String or = "";
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT a.alert_date, ot.display_name, a.oid FROM alerts a, ";
    		query += "observations o, observation_types ot where a.alert_active = 1 AND a.oid = o.oid AND o.pid = ? ";
    		query += "AND o.type_id = ot.type_id";
			ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		if ( rs.next() ) {
    			Date date = rs.getDate("alert_date");
    			or += "alerts.oid = "+rs.getInt("oid");
    			answer += "You have a " + rs.getString("display_name") + " alert from " + date.toString();
    		}
    		while ( rs.next() ) {
    			or += " OR alerts.oid = " + rs.getInt("oid");
    			Date date = rs.getDate("alert_date");
    			answer += ",You have a " + rs.getString("display_name") + " alert from " + date.toString();
    		}
    		if ( ! or.equals("")) {
	    		recordViewedAlerts(or);
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
        return answer;
    }
	
	/**
	 * This will clear any alerts that have already been viewed
	 * @param patient - The patient to clear the alerts for
	 */
	public static void clearViewedAlerts( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        String or = getViewedAlerts( patient );
        if ( ! or.equals("")) {
	        try {
	        	conn = JDBCConnection.getConnection();
	        	String query = "UPDATE alerts SET alert_active = 0 WHERE " + or;
	    		ps = conn.prepareStatement(query);
	    		ps.executeQuery();
	        } catch(SQLException e) {
	           	e.printStackTrace();
	        } finally {
				JDBCConnection.closeConnection(conn, ps, null);
			}
        }
	}
	
	/**
	 * Mark the alerts as viewed - This method will be called automatically after a patient
	 * views unread alerts
	 * @param patient - The patient to mark alerts viewed for
	 */
	private static void recordViewedAlerts( String or ) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = JDBCConnection.getConnection();
    		String query = "UPDATE alerts SET viewed = 1 WHERE " + or;
    		ps = conn.prepareStatement(query);
    		ps.execute();
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	/**
	 * This method will get a count of all active alerts for a patient and all of his or her
	 * health friends.
	 * @param patient
	 * @return
	 */
	public static int lingeringAlertCount(Patient patient) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int numberOfAlerts = 0;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		String query = "SELECT COUNT(a.alert_active) as numalerts FROM alerts a, health_friends hf, observations o ";
    		query += "WHERE ( hf.pid = ? AND hf.pid = o.pid AND o.oid = a.oid AND a.alert_active = 1 ) OR ";
    		query += "( hf.pid = ? AND hf.hf_pid = o.pid AND o.oid = a.oid AND a.alert_active = 1 )";
    		ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		ps.setDouble( 2, patient.getPid());
    		rs = ps.executeQuery();
    		if ( rs.next() ) {
    			numberOfAlerts = rs.getInt("numalerts");
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
    	return numberOfAlerts;
    }
	
	/**
	 * Returns a list of all viewed alerts for a patient
	 * @param patient - The patient to view alerts for
	 * @return A comma delimited string of the alerts
	 */
	private static String getViewedAlerts( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String or = "";
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT a.oid FROM alerts a, ";
    		query += "observations o where a.alert_active = 1 AND a.oid = o.oid AND o.pid = ? ";
    		query += "AND a.viewed = 1";
			ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		if ( rs.next() ) {
    			or += "alerts.oid = "+rs.getInt("oid");
    		}
    		while ( rs.next() ) {
    			or += " OR alerts.oid = " + rs.getInt("oid");
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
        return or;
    }
}
