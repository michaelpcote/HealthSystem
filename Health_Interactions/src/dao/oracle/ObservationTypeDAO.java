package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ObservationType;
import beans.Patient;
import connection.JDBCConnection;

public class ObservationTypeDAO {

	
	public static List<ObservationType> getAllObservationTypes() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ObservationType> ob_types = new ArrayList<ObservationType>();
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM observation_types order by type_id");
			rs = ps.executeQuery();
			while ( rs.next() ) {
				ObservationType o = new ObservationType();
				o.setType_id(rs.getInt("type_id"));
				o.setOcid(rs.getInt("ocid"));
				o.setDisplay_name(rs.getString("display_name"));
				o.setAdditional_info(rs.getString("additional_info"));
				o.setColumn_names_types(rs.getString("column_names_types"));
				o.setValue_choices(rs.getString("value_choices"));
				o.setTable_name(rs.getString("table_name"));
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
	
	public static ObservationType getObservationType( int type_id ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ObservationType> ob_types = new ArrayList<ObservationType>();
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM observation_types WHERE type_id = ?");
			ps.setInt(1, type_id);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				ObservationType o = new ObservationType();
				o.setType_id(rs.getInt("type_id"));
				o.setOcid(rs.getInt("ocid"));
				o.setDisplay_name(rs.getString("display_name"));
				o.setAdditional_info(rs.getString("additional_info"));
				o.setColumn_names_types(rs.getString("column_names_types"));
				o.setValue_choices(rs.getString("value_choices"));
				o.setTable_name(rs.getString("table_name"));
				return o;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		//Should never reach
		return null;
	}
	
	public static List<ObservationType> getObservationTypesForPatient(Patient patient) {
		Connection conn = null;
		PreparedStatement ps = null;
		Connection conn2 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		ResultSet rs = null;
		ArrayList<ObservationType> ob_types = new ArrayList<ObservationType>();
		try {
			String query = "SELECT DISTINCT o.type_id, o.ocid, o.display_name, o.additional_info, o.column_names_types, ";
			query += "o.value_choices, o.table_name FROM observation_types o, ";
			query += "patient_conditions pc, cond_obser_relationships cor WHERE ( pc.pid = ? AND ";
			query += "pc.cid = cor.cid AND cor.type_id = o.type_id ) OR o.ocid = 1 OR o.ocid = 3 ORDER BY ";
			query += "type_id";
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, patient.getPid());
			rs = ps.executeQuery();
			while ( rs.next() ) {
				ObservationType o = new ObservationType();
				o.setType_id(rs.getInt("type_id"));
				o.setOcid(rs.getInt("ocid"));
				o.setDisplay_name(rs.getString("display_name"));
				o.setAdditional_info(rs.getString("additional_info"));
				o.setColumn_names_types(rs.getString("column_names_types"));
				o.setValue_choices(rs.getString("value_choices"));
				o.setTable_name(rs.getString("table_name"));
				ob_types.add( o );
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, rs2);
		}
		//Should never reach
		return ob_types;
	}
	
	public static void addNewObservationType( ObservationType ot ) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			String query = "INSERT INTO observation_types ot ( ot.ocid, ot.table_name, ot.display_name, ot.additional_info, ";
			query += "ot.column_names_types, ot.value_choices ) VALUES ( ?, ?, ?, ?, ?, ? )";
			ps = conn.prepareStatement(query);
			int i = 1;
			ps.setInt( i++, ot.getOcid());
			ps.setString( i++, ot.getTable_name());
			ps.setString( i++, ot.getDisplay_name());
			ps.setString( i++, ot.getAdditional_info());
			ps.setString( i++, ot.getColumn_names_types());
			ps.setString( i++, ot.getValue_choices());
			ps.execute();
			createTable(ot);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	private static void createTable(ObservationType ot) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			String colnames[] = ot.getColumn_names_types().split(",");
			String query = "CREATE TABLE "+ ot.getTable_name()+" ( oid NUMBER(19), ";
			for ( int i = 0; i < colnames.length; i++ ) {
				String colInfo[] = colnames[i].split(":");
				String colname = colInfo[0];
				String coltype = colInfo[1];
				if ( coltype.equals("String")) {
					coltype = "varchar(250)";
				} else {
					coltype = "int";
				}
				query += colname + " "+ coltype + ", ";
			}
			query += "PRIMARY KEY (oid), FOREIGN KEY (oid) REFERENCES observations(oid)";
			if ( ot.getValue_choices() == null ) {
				query += ")";
			} else {
				query += ", ";
				String possibleValues[] = ot.getValue_choices().split(":");
				if ( possibleValues[1].equals("String")) {
					String values[] = possibleValues[2].split(",");
					query += "CHECK ( " + possibleValues[0] + " = '"+values[0]+"' ";
					for ( int i = 1; i < values.length; i++ ) {
						query += "OR " + possibleValues[0] + " = '"+values[i]+"' ";
					}
					query += " ) )";
				} else {
					String values[] = possibleValues[2].split(",");
					query += "CHECK ( " + possibleValues[0] + " >= '"+values[0]+"' ";
					query += "AND " + possibleValues[0] + " <= '"+values[1]+"' ";
					query += " ) )";
				}
			}
			System.out.println(query);
			ps = conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
}
