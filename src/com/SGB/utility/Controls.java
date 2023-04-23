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
}
