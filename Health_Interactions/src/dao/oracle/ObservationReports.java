package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Diet;
import beans.ObservationTypes;
import beans.Patient;
import connection.JDBCConnection;

public class ObservationReports {

	/**
	 * Find the average calories consumed for a variable number of patient conditions
	 * @param patient_conditions takes a variable number of integers representing observation types
	 * @return an int - the average
	 */
	public static int dietAverageAmount(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgCalories = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(d.calories) AS avg FROM diet d, observations o, patient_conditions pc WHERE ";
		query += "d.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgCalories = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgCalories;
	}
	
	/**
	 * Find the average weight based on patient_conditions
	 * @param patient_conditions - a variable number of observation types
	 * @return an int
	 */
	
	public static int weightAverage(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgWeight = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(w.pounds) AS avg FROM weight w, observations o, patient_conditions pc WHERE ";
		query += "w.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgWeight = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgWeight;
	}
	
	/**
	 * The average exercise time for a variable number of patient conditions
	 * @param patient_conditions
	 * @return
	 */
	public static int exerciseAvgTime(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgTime = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(e.minutes) AS avg FROM exercise e, observations o, patient_conditions pc WHERE ";
		query += "e.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgTime = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgTime;
	}
	
	/**
	 * Get the average blood pressure for a variable number of patient conditions
	 * @param patient_conditions
	 * @return
	 */
	public static String bloodPressureAvg(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return null;
		}
		int avgDiastolic = 0;
		int avgSystolic = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(bp.diastolic) AS avgD, AVG(bp.systolic) AS avgS FROM blood_pressure bp, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgDiastolic = rs.getInt("avgD");
				avgSystolic = rs.getInt("avgS");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgSystolic+"/"+avgDiastolic;
	}
	
	/**
	 * Avg exercise tolerance for a given number of patient conditions
	 * @param patient_conditions
	 * @return
	 */
	public static int exerciseToleranceAvg(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgTime = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(et.steps) AS avg FROM exercise_tolerance et, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgTime = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgTime;
	}
	
	/**
	 * Average oxygen based on a variable number of patient conditions
	 * @param patient_conditions
	 * @return
	 */
	public static int oxygenSaturationAvg(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgTime = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(os.amount) AS avg FROM oxygen_saturation os, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgTime = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgTime;
	}
	
	/**
	 * The average amount of pain felt for a variable number of patient conditions
	 * @param patient_conditions
	 * @return
	 */
	public static int painAvg(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgTime = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(p.rating) AS avg FROM pain p, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgTime = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgTime;
	}
	
	/**
	 * The average number of contractions felt for a variable number of patient conditions
	 * @param patient_conditions
	 * @return
	 */
	public static int contractionAvg(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgTime = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(c.frequency) AS avg FROM contraction c, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgTime = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgTime;
	}
	
	/**
	 * The average temperature for a given number of patient conditions
	 * @param patient_conditions
	 * @return
	 */
	public static int temperatureAvg(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int avgTime = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT AVG(t.temp) AS avg FROM temperature t, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				avgTime = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return avgTime;
	}
	
	/**
	 * Select the mood that has been chosen the most
	 * @return
	 */
	public static String mostOccurringMood() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String mood = null;
		String query = "SELECT m.mood FROM mood m GROUP BY m.mood HAVING COUNT(*) = "
				+"( SELECT MAX(moodcount) FROM "
				+"(SELECT COUNT(*) AS moodcount FROM mood2 GROUP BY m2.mood";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				mood = rs.getString("mood");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return mood;
	}
	
	/**
	 * Select the mood that has been chosen the least
	 * @return
	 */
	public static String leastOccurringMood() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String mood = null;
		String query = "SELECT m.mood FROM mood m GROUP BY m.mood HAVING COUNT(*) = "
				+"( SELECT MIN(moodcount) FROM "
				+"(SELECT COUNT(*) AS moodcount FROM mood2 GROUP BY m2.mood";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				mood = rs.getString("mood");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return mood;
	}
	
	/**
	 * Select the lowest weight by observation types
	 * @param patient_conditions
	 * @return
	 */
	public static int lowestWeight(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int lowestWeight = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT MIN(w.pounds) FROM weight w, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				lowestWeight = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
		return lowestWeight;
	}
	
	/**
	 * Select the lowest weight by observation types
	 * @param patient_conditions
	 * @return
	 */
	public static int highestWeight(int ... patient_conditions ) {
		if ( patient_conditions.length == 0 ) {
			return -1;
		}
		int lowestWeight = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT MAX(w.pounds) FROM weight w, observations o, patient_conditions pc WHERE ";
		query += "bp.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				lowestWeight = rs.getInt("avg");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return lowestWeight;
	}
	
	/**
	 * Find the highest blood pressure for a variable number of patient conditions
	 * @param patient_conditions
	 * @return List of patients with that blood pressure
	 */
	public static List<Patient> highestBloodPressure(int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return null;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status "+ 
					   "FROM patients p, blood_pressure bp, observations o, patient_conditions pc WHERE ";
		query += "o.oid = bp.oid AND o.pid = p.pid AND p.pid = pc.pid AND ( pc.cid = ? ";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		query += ") AND (bp.systolic + bp.diastolic) = ";
		query += "SELECT MAX( bp2.systolic + bp2.diastolic ) FROM blood_pressure bp2, observations o2, patient_conditions pc WHERE ";
		query += "bp2.oid = o2.oid AND o2.pid = pc.pid AND ( pc.cid = ?";
		for ( int i = 1; i < patient_conditions.length; i++ ) {
			query += "OR pc.cid = ? ";
		}
		query += ")";
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement(query);
			for ( int i = 0; i < patient_conditions.length; i++ ) {
				ps.setInt( (i + 1), patient_conditions[i]);
			}
			rs = ps.executeQuery();
			if ( rs.next() ) {
				return PatientDAO.loadPatients(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return null;
	}
	
	/**
	 * The number of observations that an individual patient has made
	 * @param patient
	 * @return
	 */
	public static int numberOfObservationsMadeByPatient( Patient patient) {
		int numberOfObservations = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT COUNT(*) AS obsnum FROM observations o WHERE o.pid = ?");
			ps.setDouble( 1, patient.getPid());
			rs = ps.executeQuery();
			if ( rs.next() ) {
				numberOfObservations = rs.getInt("obsnum");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return numberOfObservations;
	}
	
	/**
	 * The number of observations that any patient has entered for a given observation type
	 * @param obs_type
	 * @return
	 */
	public static int numberOfObservationsByObsType( int obs_type ) {
		int numberOfObservations = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCConnection.getConnection();
			ps = conn.prepareStatement("SELECT COUNT(*) AS obsnum FROM observations o WHERE o.type_id = ?");
			ps.setInt(1, obs_type);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				numberOfObservations = rs.getInt("obsnum");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return numberOfObservations;
	}
	
	/**
	 * Get all diet observations between two dates for a specific patient
	 * @param patient
	 * @param ot - Type of 
	 * @param startdate - The earlier date
	 * @param enddate - The later date
	 * @return
	 */
	public static List<Diet> getDietObservationsBetween( Patient patient, ObservationTypes ot, String startdate, String enddate ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Diet> diets = new ArrayList<Diet>();
		Date start = Date.valueOf(startdate);
		Date end = Date.valueOf(enddate);
		try {
			conn = JDBCConnection.getConnection();
			String query = "SELECT d.oid, d.food_type, d.calories FROM diet d, observations o where o.pid = ? ";
			query += "AND o.type_id = ? AND o.oid = d.oid AND o.date_observed > ? AND o.date_observed < ?";
			ps = conn.prepareStatement(query);
			ps.setDouble( 1, patient.getPid() );
			ps.setInt( 2, ot.getType_id());
			ps.setDate(3, start);
			ps.setDate(4, end);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				Diet diet = new Diet( );
				diet.setCalories(rs.getInt("calories"));
				diet.setFood(rs.getString("food_type"));
				diets.add( diet );
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return diets;
	}
	
}
