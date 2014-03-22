package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.JDBCConnection;
import beans.PatientCreatedObservationType;

public class PatientCreatedObservationDAO {

	
	public static void addPatientCreatedObservationType(PatientCreatedObservationType pot) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			String query = "INSERT INTO patient_ob_types p ( p.pid, p.ocid, p.table_name, p.display_name, p.additional_info, ";
			query += "p.column_names_types, p.value_choices ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
			ps = conn.prepareStatement(query);
			int i = 1;
			ps.setInt( i++, pot.getPid());
			ps.setInt( i++, pot.getOcid());
			ps.setString( i++, pot.getTable_name());
			ps.setString( i++, pot.getDisplay_name());
			ps.setString( i++, pot.getAdditional_info());
			ps.setString( i++, pot.getColumn_names_types());
			ps.setString( i++, pot.getValue_choices());
			ps.execute();
			createPatientTable(pot);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	private static void createPatientTable(PatientCreatedObservationType pot) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			String colnames[] = pot.getColumn_names_types().split(",");
			String query = "CREATE TABLE "+pot.getTable_name()+" ( oid NUMBER(19), ";
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
			if ( pot.getValue_choices() == null ) {
				query += ")";
			} else {
				query += ", ";
				String possibleValues[] = pot.getValue_choices().split(":");
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
					query += "AND " + possibleValues[0] + " >= '"+values[1]+"' ";
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
