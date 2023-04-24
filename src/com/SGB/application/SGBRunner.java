package com.SGB.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.SGB.connection.SQLConnection;
import com.SGB.utility.Controls;

public class SGBRunner {
	
	public static void main(String[] args) {
		
		// Delcare Variables
		Scanner scan = new Scanner(System.in);
		Connection conn = SQLConnection.getConnection();
		int choice = 0;
		
		do {
			choice = Controls.consoleStart(scan);
			
			switch (choice){
				case 1:
					Controls.login(scan, conn);
					break;
				case 2:
					Controls.register(scan, conn);
					break;
				
			}
		} while (choice != 3);
		
		System.out.println("Thanks for using SGB! Goodbye");
		scan.close();
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
