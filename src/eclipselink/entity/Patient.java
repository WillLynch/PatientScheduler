package eclipselink.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

/**
 * CSC-370 Assignment 4
 * Patient.java
 * Purpose: Provides a data container for Patient entities,
 * with JPA annotations in order to persist the data
 *
 * @author William Lynch
 */

@Entity
@Table
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private long patientId;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	@OneToMany(targetEntity=Appointment.class, cascade = CascadeType.ALL)
	private Set<Appointment> appointments;
	
	public Patient(int patientId, String phoneNumber, String firstName, String lastName) {
		super();
		this.patientId = patientId;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Patient() {
		super();
	}
	
	public long getPatientId() {
		return this.patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
		appointment.setPatient(this);
	}
	
	@Override
    public String toString() {
       return "First name: " + this.firstName + "\n" +
    		   "Last Name: " + this.lastName + "\n" + 
    		   "Phone Number: " + this.phoneNumber;
    }
}
