package com.SGB.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.SGB.sql.Commands;

public class Controls {

	
	public static int consoleStart(Scanner scan) {
		System.out.print("""
				+-------------------------------------+
				| Welcome to SGB - School Grade Book! |
				+-------------------------------------+
				
				Please select from the following options:
				
				1. Login
				2. Register
				3. Exit
				
				Choice> """);
		
		int choice = Integer.parseInt(scan.nextLine());
		System.out.println();
		return choice;
	}
	
	public static void login(Scanner scan, Connection conn) {
		String un,  pw;
		int id = 0;
		
		System.out.print("""
				+------------+
				| Login Form |
				+------------+
				
				Username: """);
		un = scan.nextLine();
		System.out.println();
		
		System.out.print("Password: ");
		pw = scan.nextLine();
		System.out.println();
		
		ResultSet rs = Commands.login(un, pw, conn);
		
		try {
			while (rs.next()) {
				id = rs.getInt(1);
				Controls.menu(scan, conn, id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in login form. Please check back late.");
			e.printStackTrace();
		}
		
		
	}
	
	public static void register(Scanner scan, Connection conn) {
		String fn, ln, un, pw;
		
		System.out.print("""
				+-------------------+
				| Registration Form |
				+-------------------+
				
				First Name: """);
		fn = scan.nextLine();
		System.out.println();
		
		System.out.print("Last Name: ");
		ln = scan.nextLine();
		System.out.println();
		
		System.out.print("Username: ");
		un = scan.nextLine();
		System.out.println();
		
		System.out.print("Password: ");
		pw = scan.nextLine();
		System.out.println();
		
		Commands.register(fn, ln, un, pw, conn);
		
	}
	
	public static void menu(Scanner scan, Connection conn, int id) {
		int choice = 0;
		int length;
		ResultSet rs =  null;
		String fn = null, ln;
		
		rs = Commands.grabUser(id, conn);
		
		try {
			while (rs.next()) {
				fn = rs.getString(2);
				ln = rs.getString(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		length = fn.length();
		
		do {
			System.out.print("+---------");
			for (int x = 0; x <= length; x++) {
				System.out.print('-');
			}
			System.out.println('+');
			System.out.print("| Welcome ");
			System.out.println(fn + " |");
			System.out.print("+---------");
			for (int x = 0; x <= length; x++) {
				System.out.print('-');
			}
			System.out.println('+');
			System.out.println();
			
			System.out.println("Please Select from the following options: \n");
			System.out.println("""
					1. View Classes
					2. Create Class
					3. Exit""");
			System.out.println();
			System.out.print("Choice> ");
			choice = Integer.parseInt(scan.nextLine());
			
			switch(choice) {
			case 1:
				Controls.classList(scan, conn, id);
				break;
			case 2:
				Controls.createClass(scan, conn, id);
			}
		
			
		} while (choice != 3);
	}
	
	public static void classList (Scanner scan, Connection conn, int id) {
		System.out.println("""
				+------------+
				| Class List |
				+------------+
				""");
		ResultSet rs = Commands.grabClass(id, conn);
		int choice = 0;
		
		try {
			while (rs.next()) {
				System.out.println("Class ID: " + rs.getInt(1));
				System.out.println("Class Name: " + rs.getString(2));
				System.out.println();
			}
			
			System.out.println("Please Select a class to View: \n");
			System.out.print("Choice> ");
			choice = Integer.parseInt(scan.nextLine());
			System.out.println();
			
			Controls.classSummary(scan, conn, choice);
		} catch (SQLException e) {
			System.out.println("No Classes Found. Please create a class and try again");
		}
		
		
	}
	
	public static void classSummary(Scanner scan, Connection conn, int classChoice) {
		int choice = 0;
		do {
			System.out.println("""
					+---------------+
					| Class Summary |
					+---------------+
					
					Please Select from the following options:
					
					1. List Students and Grades
					2. Class Stats
					3. Update a Grade
					4. Remove/Add a student
					5. Exit
					""");
			System.out.print("Choice> ");
			choice = Integer.parseInt(scan.nextLine());
			System.out.println();
			
			switch (choice) {
			case 1:
				Controls.viewRoster(Commands.grabRoster(classChoice, conn));
				break;
			case 2:
				Controls.stats(Commands.grabStats(classChoice, conn));
				break;
			case 3:
				Controls.updateGrade(scan, classChoice, conn);
				break;
			case 4:
				Controls.addRemoveStudent(scan, classChoice, conn);
				break;
			}
		} while (choice != 5);
	}
	
	public static void viewRoster(ResultSet rs) {
		String fn = "", ln = "";
		float grade;
		int id = 0;
		
		try {
			while (rs.next()) {
				id = rs.getInt(1);
				fn = rs.getString(2);
				ln = rs.getString(3);
				grade = rs.getFloat(4);
				
				System.out.println("Student ID: " + id);
				System.out.println("First Name: " + fn);
				System.out.println("Last Name: " + ln);
				System.out.println("Grade: " + grade);
				System.out.println();
				
			}
		} catch (SQLException e) {
			System.out.println("Please Check Roster and try again.");
			e.printStackTrace();
		}
	}
	
	public static void stats (ResultSet rs) {
		System.out.print("""
				+-------------+
				| Class Stats |
				+-------------+
				
				Average Grade: """);
		
		try {
			while (rs.next()){
				System.out.println(rs.getFloat(1));
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error grabbing stats");
			e.printStackTrace();
		}
	}
	
	public static void updateGrade(Scanner scan, int classChoice, Connection conn) {
		System.out.print("""
				+--------------+
				| Update Grade |
				+--------------+
				
				Which student would you like to update? 
				Please chose there id: """);
		int student = Integer.parseInt(scan.nextLine());
		System.out.println();
		System.out.print("Updated Grade: ");
		float grade = Float.parseFloat(scan.nextLine());
		
		Commands.updateGrade(student, grade, classChoice, conn);
		
		
	}
	
	public static void addRemoveStudent(Scanner scan, int classChoice, Connection conn) {
		int choice = 0;
		
		do {
		System.out.println("""
				+--------------------+
				| Add/Remove Student |
				+--------------------+
				
				Would you like to:
				
				1. Add Student
				2. Remove Student
				3. Exit""");
		System.out.println();
		System.out.print("Choice> ");
		choice = Integer.parseInt(scan.nextLine());
		
		switch (choice) {
		case 1:
			Controls.addStudent(scan, classChoice, conn);
			break;
		case 2:
			break;
		}
		} while (choice != 3);
	}
	
	public static void addStudent(Scanner scan, int classChoice, Connection conn) {
		String fn, ln;
		float grade;
		
		System.out.print("""
				+-------------+
				| Add Student |
				+-------------+
				
				Student First Name: """);
		fn = scan.nextLine();
		System.out.println();
		
		System.out.print("Student Last Name: ");
		ln = scan.nextLine();
		System.out.println();
		
		System.out.println("Grade: ");
		grade = Float.parseFloat(scan.nextLine());
		System.out.println();
		
		Commands.addStudent(fn, ln, classChoice, grade, conn);

	}
	
	public static void removeStudent(Scanner scan, Connection conn) {
		int id;
		
		System.out.print("""
				+----------------+
				| Remove Student |
				+----------------+
				
				What Student ID would you like to remove?
				
				Choice> """);
		id = Integer.parseInt(scan.nextLine());
		System.out.println();
		
		Commands.removeStudent(id, conn);
	}
	
	public static void createClass(Scanner scan, Connection conn, int teacherid) {
		String newClass;
		
		System.out.print("""
				+--------------+
				| Create Class |
				+--------------+
				
				What is the name of your class? 
				
				Choice> """);
		
		newClass = scan.nextLine();
		System.out.println();
		
		Commands.createClass(newClass, teacherid, conn);
		
	}
	
//	public static void 
}
