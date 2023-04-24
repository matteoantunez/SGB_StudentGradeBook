package com.SGB.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			PreparedStatement stmt = conn.prepareStatement("Select class.class_id, class_name from teacher_class, class where teacher_id = ? and teacher_class.class_id = class.class_id");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("Error Grabbing Classes. Please Check you query and try again.");
		}
		
		return rs;
	}
	
	public static ResultSet grabRoster(int id, Connection conn) {
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement("select student_id, student_firstName, student_lastName, grades from student, student_class where student.student_id = student_class.student_id AND class_id = ? order by student_lastName asc");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("Error Grabbing Roster, please try again");
		}
		
		return rs;
	}
	
	public static ResultSet grabStats(int id, Connection conn) {
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT avg(grades) from student_class where class_id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			System.out.println("Error collecting stats, please check query and try again");
		}
		
		return rs;
	}
	
	public static void updateGrade(int student, float grade, int cclass, Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement("Update student_class  SET grades = ? where student_id = ? and class_id = ?");
			stmt.setFloat(1, grade);
			stmt.setInt(2, student);
			stmt.setInt(3, cclass);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Error changing grade, please try again.");
		}
	}
	
	public static void addStudent(String fn, String ln, int cclas, float grade, Connection conn) {
		int studentID = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (student_firstName, student_lastName) values(?, ?)");
			stmt.setString(1, fn);
			stmt.setString(2, ln);
			stmt.execute();
			
			stmt = conn.prepareStatement("Select student_id from student where student_firstName = ? and student_lastName = ?");
			stmt.setString(1, fn);
			stmt.setString(2, ln);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				studentID = rs.getInt(1);
			}
			
			stmt = conn.prepareStatement("INSERT INTO student_class values (?, ?, ?)");
			stmt.setInt(1, studentID);
			stmt.setInt(2, cclas);
			stmt.setFloat(3, grade);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Error adding student, check query and try again");
		}
	}
	
	public static void removeStudent(int id, Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM student where student_id = ?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (Exception e) {
			System.out.println("Erorr removing student, checkk query and try again.");
		}
	}
	
	public static void createClass(String className, int id, Connection conn) {
		int cid = 0;
		
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO class (class_name) values (?)");
			stmt.setString(1, className);
			stmt.execute();
			
			stmt = conn.prepareStatement("Select class_id from class where class_name = ?");
			stmt.setString(1, className);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				cid = rs.getInt(1);
			}
			
			stmt = conn.prepareStatement("INSERT INTO teacher_class values(?, ?)");
			stmt.setInt(1, id);
			stmt.setInt(2, cid);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error creating class, please try again");
		}
		
	}
}
