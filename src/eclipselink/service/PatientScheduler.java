package eclipselink.service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import eclipselink.entity.Patient;
import eclipselink.entity.Appointment;

import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * CSC-370 Assignment 4
 * PatientScheduler.java
 * Purpose: Provides a CLI application for operating on Patient 
 * 			and Appointment entities
 *
 * @author William Lynch
 */

public class PatientScheduler {
	/** 
	 * Runs the right database operation based on CLI input
	 * @param args Command Line Arguments
	 */
	public static void main( String[ ] args ) { 
		if (args.length != 1)
			System.out.println("Wrong number of arguments. Refer to README for operation instructions.");
		
		switch (args[0]) {
		case "create-patient":
			CreatePatient();
			break;
		case "find-patient":
			FindPatient();
			break;
		case "delete-patient":
			DeletePatient();
			break;
		case "create-appointment":
			CreateAppointment();
			break;
		case "find-appointment":
			FindAppointment();
			break;
		case "delete-appointment":
			DeleteAppointment();
			break;
		default:
			System.out.println("Unrecognized option. Refer to README for operation instructions.");
		}
	}
	
	/**
	 * Prompts user for info to input a Patient in the DB
	 */
	public static void CreatePatient() {
		Scanner userInput = new Scanner(System.in);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Clinic-Scheduler" );
	    EntityManager entitymanager = emfactory.createEntityManager( );
	    entitymanager.getTransaction( ).begin( );
	    Patient patient = new Patient( );
	    
	    System.out.println("Enter new Patient first name:");
	    patient.setFirstName(userInput.next());
	    System.out.println("Enter new patient last name:");
	    patient.setLastName(userInput.next());
	    System.out.println("Enter new patient phonenumber:");
	    patient.setPhoneNumber(userInput.next());
	      
	    entitymanager.persist( patient );
	    entitymanager.getTransaction( ).commit( );
	    
	    System.out.println("Created patient with id: " + patient.getPatientId());
	    userInput.close();
	    entitymanager.close( );
	    emfactory.close( );
	}
	/**
	 * Prompts a user for last name to lookup patients
	 */
	public static void FindPatient() {
		Scanner userInput = new Scanner(System.in);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Clinic-Scheduler" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		entitymanager.getTransaction( ).begin( );
		
		System.out.println("Enter patient last name:");
		String patientLastName = userInput.next();
		
		Query query = entitymanager.createQuery("SELECT e FROM Patient e WHERE e.lastName = :name")
				.setParameter("name", patientLastName);
		List<Patient> patientResults = query.getResultList();
		if (patientResults.size() == 0)
			System.out.println("No Results");
		else {
			System.out.println("\nResults:\n");
			for (Patient e: patientResults) {
				System.out.println(e);
				System.out.println();
			}
		}
		userInput.close();
	    entitymanager.close( );
	    emfactory.close( );
	}
	/**
	 * Prompts a user for a user id, to specify the patient to be deleted
	 */
	public static void DeletePatient() {
		Scanner userInput = new Scanner(System.in);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Clinic-Scheduler" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		entitymanager.getTransaction( ).begin( );
		System.out.println("Enter user id:");
		Patient patient = entitymanager.find(Patient.class, userInput.nextLong());
		entitymanager.remove(patient);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		userInput.close();
		System.out.println("Patient deleted");
	}
	/**
	 * Prompts a user for info to create an appointment in the DB
	 */
	public static void CreateAppointment() {
		Scanner userInput = new Scanner(System.in);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Clinic-Scheduler" );
	    EntityManager entitymanager = emfactory.createEntityManager( );
	    entitymanager.getTransaction( ).begin( );
	    Appointment appointment = new Appointment( );
	    
	    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    System.out.println("Enter new appointment date and time (yyyy-MM-dd):");
	    String dateString = userInput.next();
	    try {
			Date dateObject = sdf.parse(dateString);
		    appointment.setStartTime(dateObject);
		} catch (ParseException e) {
			System.out.println("wrong date format supplied");
			e.printStackTrace();
		} 
	    
	    System.out.println("Enter patient id:");
	    Patient appointmentPatient = entitymanager.find(Patient.class,  userInput.nextLong());
	    appointment.setPatient(appointmentPatient);
	    appointmentPatient.addAppointment(appointment);
	    
	    entitymanager.persist( appointment );
	    entitymanager.getTransaction( ).commit( );
	    
	    System.out.println("Created appointment with id: " + appointment.getAppointmentId());
	    userInput.close();
	    entitymanager.close( );
	    emfactory.close( );
	}
	
	/**
	 * Prompts a user for an appointment date range to find appointments within range
	 */
	public static void FindAppointment() {
		Scanner userInput = new Scanner(System.in);
	    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Clinic-Scheduler" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		entitymanager.getTransaction( ).begin( );
		
		Date timeRangeStart = null;
		Date timeRangeEnd = null;
		try {
			System.out.println("Enter time range start:");
			timeRangeStart = sdf.parse(userInput.next());
			System.out.println("Enter time range end:");
			timeRangeEnd = sdf.parse(userInput.next());
		} catch (ParseException e1) {
			System.out.println("Wrong date format");
			e1.printStackTrace();
		}

		Query query = entitymanager.createQuery("SELECT e FROM Appointment e " +
				"WHERE e.startTime BETWEEN :start AND :end")
				.setParameter("start", timeRangeStart)
				.setParameter("end", timeRangeEnd);
		
		List<Appointment> appointmentResults = query.getResultList();
		if (appointmentResults.size() == 0)
			System.out.println("No results.");
		else {
			System.out.println("\nResults:\n");
			for (Appointment e: appointmentResults) {
				System.out.println(e);
				System.out.println();
			}
		}
	}
	
	/**
	 * Prompts a user for appointment id in order to delete that appointment
	 */
	public static void DeleteAppointment() {
		Scanner userInput = new Scanner(System.in);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Clinic-Scheduler" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		entitymanager.getTransaction( ).begin( );
		System.out.println("Enter appointment id:");
		Appointment appointment = entitymanager.find(Appointment.class, userInput.nextLong());
		if (appointment == null) {
			System.out.println("No appointment found");
			System.exit(0);
		}
		entitymanager.remove(appointment);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		userInput.close();
		System.out.println("Appointment deleted");
	}
}
