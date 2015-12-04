Author: William Lynch
CSC 370 Assignment 4

This document contains setup instructions, and instructions for this application.
The JAR file contains both my own code, contained within the 'eclipselink' package,
as well as the necessary dependencies. Additionally, a DDL script is included.

*******
*SETUP*
*******

1. Make sure mysql-server is installed
2. In the MySQL workbench, run the DBSetup.sql DDL script (located in the root folder of the .jar)
   to create the database
3. Extract the .jar file, and modify the property tags in META-INF/persistence.xml to 
   contain your local mysql url, user, and password
4. Execute the program with java -jar PatientScheduler.jar (in the root folder)

***********
*OPERATION*
***********

You must provide one argument to the application. This argument specifies what
CRUD operation to perform.

Creating a Patient:
-------------------
java -jar PatientScheduler.jar create-patient

You will then be prompted for the patient details. It will return the user ID
when a user has been created.


Creating an Appointment:
-------------------------
java -jar PatientScheduler.jar create-appointment

You will then be prompted for appointment details.It will return the appointment ID
when a user has been created.

Finding a patient:
-------------------
java -jar PatientScheduler.jar find-patient

You will be prompted for the user Id.

Finding an appointment:
-----------------------
java -jar PatientScheduler.jar find-appointment

You will be prompted for a date range.

Deleting a Patient:
-------------------
java -jar PatientScheduler.jar delete-patient

You will be prompted for the patient ID.

Deleting an appointment:
------------------------
java -jar PatientScheduler.jar delete-appointment

You will be prompted for the appointment ID.





