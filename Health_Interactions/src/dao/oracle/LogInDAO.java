package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.JDBCConnection;

/**
 * A class to handle log ins.
 * @author SG0214981
 *
 */
public class LogInDAO {

	/**
	 * This will work temporarily for patients only. I will update it to include doctors and other types once I've added those columns
	 * @param user - The number ID they enter
	 * @param pw - The password they enter
	 * @return "Patient" "Physician" "Social Worker" or ""
	 */
	public static String allowLogIn( int user, String pw ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn2 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		Connection conn3 = null;
		PreparedStatement ps3 = null;
		ResultSet rs3 = null;
		try {
			conn = JDBCConnection.getConnection();
			String query = "SELECT * FROM patients p ";
			query += "where p.pid = ? AND p.password = ?";
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setInt( index++, user);
			ps.setString(index++, pw);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				return "Patient";
			}
			conn2 = JDBCConnection.getConnection();
			query = "SELECT * FROM physicians phy ";
			query += "where phy.phy_id = ? AND phy.password = ?";
			ps2 = conn.prepareStatement(query);
			index = 1;
			ps2.setInt( index++, user);
			ps2.setString(index++, pw);
			rs2 = ps2.executeQuery();
			if ( rs2.next() ) {
				return "Physician";
			}
			conn3 = JDBCConnection.getConnection();
			query = "SELECT * FROM social_workers sw ";
			query += "where sw.sid = ? AND sw.password = ?";
			ps3 = conn.prepareStatement(query);
			index = 1;
			ps3.setInt( index++, user);
			ps3.setString(index++, pw);
			rs3 = ps3.executeQuery();
			if ( rs3.next() ) {
				return "Social Worker";
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
			JDBCConnection.closeConnection(conn2, ps2, rs2);
			JDBCConnection.closeConnection(conn3, ps3, rs3);
		}
		//Return false if this doesn't match
		return "";
	}
}
