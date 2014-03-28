package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Patient;
import beans.Physician;
import beans.SocialWorker;
import beans.SocialWorkerAppt;
import connection.JDBCConnection;

/**
 * This class will handle all interactions for social workers.
 * @author SG0214981
 *
 */
public class SocialWorkersDAO {
	
	/**
	 * Inserts a social worker into the db
	 * @param sw - The social worker to insert
	 */
	public static int insertSocialWorker( SocialWorker sw ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pid = -1;
		try {
			conn = JDBCConnection.getConnection();
			String query = "INSERT INTO social_workers ( password, fname, lname )";
			query += " VALUES ( ?, ?, ? )";
			ps = conn.prepareStatement(query, new String[] {"sid"});
			int index = 1;
			ps.setString(index++, sw.getPw());
			ps.setString(index++, sw.getFname());
			ps.setString(index++, sw.getLname());
			ps.executeUpdate();
    		rs = ps.getGeneratedKeys();
    		if ( rs != null && rs.next() ) {
    			pid = rs.getInt(1);
    		}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return pid;
	}
	
	/**
	 * This will return a list of patients relevant to the social worker.
	 * @param sw - The social worker
	 * @return a list of patients
	 */
	public static List<Patient> getPatientsForSocialWorker( SocialWorker sw ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status, "; 
		query += "p.password FROM patients p, social_w_patients sw WHERE sw.sid = ? and sw.pid = p.pid";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, sw.getSid());
			rs = ps.executeQuery();
    		return PatientDAO.loadPatients(rs);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return null;
	}
	
	/**
	 * Get a list of all social workers
	 * @return
	 */
	public static List<SocialWorker> getAllSocialWorkers() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<SocialWorker> socialWorkers = new ArrayList<SocialWorker>();
		String query = "SELECT sw.password, sw.fname, sw.lname, sw.sid FROM social_workers sw";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				SocialWorker sw = new SocialWorker();
				sw.setSid(rs.getInt("sid"));
				sw.setFname(rs.getString("fname"));
				sw.setLname(rs.getString("lname"));
				sw.setPw(rs.getString("password"));
				socialWorkers.add(sw);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return socialWorkers;
	}
	
	/**
	 * Get a single social worker
	 * @return
	 */
	public static SocialWorker getSocialWorker( int sid ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SocialWorker sw = new SocialWorker();
		String query = "SELECT sw.password, sw.fname, sw.lname, sw.sid FROM social_workers sw WHERE";
		query += " sw.sid = ?";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sid);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				sw.setSid(rs.getInt("sid"));
				sw.setFname(rs.getString("fname"));
				sw.setLname(rs.getString("lname"));
				sw.setPw(rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return sw;
	}
	
	/**
	 * This will return a list of every patient other than the patients already attached
	 * to a social worker
	 * @param sw - the social worker
	 * @return a list of patients
	 */
	public static List<Patient> getPossiblePatientsForSocialWorker( SocialWorker sw ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status, "; 
		query += "p.password FROM patients p WHERE NOT EXISTS ( SELECT * FROM social_w_patients sw WHERE sw.sid = ? ";
		query += "AND sw.pid = p.pid )";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, sw.getSid());
			rs = ps.executeQuery();
    		return PatientDAO.loadPatients(rs);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return null;
	}
	
	public static void createAppt( SocialWorkerAppt swa ) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO socialworker_appt ( sid, pid, appt_date, appt_time ) VALUES ";
		query += "( ?, ?, ?, ? )";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, swa.getSid());
			ps.setInt(index++, swa.getPid());
			ps.setDate(index++, swa.getAppt_date());
			ps.setString(index++, swa.getHour()+":"+swa.getMinutes());
			ps.execute();
    	} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	public static List<SocialWorkerAppt> getSocialWorkerApptRequest(Patient patient) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<SocialWorkerAppt> workers = new ArrayList<SocialWorkerAppt>();
		String query = "SELECT swa.sid, swa.pid, swa.appt_date, swa.appt_time FROM socialworker_appt ";
		query += "WHERE swa.pid = ?";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setDouble(index++, patient.getPid());
			rs = ps.executeQuery();
			while ( rs.next() ) {
				SocialWorkerAppt swa = new SocialWorkerAppt();
				swa.setSid(rs.getInt("sid"));
				swa.setPid(rs.getInt("pid"));
				swa.setAppt_date(rs.getDate("appt_date"));
				swa.setTime(rs.getString("appt_time"));
				workers.add(swa);
			}
    	} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
		return workers;
	}
}
