package eclipselink.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;

import org.eclipse.persistence.internal.jpa.parsing.TemporalLiteralNode.TemporalType;

@Entity
@Table
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appointmentId;
	@Temporal(TemporalType.TIME)
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
	
	public int getAppointmentId() {
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
       return "Patient:" + this.patient.getLastName() + "Date:" + this.startTime;
    }
}
