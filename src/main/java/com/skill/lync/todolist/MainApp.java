package com.skill.lync.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class MainApp {

	 static Connection con;

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/TODODB", "root", 
							"joker@sqlpassword");
			System.out.println("******* Connected to the Database *******");
			System.out.println("Please Select an Option\n"
					+ "1. Register as new User\n"
					+ "2. Login");
			Scanner sc = new Scanner(System.in);
			int choice1 = sc.nextInt();
			if(choice1 == 1) {
				Users.userRegister();
			}
			else if(choice1 == 2) {
				Users.userLogin();
			}
			else {
				System.out.println("Wrong Option selected Referesh the page");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
