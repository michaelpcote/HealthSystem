package dao.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Patient;
import beans.Prescription;
import connection.JDBCConnection;

/**
 * This class will handle all interactions between the beans and db for prescriptions
 * @author SG0214981
 *
 */
public class PrescriptionDAO {

	/**
	 * Add a new prescription
	 * @param pre - The prescription to insert
	 */
	public static void addPrescription( Prescription pre ) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = JDBCConnection.getConnection();
        	String query = "INSERT INTO prescriptions ( for, phone, drug_name, dosage, start_date, end_date )";
        	query += " VALUES ( ?, ?, ?, ?, ?, ? )";
        	ps = conn.prepareStatement(query);
        	int index = 1;
        	ps.setInt( index++, pre.getPid());
        	ps.setString( index++, pre.getPhone());
        	ps.setString( index++, pre.getDrug_name());
        	ps.setInt( index++, pre.getDosage());
        	ps.setDate( index++, pre.getStartDate() );
        	ps.setDate( index++, pre.getEndDate());
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    }
	
	/**
	 * Edit a specific prescription
	 * @param pre
	 */
	public static void editPrescription( Prescription pre ) {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = JDBCConnection.getConnection();
        	String query = "UPDATE prescriptions SET for_pid = ?, phone = ?, drug_name = ?, dosage = ?, ";
        	query += " start_date = ?, end_date = ? WHERE prescription = ?";
        	ps = conn.prepareStatement(query);
        	int index = 1;
        	ps.setInt( index++, pre.getPid());
        	ps.setString( index++, pre.getPhone());
        	ps.setString( index++, pre.getDrug_name());
        	ps.setInt( index++, pre.getDosage());
        	ps.setDate( index++, pre.getStartDate() );
        	ps.setDate( index++, pre.getEndDate());
        	ps.setInt( index++, pre.getPrescription_num());
        } catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
    }
	
	/**
	 * Get all prescriptions for a patient
	 * @param patient - The patient to get prescriptions for
	 * @return a list of all prescriptions
	 */
	public static List<Prescription> getAllPrescriptions( Patient patient ) {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Prescription> pres = new ArrayList<Prescription>();
        try {
        	// Get a connection to the specified JDBC URL.
    		conn = JDBCConnection.getConnection();
    		String query = "SELECT pre.for, pre.phone, pre.drug_name, pre.dosage, pre.start_date, pre.end_date, ";
    		query += "p.fname, p.lname FROM patients p, prescriptions pre WHERE p.pid = ? AND ";
    		query += "p.pid = pre.for";
			ps = conn.prepareStatement(query);
			int index = 1;
    		ps.setDouble( index++, patient.getPid());
    		rs = ps.executeQuery();
    		while ( rs.next() ) {
    			Prescription pre = new Prescription();
    			pre.setFname(rs.getString("fname"));
    			pre.setLname(rs.getString("lname"));
    			pre.setDrug_name(rs.getString("drug_name"));
    			pre.setDosage(rs.getInt("dosage"));
    			pre.setPhone(rs.getString("phone"));
    			pre.setPid(rs.getInt("for"));
    			pre.setEnd(rs.getDate("end_date"));
    			pre.setStartDate(rs.getDate("start_date"));
    			pres.add(pre);
    		}
    	} catch(SQLException e) {
           	e.printStackTrace();
        } finally {
			JDBCConnection.closeConnection(conn, ps, null);
		}
        return pres;
    }
}
