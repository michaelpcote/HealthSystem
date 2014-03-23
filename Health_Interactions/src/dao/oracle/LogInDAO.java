package dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.JDBCConnection;

public class LogInDAO {

	/**
	 * This will work temporarily for patients only. I will update it to include doctors and other types once I've added those columns
	 * @param user - The number ID they enter
	 * @param pw - The password they enter
	 * @return
	 */
	public static boolean allowLogIn( int user, String pw ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getConnection();
			String query = "SELECT * FROM patients p, physicians phy, social_workers sw ";
			query += "where ( p.pid = ? AND p.password = ? ) OR ( phy.phy_id = ? AND phy.password = ? ) ";
			query += "OR ( sw.sid = ? AND sw.password = ? )";
			ps = conn.prepareStatement(query);
			int index = 1;
			for ( int i = 0; i < 3; i++ ) {
				ps.setInt( index++, user);
				ps.setString(index++, pw);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		//Return false if this doesn't match
		return false;
	}
}
