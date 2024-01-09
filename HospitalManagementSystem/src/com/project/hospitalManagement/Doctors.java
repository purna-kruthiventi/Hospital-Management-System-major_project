package com.project.hospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctors {
	private Connection connection;
	  
	   public Doctors(Connection connection) {
		super();
		this.connection=connection;
	}
	  
	   public void viewDoctors() {
		  try {
			 
			  PreparedStatement pstmt = connection.prepareStatement("select * from Doctors");
			  ResultSet rs = pstmt.executeQuery();
			  System.out.println("retreiving patients....");
			  System.out.println("+----------------+---------------+--------------+");
			  while(rs.next()) {
				  String name= rs.getString("name");
	
				  String specialization = rs.getString("specialization");
				  System.out.println("name: "+name+"specialization: "+specialization);
			  }
		  }catch(SQLException e) {
			  
		  }
	   }
	   
	   public boolean getDoctorById(int id) {
		   try {
			  
			   String query = "select * from Doctor where id=?";
			   PreparedStatement pstmt = connection.prepareStatement(query);
			   pstmt.setInt(1, id);
			  ResultSet rst= pstmt.executeQuery();
			   return rst.next();
				 
				 
				  
		   }catch(SQLException e) {
			   e.printStackTrace();
			   return false;
		   }
	   }
	   
}



