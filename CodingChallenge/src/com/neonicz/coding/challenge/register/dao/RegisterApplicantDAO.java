package com.neonicz.coding.challenge.register.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterApplicantDAO {

	public Boolean saveApplicant(String name, String education, String place,
			String collegeName, String emaiId, Long contactNumber, String description) {
		Connection con = null;
		boolean successfull = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coding_challenge";
			con = DriverManager.getConnection(url, "root", "neonicz");
			String insertApplicant = "INSERT INTO applicants(name,education,place,collegeName,emaiId,contactNumber,description)"
					+ "   VALUES ( ?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(insertApplicant);
			stmt.setString(1, name);
			stmt.setString(2, education);
			stmt.setString(3, place);
			stmt.setString(4, collegeName);
			stmt.setString(5, emaiId);
			stmt.setLong(6, contactNumber);
			stmt.setString(7, description);
			Boolean inserted = stmt.execute();
			System.out.println(inserted);
			if (stmt.getUpdateCount() > 0) {
				System.out.println("Data insert successful");
				successfull = true;
			} else {
				System.out.println("Data insert failed");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return successfull;
	}
	
	public Boolean isApplicantRegistered(String email, Long contactNumber) {
		Connection con = null;
		String selectApplicantByMobileOrEmail = "SELECT * FROM applicants WHERE emaiId=? OR contactNumber=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coding_challenge";
			con = DriverManager.getConnection(url, "root", "neonicz");
			PreparedStatement stmt = con.prepareStatement(selectApplicantByMobileOrEmail);
			stmt.setString(1, email);
			stmt.setLong(2, contactNumber);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return true;
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<Map<String,String>> getAllApplicants(){
		Connection con = null;
		String selectApplicantByMobileOrEmail = "SELECT * FROM applicants";
		List<Map<String,String>> applicants = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/coding_challenge";
			con = DriverManager.getConnection(url, "root", "neonicz");
			PreparedStatement stmt = con.prepareStatement(selectApplicantByMobileOrEmail);
			
			ResultSet rs = stmt.executeQuery();
			applicants = new ArrayList<Map<String,String>>();
			System.out.println("Applicants Length Before :> "+ applicants.size());
			while (rs.next()) {
				System.out.println(rs.getString("id") + " , " + rs.getString("name"));
				
				Map<String,String> row = new HashMap<String, String>() ;
				row.put("id", rs.getString("id"));
				row.put("name", rs.getString("name"));
				row.put("education", rs.getString("education"));
				row.put("place", rs.getString("place"));
				row.put("collegeName", rs.getString("collegeName"));
				row.put("emaiId", rs.getString("emaiId"));
				row.put("contactNumber", rs.getString("contactNumber"));
				row.put("description", rs.getString("description"));
				applicants.add(row);
			}
			System.out.println("Applicants Length After :> "+ applicants.size());
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return applicants;
	}
	
}
