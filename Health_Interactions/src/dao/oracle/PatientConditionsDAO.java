package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Patient;
import beans.PatientCondition;
import connection.JDBCConnection;

/**
 * A class that handles everything for patient conditions.
 * @author SG0214981
 *
 */
public class PatientConditionsDAO {

	/**
	 * Allows a patient to be designated with a medical condition
	 * @param patient
	 * @param condition int
	 */
	public static void designatePatient( Patient patient, int condition ) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("INSERT INTO patient_conditions ( pid, cid ) VALUES ( ?, ? )");
			ps.setDouble( 1, patient.getPid());
			ps.setInt( 2, condition );
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	/**
	 * Get a list of all possible patient condition types
	 * @return
	 */
	public static List<PatientCondition> getAllConditionTypes() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PatientCondition> conditions = new ArrayList<PatientCondition>();
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM condition_types");
			rs = ps.executeQuery();
			while ( rs.next() ) {
				PatientCondition condition = new PatientCondition();
				condition.setCondition(rs.getInt("cid"));
				condition.setDescription(rs.getString("description"));
				conditions.add( condition );
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
		return conditions;
	}
	
	/**
	 * Get a list of all patients with a given condition type
	 * @param condition
	 * @return
	 */
	public static List<Patient> getAllPatientsWithCondition(int condition) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Patient> patients = new ArrayList<Patient>();
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT p.pid, p.fname, p.lname, p.address, p.city, p.state, "+
					"p.zip, p.dob, p.sex, p.public_status FROM patients p, patient_conditions pc "+
					"where p.pid = pc.pid and pc.cid = ?");
			ps.setInt(1, condition);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				Patient patient = new Patient();
				patient.setPid(rs.getDouble("pid"));
				patient.setFname(rs.getString("fname"));
				patient.setLname(rs.getString("lname"));
				patient.setAddress(rs.getString("address"));
				patient.setCity(rs.getString("city"));
				patient.setState(rs.getString("state"));
				patient.setZip(rs.getString("zip"));
				patient.setDob(rs.getDate("dob"));
				patient.setSex(rs.getInt("sex"));
				patient.setPublicStatus(rs.getString("public_status"));
				patients.add( patient );
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
		return patients;
	}
	
	/**
	 * This will add a new type of condition that it is possible to diagnosis a patient with.
	 * @param description - The name of the new condition to add
	 */
	public static void addNewCondition(String description) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("INSERT INTO condition_types ( description ) VALUES ( ? )");
			ps.setString( 1, description );
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	/**
	 * This method will create a new relationship between a Condition type and a observation type
	 * @param cid - The condition type
	 * @param type_id - The observation type to connect to the condition
	 */
	public static void addNewConditionObservationInteraction( int cid, int type_id ) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("INSERT INTO cond_obser_relationships ( cid, type_id ) VALUES ( ?, ? )");
			ps.setInt( 1, cid );
			ps.setInt( 2, type_id );
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
}
