package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ObservationType;
import beans.Patient;
import connection.JDBCConnection;

/**
 * This class handles all interactions with patients in the db.
 * @author SG0214981
 *
 */
public class PatientDAO {

	//Empty constructor
	public PatientDAO() {
		
	}
	
	/**
	 * Inserts into the db
	 * @param patient
	 */
	public int insertPatient( Patient patient ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pid = -1;
		try {
			conn = JDBCConnection.getConnection();
			String query = "INSERT INTO patients ( password, fname, lname, address, city, state, zip,";
			query += " dob, sex, public_status ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			ps = conn.prepareStatement(query, new String[] {"pid"});
			ps = loadParameters( ps, patient );
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
	 * Get a list of all patients
	 * @return
	 */
	public static List<Patient> viewAllPatients() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM patients");
			rs = ps.executeQuery();
			return loadPatients(rs);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		//Should never reach
		return null;
	}
	
	/**
	 * Creates a list of Patient
	 * @return the list
	 */
	public static List<Patient> loadPatients(ResultSet rs) throws SQLException {
		ArrayList<Patient> list = new ArrayList<Patient>();
		while (rs.next()) {
			list.add(loadSinglePatient(rs));
		}
		return list;
	}
	
	/**
	 * For Patient
	 * Used with conjunction of loadList, this helps build the list by building a single line of the
	 * ResultSet into a Bean
	 */
	public static Patient loadSinglePatient(ResultSet rs) throws SQLException {
		Patient patient = new Patient();
		patient.setPid(rs.getInt("pid"));
		patient.setPassword(rs.getString("password"));
		patient.setFname(rs.getString("fname"));
		patient.setLname(rs.getString("lname"));
		patient.setAddress(rs.getString("address"));
		patient.setCity(rs.getString("city"));
		patient.setState(rs.getString("state"));
		patient.setZip(rs.getString("zip"));
		patient.setDob(rs.getDate("dob"));
		patient.setSex(rs.getInt("sex"));
		patient.setPublicStatus(rs.getString("public_status"));
		return patient;
	}

	/**
	 * Load a patient into the prepared statement
	 * @param ps
	 * @param patient
	 * @return
	 * @throws SQLException
	 */
	private PreparedStatement loadParameters(PreparedStatement ps, Patient patient) throws SQLException {
		int i = 1;
		ps.setString(i++, patient.getPassword());
		ps.setString(i++, patient.getFname());
		ps.setString(i++, patient.getLname());
		ps.setString(i++, patient.getAddress());
		ps.setString(i++, patient.getCity());
		ps.setString(i++, patient.getState());
		ps.setString(i++, patient.getZip());
		ps.setDate(i++, (Date) patient.getDob());
		ps.setInt(i++, patient.getSex());
		ps.setString(i++, patient.getPublicStatus());
		//ps.executeUpdate();
		return ps;
	}

	/**
	 * Get a single patient by their ID
	 * @param i
	 * @return
	 */
	public Patient getPatient(int i) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM patients where pid = ?");
			ps.setDouble( 1, i );
			rs = ps.executeQuery();
			if ( rs.next() ) {
				return loadSinglePatient(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return null;
	}
	
	
	
}
