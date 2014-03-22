import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.ObservationType;
import beans.Patient;
import dao.oracle.ObservationTypeDAO;
import dao.oracle.PatientDAO;


public class ObservationTypesDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetObservationTypesForPatient() {
		PatientDAO pdao = new PatientDAO();
		Patient patient = pdao.getPatient(2);
		List<ObservationType> list = ObservationTypeDAO.getObservationTypesForPatient(patient);
		for ( int i = 0; i < list.size(); i++ ) {
			System.out.println( list.get(i).getDisplay_name() );
		}
	}

}
