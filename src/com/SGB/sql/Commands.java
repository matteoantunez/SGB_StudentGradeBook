package com.SGB.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Commands {

	public static void register(String fn, String ln, String un, String pw, Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO teacher (teacher_firstName, teacher_lastName, teacher_username, teacher_password) VALUES (?, ?, ?, ?)");
			stmt.setString(1, fn);
			stmt.setString(2, ln);
			stmt.setString(3, un);
			stmt.setString(4, pw);
			stmt.execute();
			System.out.println("Sucessfully Registered!");
			System.out.println();
		} catch (Exception e) {
			System.out.println("User could not be created, please try again.");
		}
	}
	
	public static ResultSet login (String un, String pw, Connection conn) {
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Teacher where teacher_username = ? AND teacher_password = ?");
			stmt.setString(1, un);
			stmt.setString(2, pw);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("User could not be logged in, please try again.");
		}
		
		return rs;
		
	}
	
	public static ResultSet grabUser(int id, Connection conn) {
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement("Select * from Teacher where teacher_id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("Could not grab user, please check user_id and try again");
		}
		
		return rs;
	}
	
	public static ResultSet grabClass(int id, Connection conn) {
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement("Select teacher_id, class.class_id, class_name from teacher_class, class where teacher_id = ? and teacher_class.class_id = class.class_id");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("Error Grabbing Classes. Please Check you query and try again.");
		}
		
		return rs;
	}
}
