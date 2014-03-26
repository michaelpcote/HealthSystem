package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.sql.DATE;
import beans.Patient;
import connection.JDBCConnection;

public class AlertsDAO {

	/**
	 * Returns a list of all non-viewed alerts for a patient
	 * @param patient - The patient to view alerts for
	 * @return A comma delimited string of the alerts
	 */
	public static String viewAlerts( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String answer = "";
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT a.alert_date, ot.display_name FROM alerts a, ";
    		query += "observations o, observation_types ot where a.viewed = 0 AND a.oid = o.oid AND o.pid = ? ";
    		query += "AND o.type_id = ot.type_id";
			ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		if ( rs.next() ) {
    			Date date = rs.getDate("alert_date");
    			answer += "You have a " + rs.getString("display_name") + " from " + date.toString();
    		}
    		while ( rs.next() ) {
    			Date date = rs.getDate("alert_date");
    			answer += ",You have a " + rs.getString("display_name") + " from " + date.toString();
    		}
    		recordViewedAlerts(patient);
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
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
        try {
        	conn = JDBCConnection.getConnection();
    		String query = "UPDATE alerts a SET a.alert_active = 0 WHERE a.oid = o.oid AND o.pid = ? ";
    		query += "AND a.viewed = 1";
    		ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	/**
	 * Mark the alerts as viewed - This method will be called automatically after a patient
	 * views unread alerts
	 * @param patient - The patient to mark alerts viewed for
	 */
	private static void recordViewedAlerts( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = JDBCConnection.getConnection();
    		String query = "UPDATE alerts a SET a.viewed = 1 WHERE a.oid = o.oid AND o.pid = ? ";
    		ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
}
