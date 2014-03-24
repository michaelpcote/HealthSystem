package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Patient;
import beans.Physicians;
import beans.SocialWorker;
import connection.JDBCConnection;

public class PhysiciansDAO {

	/**
	 * Inserts a physician into the db
	 * @param patient
	 */
	public static int insertPhysician( Physicians physician ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pid = -1;
		try {
			conn = JDBCConnection.getConnection();
			String query = "INSERT INTO physicians ( password, fname, lname, clinic )";
			query += " VALUES ( ?, ?, ?, ? )";
			ps = conn.prepareStatement(query, new String[] {"phy_id"});
			int index = 1;
			ps.setString(index++, physician.getPw());
			ps.setString(index++, physician.getFname());
			ps.setString(index++, physician.getLname());
			ps.setString(index++, physician.getClinic());
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
	 * Assign a patient to a social worker
	 * @param p - The patient
	 * @param sw - The social worker
	 */
	public static void assignPatientToSocialWorker( Patient p, SocialWorker sw ) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			String query = "INSERT INTO social_w_patients ( sid, pid ) VALUES ( ?, ? )";
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, sw.getSid());
			ps.setDouble(index++, p.getPid());
			ps.execute();
    	} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
}