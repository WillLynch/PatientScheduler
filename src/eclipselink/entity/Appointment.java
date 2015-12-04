package eclipselink.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.ManyToOne;

/**
 * CSC-370 Assignment 4
 * Appointment.java
 * Purpose: Provides a data container for Appointment entities,
 * with JPA annotations for persistence
 *
 * @author William Lynch
 */

@Entity
@Table
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appointmentId;
	private Date startTime;
	
	@ManyToOne
	private Patient patient;
	
	public Appointment(int appointmentId, Date startTime) {
		super();
		this.appointmentId = appointmentId;
		this.startTime = startTime;
	}
	
	public Appointment() {
		super();
	}
	
	public long getAppointmentId() {
		return this.appointmentId;
	}
	
	public void setAppointmentId(int num) {
		this.appointmentId = num;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Date date) {
		this.startTime = date;
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@Override
    public String toString() {
       return "Date: " + this.startTime + "\n" + this.getPatient();
    }
}
