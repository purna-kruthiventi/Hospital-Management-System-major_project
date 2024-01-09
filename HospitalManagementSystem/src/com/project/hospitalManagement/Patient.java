package com.project.hospitalManagement;
import java.sql.*;
import java.util.*;



public class Patient {
	  private Connection connection;
	  private Scanner scanner;
	   
	   public Patient(Connection connection,Scanner scanner) {
		super();
		this.connection=connection;
		this.scanner=scanner;
	}

	  // Scanner sc = new Scanner(System.in);
	   
	   public void addPatient() {
		
		System.out.print("enter the patient name: ");
		String  name = scanner.next();
		System.out.println("enter the patient age");
	    int age = scanner.nextInt();
		System.out.println("enter the patient gender");
		String gender= scanner.next();
	
	  
	   try {
		  // Class.forName("com.mysql.jdbc.Driver");
	         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/purna","root","757933591836");
		     PreparedStatement pstmt = connection.prepareStatement("insert into Patients(name,age,gender) values(?,?,?)");
		     pstmt.setString(1, name);
		     pstmt.setInt(2, age);
		     pstmt.setString(3, gender);
		     
		     int rows = pstmt.executeUpdate();
		     if(rows>0) {
		    	 System.out.println("patient added successfully");
		     }else {
		    	 System.out.println("failed to add patient");
		     }
		    
		   }catch(Exception e) {
		   e.printStackTrace();
	   }
   }
	   public void viewPatients() {
		  try {
			//  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/purna","root","757933591836");
			  PreparedStatement pstmt = connection.prepareStatement("select * from Patients");
			  ResultSet rs = pstmt.executeQuery();
			  System.out.println("retreiving patients....");
			  System.out.println("+----------------+---------------+--------------+");
			  while(rs.next()) {
				  String name = rs.getString("name");
				  int age= rs.getInt("age");
				  String gender = rs.getString("gender");
				  System.out.println("name: "+name+"|age: "+age+"|gender: "+gender);
			  }
		  }catch(SQLException e) {
			  e.printStackTrace();
		  }
	   }
	   
	   public boolean getPatientById(int id) {
		   try {
			 //  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/purna","root","757933591836");
			   String query = "select * from Patients where id=?";
			   PreparedStatement pstmt = connection.prepareStatement(query);
			   pstmt.setInt(1, id);
			  ResultSet rst= pstmt.executeQuery();
			    return rst.next(); 
			    
				/*  String name =rst.getString("name");
				  int age = rst.getInt("age");
				  String gender = rst.getString("gender");
				  System.out.print("name: "+name+"age: "+age+"gender: "+gender);  */
			 
				 // System.out.println("no data found for this requred ID"+id);
				 
			  
		   }catch(SQLException e) {
			   e.printStackTrace();
			   return false;
		   }
		  
		   
	   }
	   
}

