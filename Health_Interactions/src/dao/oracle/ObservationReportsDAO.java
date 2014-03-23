package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ObservationType;
import beans.Patient;
import connection.JDBCConnection;

public class ObservationReportsDAO {

	/**
	 * Find the average of an integer column
	 * @param ot - An observation type - should have an integer
	 * @param patient_conditions takes a variable number of integers representing observation types
	 * @return an String - it will return the name of the column that is an int and the value like
	 * "colname:value,colname:value" one column and value for each integer column
	 */
	public static String averageAmount(ObservationType ot, int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return null;
		}
		String averages = "";
		String database = ot.getTable_name();
		String types[] = parseColumnTypes( ot.getColumn_names_types());
		String names[] = parseColumnNames( ot.getColumn_names_types());
		boolean first = true;
		for ( int i = 0; i < types.length; i++ ) {
			if ( types[i].equals("int")) {
				int avg = -1;
				String name = names[i];
				if ( first ) {
					averages += name;
					first = false;
				} else {
					averages += ","+name;
				}
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "SELECT AVG(u."+name+") AS avg FROM " + database + " u";
				query += ", observations o, patient_conditions pc WHERE ";
				query += "u.oid = o.oid AND o.pid = pc.pid AND pc.cid = ? ";
				for ( int j = 1; j < patient_conditions.length; j++ ) {
					query += "OR pc.cid = ? ";
				}
				try {
					conn = JDBCConnection.getConnection();
					ps = conn.prepareStatement(query);
					for ( int j = 0; j < patient_conditions.length; j++ ) {
						ps.setInt( (j + 1), patient_conditions[j]);
					}
					rs = ps.executeQuery();
					if ( rs.next() ) {
						avg = rs.getInt("avg");
					}
					averages += ":"+avg;
				} catch (SQLException e) {
					System.out.println(e.toString());
				} finally {
					JDBCConnection.closeConnection(conn, ps, rs);
				}
			}
		}
		return averages;
	}
	
	/**
	 * Select the lowest weight by observation types
	 * @param patient_conditions
	 * @return
	 */
	public static String lowestAmount(ObservationType ot, int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return null;
		}
		String lowest = "";
		String database = ot.getTable_name();
		String types[] = parseColumnTypes( ot.getColumn_names_types());
		String names[] = parseColumnNames( ot.getColumn_names_types());
		boolean first = true;
		for ( int i = 0; i < types.length; i++ ) {
			if ( types[i].equals("int")) {
				int min = -1;
				String name = names[i];
				if ( first ) {
					lowest += name;
					first = false;
				} else {
					lowest += ","+name;
				}
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "SELECT MIN(u."+name+") AS minimum FROM " + database + " u";
				query += ", observations o, patient_conditions pc WHERE ";
				query += "u.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
				for ( int j = 1; j < patient_conditions.length; j++ ) {
					query += "OR pc.cid = ? ";
				}
				try {
					conn = JDBCConnection.getConnection();
					ps = conn.prepareStatement(query);
					for ( int j = 0; j < patient_conditions.length; j++ ) {
						ps.setInt( (j + 1), patient_conditions[j]);
					}
					rs = ps.executeQuery();
					if ( rs.next() ) {
						min = rs.getInt("minimum");
					}
					lowest += ":"+min;
				} catch (SQLException e) {
					System.out.println(e.toString());
				} finally {
					JDBCConnection.closeConnection(conn, ps, rs);
				}
			}
		}
		return lowest;
	}
	
	public static String highestAmount(ObservationType ot, int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return null;
		}
		String highest = "";
		String database = ot.getTable_name();
		String types[] = parseColumnTypes( ot.getColumn_names_types());
		String names[] = parseColumnNames( ot.getColumn_names_types());
		boolean first = true;
		for ( int i = 0; i < types.length; i++ ) {
			if ( types[i].equals("int")) {
				int max = -1;
				String name = names[i];
				if ( first ) {
					highest += name;
					first = false;
				} else {
					highest += ","+name;
				}
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "SELECT MAX(u."+name+") AS maximum FROM " + database + " u";
				query += ", observations o, patient_conditions pc WHERE ";
				query += "u.oid = o.oid AND o.pid = pc.pid AND pc.cid = ?";
				for ( int j = 1; j < patient_conditions.length; j++ ) {
					query += "OR pc.cid = ? ";
				}
				try {
					conn = JDBCConnection.getConnection();
					ps = conn.prepareStatement(query);
					for ( int j = 0; j < patient_conditions.length; j++ ) {
						ps.setInt( (j + 1), patient_conditions[j]);
					}
					rs = ps.executeQuery();
					if ( rs.next() ) {
						max = rs.getInt("maximum");
					}
					highest += ":"+max;
				} catch (SQLException e) {
					System.out.println(e.toString());
				} finally {
					JDBCConnection.closeConnection(conn, ps, rs);
				}
			}
		}
		return highest;
	}
	
	/**
	 * Get a list of patients that have a number equal to the highest number of a column given a 
	 * variable number of patient conditions. If the observation type that is passed in has two 
	 * integer columns, then the list will contain patients that have the highest of one or the other.
	 * If the same person has the highest in both columns, only a single patient will be returned.
	 * @param patient_conditions
	 * @return List of patients with that blood pressure
	 */
	public static List<Patient> getPatientsWithHighest(ObservationType ot, int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return null;
		}
		String database = ot.getTable_name();
		String types[] = parseColumnTypes( ot.getColumn_names_types());
		String names[] = parseColumnNames( ot.getColumn_names_types());
		List<Patient> patients = null;
		for ( int i = 0; i < types.length; i++ ) {
			if ( types[i].equals("int")) {
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "SELECT DISTINCT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status, "+ 
							   "p.password FROM patients p, "+ database + " u, observations o, patient_conditions pc WHERE ";
				query += "o.oid = u.oid AND o.pid = p.pid AND p.pid = pc.pid AND ";
				if ( patients != null && patients.size() != 0 ) {
					query += "( p.pid <> ? ";
					if ( patients.size() > 1 ) {
						for ( int j = 1; j < patients.size(); j++ ) {
							query += "AND p.pid <> ? ";
						}
					}
					query += ") AND ";
				}
				query += "( pc.cid = ? ";
				for ( int j = 1; j < patient_conditions.length; j++ ) {
					query += "OR pc.cid = ? ";
				}
				query += ") AND (u."+names[i]+") = ";
				query += "(SELECT MAX( u2."+names[i]+" ) FROM "+ database+" u2, ";
				query += "observations o2, patient_conditions pc WHERE ";
				query += "u2.oid = o2.oid AND o2.pid = pc.pid AND ( pc.cid = ? ";
				for ( int j = 1; j < patient_conditions.length; j++ ) {
					query += "OR pc.cid = ? ";
				}
				query += ") )";
				try {
					conn = JDBCConnection.getConnection();
					ps = conn.prepareStatement(query);
					int index = 1;
					if ( patients != null && patients.size() != 0 ) {
						for ( int j = 0; j < patients.size(); j++ ) {
							ps.setDouble(index++, patients.get(j).getPid());
						}
					}
					for ( int j = 0; j < (patient_conditions.length ); j++ ) {
						ps.setInt( index++, patient_conditions[j]);
					}
					for ( int j = patient_conditions.length; j < (patient_conditions.length * 2); j++ ) {
						ps.setInt( index++, patient_conditions[j - patient_conditions.length]);
					}
					rs = ps.executeQuery();
					if ( patients == null ) {
						patients = PatientDAO.loadPatients(rs);
					} else {
						List<Patient> temp = PatientDAO.loadPatients(rs);
						for ( int j = 0; j < temp.size(); j++ ) {
							patients.add(temp.get(j));
						}
					}
				} catch (SQLException e) {
					System.out.println(e.toString());
				} finally {
					JDBCConnection.closeConnection(conn, ps, rs);
				}
			}
		}
		return patients;
	}
	
	/**
	 * Get a list of patients that have a number equal to the lowest number of an int type column given a 
	 * variable number of patient conditions. If the observation type that is passed in has two 
	 * integer columns, then the list will contain patients that have the highest of one or the other.
	 * If the same person has the highest in both columns, only a single patient will be returned.
	 * @param patient_conditions
	 * @return List of patients with that blood pressure
	 */
	public static List<Patient> getPatientsWithLowest(ObservationType ot, int ... patient_conditions) {
		if ( patient_conditions.length == 0 ) {
			return null;
		}
		String database = ot.getTable_name();
		String types[] = parseColumnTypes( ot.getColumn_names_types());
		String names[] = parseColumnNames( ot.getColumn_names_types());
		List<Patient> patients = null;
		for ( int i = 0; i < types.length; i++ ) {
			if ( types[i].equals("int")) {
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "SELECT DISTINCT p.pid, p.fname, p.lname, p.address, p.city, p.state, p.zip, p.dob, p.sex, p.public_status, "+ 
							   "p.password FROM patients p, "+ database + " u, observations o, patient_conditions pc WHERE ";
				query += "o.oid = u.oid AND o.pid = p.pid AND p.pid = pc.pid AND ";
				if ( patients != null && patients.size() != 0 ) {
					query += "( p.pid <> ? ";
					if ( patients.size() > 1 ) {
						for ( int j = 1; j < patients.size(); j++ ) {
							query += "AND p.pid <> ? ";
						}
					}
					query += ") AND ";
				}
				query += "( pc.cid = ? ";
				for ( int j = 1; j < patient_conditions.length; j++ ) {
					query += "OR pc.cid = ? ";
				}
				query += ") AND (u."+names[i]+") = ";
				query += "(SELECT MIN( u2."+names[i]+" ) FROM "+ database+" u2, ";
				query += "observations o2, patient_conditions pc WHERE ";
				query += "u2.oid = o2.oid AND o2.pid = pc.pid AND ( pc.cid = ? ";
				for ( int j = 1; j < patient_conditions.length; j++ ) {
					query += "OR pc.cid = ? ";
				}
				query += ") )";
				try {
					conn = JDBCConnection.getConnection();
					ps = conn.prepareStatement(query);
					int index = 1;
					if ( patients != null && patients.size() != 0 ) {
						for ( int j = 0; j < patients.size(); j++ ) {
							ps.setDouble(index++, patients.get(j).getPid());
						}
					}
					for ( int j = 0; j < (patient_conditions.length ); j++ ) {
						ps.setInt( index++, patient_conditions[j]);
					}
					for ( int j = patient_conditions.length; j < (patient_conditions.length * 2); j++ ) {
						ps.setInt( index++, patient_conditions[j - patient_conditions.length]);
					}
					rs = ps.executeQuery();
					if ( patients == null ) {
						patients = PatientDAO.loadPatients(rs);
					} else {
						List<Patient> temp = PatientDAO.loadPatients(rs);
						for ( int j = 0; j < temp.size(); j++ ) {
							patients.add(temp.get(j));
						}
					}
				} catch (SQLException e) {
					System.out.println(e.toString());
				} finally {
					JDBCConnection.closeConnection(conn, ps, rs);
				}
			}
		}
		return patients;
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
	 * Get all observations between two dates for a specific patient and observation type
	 * @param patient
	 * @param ot - Type of 
	 * @param startdate - The earlier date
	 * @param enddate - The later date
	 * @return String - This will be a comma separated list so that the first line will be the headers and the 
	 * rest of the string will be "header:header:header,value:value:value,value:value:value"
	 */
	public static String getObservationsBetween( Patient patient, ObservationType ot, String startdate, String enddate ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date start = Date.valueOf(startdate);
		Date end = Date.valueOf(enddate);
		String observations = "";
		try {
			String names[] = parseColumnNames( ot.getColumn_names_types() );
			String types[] = parseColumnTypes( ot.getColumn_names_types() );
			conn = JDBCConnection.getConnection();
			String query = "SELECT u.oid";//FROM diet d, observations o where o.pid = ? ";
			for ( int i = 0; i < names.length; i++ ) {
				query += ", u."+names[i];
			}
			query += " FROM "+ ot.getTable_name() + " u, observations o where o.pid = ? ";
			query += "AND o.type_id = ? AND o.oid = u.oid AND o.date_observed > ? AND o.date_observed < ?";
			ps = conn.prepareStatement(query);
			ps.setDouble( 1, patient.getPid() );
			ps.setInt( 2, ot.getType_id());
			ps.setDate(3, start);
			ps.setDate(4, end);
			rs = ps.executeQuery();
			observations = createHeaders( names );
			while ( rs.next() ) {
				observations += ",";
				for ( int i = 0; i < names.length; i++ ) {
					if ( types[i].equals("String")) {
						observations += rs.getString(names[i]);
					} else {
						observations += rs.getInt(names[i]);
					}
					if ( i != names.length - 1 ) {
						observations += ":";
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return observations;
	}
	
	/**
	 * Get all observations for a specific patient and observation type
	 * @param patient
	 * @param ot - Type of observation
	 * @return String - This will be a comma separated list so that the first line will be the headers and the 
	 * rest of the string will be "header:header:header,value:value:value,value:value:value"
	 */
	public static String viewObservationsForPatient( Patient patient, ObservationType ot ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String observations = "";
		try {
			String names[] = parseColumnNames( ot.getColumn_names_types() );
			String types[] = parseColumnTypes( ot.getColumn_names_types() );
			conn = JDBCConnection.getConnection();
			String query = "SELECT u.oid";//FROM diet d, observations o where o.pid = ? ";
			for ( int i = 0; i < names.length; i++ ) {
				query += ", u."+names[i];
			}
			query += " FROM "+ ot.getTable_name() + " u, observations o where o.pid = ? ";
			query += "AND o.type_id = ? AND o.oid = u.oid";
			ps = conn.prepareStatement(query);
			ps.setDouble( 1, patient.getPid() );
			ps.setInt( 2, ot.getType_id());
			rs = ps.executeQuery();
			observations = createHeaders( names );
			while ( rs.next() ) {
				observations += ",";
				for ( int i = 0; i < names.length; i++ ) {
					if ( types[i].equals("String")) {
						observations += rs.getString(names[i]);
					} else {
						observations += rs.getInt(names[i]);
					}
					if ( i != names.length - 1 ) {
						observations += ":";
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			JDBCConnection.closeConnection(conn, ps, rs);
		}
		return observations;
	}
	
	private static String createHeaders(String[] names) {
		String headers = names[0];
		for ( int i = 1; i < names.length; i++ ) {
			headers += ":"+names[i];
		}
		return headers;
	}

	private static String[] parseColumnNames(String columnInfo) {
		String both[] = columnInfo.split(",");
		String names[] = new String[both.length];
		for ( int i = 0; i < names.length; i++ ) {
			String combo[] = both[i].split(":");
			names[i] = combo[0];
		}
		return names;
	}
	
	private static String[] parseColumnTypes(String columnInfo) {
		String both[] = columnInfo.split(",");
		String values[] = new String[both.length];
		for ( int i = 0; i < values.length; i++ ) {
			String combo[] = both[i].split(":");
			values[i] = combo[1];
		}
		return values;
	}
	
}
