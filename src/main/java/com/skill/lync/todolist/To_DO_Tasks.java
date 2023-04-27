package com.skill.lync.todolist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class To_DO_Tasks {
	static String user_name;
	static PreparedStatement pstmt;
	static Connection con = MainApp.con;
	static Scanner sc = new Scanner(System.in);
	private static ResultSet res;
	
	
	static void taskList(String user) {
		user_name = user;
		System.out.println(" Select an option: \n"
				+ "1. Add task\n"
				+ "2. View tasks\n"
				+ "3. Update task\n"
				+ "4. Remove task\n"
				+ "5. Search task\n"
				+ "6. Logout");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			newTask();
			break;
		}
		case 2: {
			viewTasks();
			break;
		}
		case 3: {
			updateTask();
			break;
		}
		case 4: {
			removeTask();
			break;
		}
		case 5:{
			searchTask();
			break;
		}
		case 6: {
			MainApp.main(null);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}
	private static void searchTask() {
		
		
	}
	static void removeTask() {
		try {
			String sql = "delete todo_list set status=? where user_name=? and id=?";
			pstmt = con.prepareStatement(sql);
			System.out.println("Enter  the task Id = ");
			pstmt.setInt(3, sc.nextInt());
			pstmt.setString(2, user_name);

			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Task deleted ");
				taskList(user_name);
			}else {
				System.out.println("Task not deleted");
				taskList(user_name);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	static void updateTask() {
		try {
			String sql = "update todo_list set status=? where user_name=? anda id=?";
			pstmt = con.prepareStatement(sql);
			System.out.println("Enter  the task Id = ");
			pstmt.setInt(3, sc.nextInt());
			pstmt.setString(2, user_name);
			System.out.println("Enter the task status = ");
			pstmt.setString(1, sc.next());
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Task updated");
				taskList(user_name);
			}else {
				System.out.println("Task not updated");
				taskList(user_name);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	static void viewTasks() {
		
		try {
			String sql = "select * from todo_list where user_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			res = pstmt.executeQuery();
			while(res.next()==true) {
				System.out.println("Task Id\t: "+res.getInt(1));
				System.out.println("Task Name \t: "+res.getString(2));
				System.out.println("Task Description \t: "+res.getString(3));
				System.out.println("Task Status \t: "+res.getString(4));
				System.out.println("--------------------------------------");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void newTask() {
		try {
			System.out.println(con);
			System.out.println("Enter the task name:");
			String task_name = sc.next();
			System.out.println("Enter the description: ");
			String desc = sc.next();
			String sql = "insert into todo_list (task_name,description,user_name) values (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, task_name);
			pstmt.setString(2, desc);
			pstmt.setString(3, user_name);
			int x = pstmt.executeUpdate();
			if(x>0) {
				System.out.println("Task Added successfully.\n");
				taskList(user_name);
			}
			else {
				System.out.println("Task action failed.");
				taskList(user_name);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
		
}
