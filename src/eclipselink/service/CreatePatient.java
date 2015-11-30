package eclipselink.service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import eclipselink.entity.Patient;

public class CreatePatient {
	public static void main( String[ ] args ) { 
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Clinic-Scheduler" );
	    EntityManager entitymanager = emfactory.createEntityManager( );
	    entitymanager.getTransaction( ).begin( );
	    Patient patient = new Patient( ); 
	    patient.setPatientId(55);
	    patient.setFirstName( "Gopal" );
	    patient.setLastName( "sdfsd" );
	    patient.setPhoneNumber( "111-1111" );
	      
	    entitymanager.persist( patient );
	    entitymanager.getTransaction( ).commit( );

	    entitymanager.close( );
	    emfactory.close( );
	}
}
