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
			ps = conn.prepareStatement("SELECT * FROM patients p where p.pid = ? AND p.password = ?");
			ps.setInt( 1, user);
			ps.setString(2, pw);
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
