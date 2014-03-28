package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Patient;
import beans.Physician;
import beans.PhysicianAppt;
import beans.SocialWorker;
import beans.SocialWorkerAppt;
import connection.JDBCConnection;

/**
 * This class will handle all interactions with the db for a physician
 * @author SG0214981
 *
 */
public class PhysiciansDAO {

	/**
	 * Inserts a physician into the db
	 * @param patient
	 */
	public static int insertPhysician( Physician physician ) {
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
	
	/**
	 * Assign a patient to a social worker
	 * @param p - The patient
	 * @param sw - The social worker
	 */
	public static Physician getPhysician( int physician ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Physician p = new Physician();
		try {
			conn = JDBCConnection.getConnection();
			String query = "SELECT * from physicians where phy_id = ?";
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, physician);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				p.setClinic(rs.getString("clinic"));
				p.setFname(rs.getString("fname"));
				p.setLname(rs.getString("lname"));
				p.setPid(rs.getInt("phy_id"));
			}
    	} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
		return p;
	}
	
	public static void createAppt( PhysicianAppt pa ) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO doctor_appt ( phy_id, pid, appt_date, appt_time ) VALUES ";
		query += "( ?, ?, ?, ? )";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setInt(index++, pa.getPhy_id());
			ps.setInt(index++, pa.getPid());
			ps.setString(index++, pa.getAppt_date());
			ps.setString(index++, pa.getHour()+":"+pa.getMinutes());
			ps.execute();
    	} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	public static List<PhysicianAppt> viewPhysicianApptRequest(Patient patient) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PhysicianAppt> physicians = new ArrayList<PhysicianAppt>();
		String query = "SELECT da.phy_id, da.pid, da.appt_date, da.appt_time FROM doctor_appt da ";
		query += "WHERE da.pid = ?";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setDouble(index++, patient.getPid());
			rs = ps.executeQuery();
			while ( rs.next() ) {
				PhysicianAppt phy = new PhysicianAppt();
				phy.setPhy_id(rs.getInt("phy_id"));
				phy.setPid(rs.getInt("pid"));
				phy.setAppt_date(rs.getString("appt_date"));
				phy.setTime(rs.getString("appt_time"));
				physicians.add(phy);
			}
    	} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
		return physicians;
	}
	
	public static List<PhysicianAppt> viewApptForPhysician(int phy_id ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PhysicianAppt> physicians = new ArrayList<PhysicianAppt>();
		String query = "SELECT da.phy_id, da.pid, da.appt_date, da.appt_time FROM doctor_appt da ";
		query += "WHERE da.phy_id = ?";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setDouble(index++, phy_id);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				PhysicianAppt phy = new PhysicianAppt();
				phy.setPhy_id(rs.getInt("phy_id"));
				phy.setPid(rs.getInt("pid"));
				phy.setAppt_date(rs.getDate("appt_date"));
				phy.setTime(rs.getString("appt_time"));
				physicians.add(phy);
			}
    	} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
		return physicians;
	}
}
