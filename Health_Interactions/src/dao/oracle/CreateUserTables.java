package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.JDBCConnection;
import beans.PatientAddedObservationType;

public class CreateUserTables {

	
	public static void addDoctorTable() {
		
	}
	
	public static void addPatientTable(PatientAddedObservationType pot) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCConnection.getConnection();
			String query = "INSERT INTO patient_ob_types p ( p.pid, p.ocid, p.table_name, p.display_name, p.additional_info, ";
			query += "p.number_of_columns, p.column_names_types, p.value_choices VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
			ps = conn.prepareStatement(query);
			int i = 1;
			ps.setInt( i++, pot.getPid());
			ps.setInt( i++, pot.getOcid());
			ps.setString( i++, pot.getTable_name());
			ps.setString( i++, pot.getDisplay_name());
			ps.setString( i++, pot.getAdditional_info());
			ps.setInt( i++, pot.getNumber_of_columns());
			ps.setString( i++, pot.getColumn_names_types());
			ps.setString( i++, pot.getValue_choices());
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
	
	private static void createPatientTable(PatientAddedObservationType pot) {
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
				query += colname + " "+ coltype;
			}
			query += "PRIMARY KEY (oid), FOREIGN KEY (oid) REFERENCES observations(oid)";
			ps = conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
	}
}
