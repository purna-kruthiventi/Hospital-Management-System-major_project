package com.project.hospitalManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class HospitalManagementSystem {

	private static final String url = "jdbc:mysql://localhost:3306/purna";
	
	private static final String username = "root";
	
	private static final String password = "757933591836";
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Patient patient = new Patient(connection,scanner);
			Doctors doctor= new Doctors(connection);
			while(true) {
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("1.  Add Patient");
				System.out.println("2.  View Patients");
				System.out.println("3.  View Doctors");
				System.out.println("4.  Book Appointment");
				System.out.println("5.  Exit");
				System.out.println("Enter your choise: ");
				int choise = scanner.nextInt();
				
				switch(choise) {
				case 1:
					// add patient
					patient.addPatient();
					System.out.println();
					break;
				case 2:
					// view patient 
					patient.viewPatients();
					System.out.println();
					break;
				case 3:
					//view doctors
					doctor.viewDoctors();
					System.out.println();
					break;
				case 4:
					// book appointment
					bookAppointment(patient,doctor,connection,scanner);
					
				case 5:
					return;
				default: 
					System.out.println("Enter valid choise!!");
					break;
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}
	public static void bookAppointment(Patient patient,Doctors doctor,Connection connection, Scanner scanner) {
		System.out.println("Enter patient ID: ");
		int patientId = scanner.nextInt();
		
		System.out.println("Enter Doctor ID: ");
		int doctorId = scanner.nextInt();
		
		System.out.println("enter appointment date (yyyy-mm-dd): ");
		String appointmentDate = scanner.next();
		
		if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
			if(checkDoctorAvailbility(doctorId,appointmentDate, connection)) {
				String appointmentQuery = "insert into appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
				try {
					PreparedStatement pstmt = connection.prepareStatement(appointmentQuery);
					pstmt.setInt(1, patientId);
					pstmt.setInt(2, doctorId);
					pstmt.setString(3, appointmentDate);
					
					int pt = pstmt.executeUpdate();
					if(pt>0) {
						System.out.println("Appointment Booked");
					}else {
						System.out.println("Failed to book appointment");
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("doctor not availble on this date");
			}
			
		}else {
			System.out.println("either doctor or patient doesn't exist");
		}

	}
	private static boolean checkDoctorAvailbility(int doctorId, String appointmentDate, Connection connection) {
		// TODO Auto-generated method stub
		return false;
	}
}
