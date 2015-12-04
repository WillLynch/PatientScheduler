CREATE DATABASE IF NOT EXISTS patient_scheduler;
 
USE patient_scheduler;

CREATE TABLE `PATIENT` (
  `PATIENTID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `PHONENUMBER` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`PATIENTID`)
);

CREATE TABLE `APPOINTMENT` (
  `APPOINTMENTID` int(11) NOT NULL AUTO_INCREMENT,
  `STARTTIME` datetime DEFAULT NULL,
  `PATIENT_PATIENTID` int(11) DEFAULT NULL,
  PRIMARY KEY (`APPOINTMENTID`)
);

CREATE TABLE PATIENT_APPOINTMENT ( 
  Patient_PATIENTID INT NOT NULL, 
  appointments_APPOINTMENTID INT NOT NULL
  PRIMARY KEY (Patient_PATIENTID, appointments_APPOINTMENTID)
);


