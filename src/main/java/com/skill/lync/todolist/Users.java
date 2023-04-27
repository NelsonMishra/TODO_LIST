package com.skill.lync.todolist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
	static String user_name;
	static String upassword;
	static Connection con = MainApp.con;
	static Scanner sc = new Scanner(System.in);
	private static PreparedStatement pstmt;
	private static ResultSet res;

	public Users(String user_name, String upassword) {
		super();
		this.user_name = user_name;
		this.upassword = upassword;

	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getUpassword() {
		return upassword;
	}

	static void userRegister() {
		try {
			System.out.println(con);
			System.out.println("Enter the user name:");
			user_name = sc.next();
			System.out.println("Enter the password: ");
			upassword = sc.next();
			String sql = "insert into users values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, upassword);
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("User Added");
				MainApp.main(null);
			}
			else {
				System.out.println("User action failed. Referesh the page to register again");
				MainApp.main(null);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void userLogin() {
		try {
			System.out.println(con);
			System.out.println("Enter the user name:");
			user_name = sc.next();
			System.out.println("Enter the password: ");
			upassword = sc.next();
			String sql = "select * from users where user_name=? and upassword=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, upassword);
			res = pstmt.executeQuery();
			if(res.next()==true) {
				System.out.println("Login Successfull.\n"
						+ "********Welcome to your To-Do-List Application "+res.getString(1).toUpperCase()+"********");
				To_DO_Tasks.taskList(res.getString(1));
			}
			else {
				System.out.println("User Login failed. Referesh the page to re-login again");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
