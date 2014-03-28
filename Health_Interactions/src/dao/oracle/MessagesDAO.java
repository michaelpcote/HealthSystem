package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Patient;
import beans.Message;
import connection.JDBCConnection;

/**
 * A class that will handle passing messages between health friends.
 * @author SG0214981
 *
 */
public class MessagesDAO {

	/**
	 * Read all messages that have not been read already
	 * @param patient - The patient to read messages
	 * @return - A list of messages
	 */
	public static List<Message> viewUnreadMessagesForPatient( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Message> messages = new ArrayList<Message>();
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT p.fname, p.lname, m.to_patient, m.from_patient, m.mid, m.message FROM patients p, messages m ";
    		query += "WHERE m.to_patient = ? AND m.viewed = 0 AND p.pid = m.from_patient";
			ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		while ( rs.next() ) {
    			Message m = new Message( rs.getString("message"));
    			m.setMid(rs.getInt("mid"));
    			m.setFromFirstName(rs.getString("fname"));
    			m.setFromLastName(rs.getString("lname"));
    			m.setFrom(rs.getInt("from_patient"));
    			m.setTo(rs.getInt("to_patient"));
    			messages.add(m);
    		}
    		if ( messages.size() > 0 ) {
    			markMessagesRead( messages );
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
        return messages;
    }
	
	/**
	 * View all messages whether they have been read or not
	 * @param patient - The patient who has received the messages
	 * @return - The list of messages to read
	 */
	public static List<Message> viewAllMessagesForPatient( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Message> messages = new ArrayList<Message>();
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT p.fname, p.lname, m.to_patient, m.from_patient, m.mid, m.message FROM patients p, messages m ";
    		query += "WHERE m.to_patient = ? AND p.pid = m.from_patient";
			ps = conn.prepareStatement(query);
    		ps.setDouble( 1, patient.getPid());
    		rs = ps.executeQuery();
    		while ( rs.next() ) {
    			Message m = new Message( rs.getString("message"));
    			m.setMid(rs.getInt("mid"));
    			m.setFromFirstName(rs.getString("fname"));
    			m.setFromLastName(rs.getString("lname"));
    			m.setFrom(rs.getInt("from_patient"));
    			m.setTo(rs.getInt("to_patient"));
    			messages.add(m);
    		}
    		if ( messages.size() > 0 ) {
    			markMessagesRead( messages );
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
        return messages;
    }
	
	/**
	 * This will mark all messages being read by a patient as read
	 * @param messages - The list of messages to mark as read
	 */
	private static void markMessagesRead( List<Message> messages ) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	String query = "UPDATE messages SET viewed = 1 WHERE mid = ?";
        	for ( int i = 1; i < messages.size(); i++ ) {
        		query += " OR mid = ?";
        	}
        	conn = JDBCConnection.getConnection();
        	ps = conn.prepareStatement(query);
        	int index = 1;
        	for ( int i = 0; i < messages.size(); i++ ) {
        		ps.setInt(index++, messages.get(i).getMid());
        	}
        	ps.executeUpdate();
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	/**
	 * Send a message to a healthfriend
	 * @param m - The message to send. Only to, from, and message must be set in the message.
	 */
	public static void sendMessageTo( Message m ) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "INSERT INTO messages( from_patient, to_patient, message ) VALUES ( ?, ?, ? )";
            // Create a Statement object for sending SQL statements to the database.
    		// Statement: The object used for executing a static SQL statement and returning the results it produces.
    		ps = conn.prepareStatement(query);
    		int index = 1;
    		ps.setInt( index++, m.getFrom());
    		ps.setInt( index++, m.getTo());
    		ps.setString( index++, m.getMessage());
    		ps.execute();
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    }
}
