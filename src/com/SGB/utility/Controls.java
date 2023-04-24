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
	
	public static void classSummary(Scanner scan, Connection conn, int choice) {
		System.out.println("""
				+---------------+
				| Class Summary |
				+---------------+
				
				Please Select from the following options:
				
				1. 
				""");
	}
}
