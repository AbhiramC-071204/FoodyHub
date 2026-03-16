package com.pro1.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.pro1.connector.ConnectorFactory;
import com.pro1.dto.User;

public class UserDAOImpl implements UserDAO {

	public List<User> getUsers() {
		ArrayList<User> userlist = new ArrayList<User>();//can store heterogeneous data so generic is there.
		try 
		{
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM user";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			
			while(res.next()==true) {
				//accessing it one by one
				int userid = res.getInt(1);
				String username = res.getString(2);
				String password = res.getString(3);
				String email = res.getString(4);
				String phone = res.getString(5);
				String address = res.getString(6);
				String role = res.getString(7);
				
				//object created
				User u1 = new User(userid, username,password,email,phone,address,role);
				//store it in a list
				userlist.add(u1);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return userlist;
	}
	
	public User getUser(int User_Id) 
	{
		User u2 = null;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM user WHERE User_ID = ?";
			PreparedStatement pstmt1 = con.prepareStatement(query);
			pstmt1.setInt(1, User_Id);
			
			ResultSet res = pstmt1.executeQuery();
			
			if(res.next()) {
			    u2 = new User(res.getInt(1),res.getString(2),res.getString(3),
			            res.getString(4),res.getString(5),res.getString(6),res.getString(7));
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return u2;
		
	}
	
	public User getUserByEmail(String email)
	{
		User user = null;
		try {
			
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM user WHERE Email = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			
			ResultSet res = pstmt.executeQuery();
			
			if(res.next())
			{
				user = new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7));
			}
			res.close();
	        pstmt.close();
	        con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean insertUser(User u) 
	{
		int i = 0;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "INSERT INTO user (User_ID, User_Name,Password,Email,Phone,Address,Role) VALUES (?,?,?,?,?,?,?);";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, u.getUser_ID());
			pstmt.setString(2, u.getUser_Name());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getPhone());
			pstmt.setString(6, u.getAddress());
			pstmt.setString(7, u.getRole());
			
			i = pstmt.executeUpdate();
			
		} 
		
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(i == 1)
		{
			return true;
		}
		return false;
	}
	
	public boolean UpdateUser(User u) 
	{
		int i = 0;
		try {
			Connection con = ConnectorFactory.requestConnection();
			
			String query = "UPDATE `user` SET `Password` = ?, `Email` = ?, `Address` = ?, `Role` = ? WHERE `User_Id` = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,u.getPassword());
			pstmt.setString(2,u.getEmail());
			pstmt.setString(3,u.getAddress());
			pstmt.setString(4,u.getRole());
			pstmt.setInt(5,u.getUser_ID());
			
			i = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(i == 1)
		{
			System.out.print("Updated");
			return true;
		}
		return false;
		
	}
	
	public boolean DeleteUser(int UserID)
	{
		int i = 0;
		
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "DELETE FROM user WHERE `User_ID` = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, UserID);
			
			i = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(i == 1)
		{
			return true;
		}
		return false;
	}
}
