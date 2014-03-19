package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ObservationTypes;
import beans.Patient;
import connection.JDBCConnection;

public class ObservationTypesDAO {

	public static List<ObservationTypes> getAllObservationTypes() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ObservationTypes> ob_types = new ArrayList<ObservationTypes>();
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM observation_types order by type_id");
			rs = ps.executeQuery();
			while ( rs.next() ) {
				ObservationTypes o = new ObservationTypes();
				o.setType_id(rs.getInt("type_id"));
				o.setOcid(rs.getInt("ocid"));
				o.setDescription(rs.getString("description"));
				ob_types.add( o );
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		//Should never reach
		return ob_types;
	}
	
	public static List<ObservationTypes> getObservationTypesForPatient(Patient patient) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ObservationTypes> ob_types = new ArrayList<ObservationTypes>();
		try {
			String query = "SELECT o.type_id, o.ocid, o.description FROM observation_types o, ";
			query += "patient_conditions pc, cond_obser_relationships cor WHERE pc.pid = ? AND ";
			query += "pc.cid = cor.cid AND cor.type_id = o.type_id";
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				ObservationTypes o = new ObservationTypes();
				o.setType_id(rs.getInt("type_id"));
				o.setOcid(rs.getInt("ocid"));
				o.setDescription(rs.getString("description"));
				ob_types.add( o );
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		//Should never reach
		return ob_types;
	}
}
