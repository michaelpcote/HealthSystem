package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Patient;
import beans.PatientConditions;
import connection.JDBCConnection;

public class PatientConditionsDAO {

	/**
	 * Allows a patient to be designated with a medical condition
	 * @param patient
	 * @param condition int
	 */
	public void designatePatient( Patient patient, int condition ) {
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
	 * Get a list of all possible patient conditions
	 * @return
	 */
	public List<PatientConditions> getAllConditions() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PatientConditions> conditions = new ArrayList<PatientConditions>();
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM condition_types");
			rs = ps.executeQuery();
			while ( rs.next() ) {
				PatientConditions condition = new PatientConditions();
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
}
