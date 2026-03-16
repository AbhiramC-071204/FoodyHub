 package com.pro1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pro1.connector.ConnectorFactory;
import com.pro1.dto.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO{

	public List<Restaurant> getRestaurants()
	{
		List<Restaurant> restaurantlist = new ArrayList<Restaurant>();
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM restaurants";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);
			
			
			while(res.next())
			{
				int restaurantid = res.getInt("Restaurant_Id");
				String name = res.getString("Name");
				String cuisine_Type = res.getString("Cuisine_Type");
				String delivery_Time = res.getString("Delivery_Time");
				String address = res.getString("Address");
				int User_ID = res.getInt("User_ID");
				double rating = res.getDouble("Rating");
				boolean is_Active = res.getBoolean("Is_Active");
				String image_Path = res.getString("Image_Path");
				
				Restaurant r1 = new Restaurant(restaurantid,name,cuisine_Type,delivery_Time,address,User_ID,rating,is_Active,image_Path); 
			
				restaurantlist.add(r1);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return restaurantlist;
	}
	
	public Restaurant getRestaurant(int Restaurant_Id) {
		Restaurant r2 = null;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM restaurants WHERE Restaurant_ID = ?";
			PreparedStatement pstmt1 = con.prepareStatement(query);
			
			pstmt1.setInt(1, Restaurant_Id);
			
			ResultSet res= pstmt1.executeQuery();
			
			res.next();
			
			r2 = new Restaurant(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getInt(6),res.getDouble(7), res.getBoolean(8),res.getString(9));
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return r2;
	}
	public boolean insertRestaurant(int Restaurant_ID, String Name, String Cuisine_Type, String Delivery_Time, String Address,int User_ID, double Rating, boolean Is_Active, String Image_Path) {
		int i = 0;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "INSERT INTO restaurants (Restaurant_ID, Name, Cuisine_Type, Delivery_Time, Address, User_ID, Rating, Is_Active, Image_Path) VALUES (?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Restaurant_ID);
			pstmt.setString(2,Name);
			pstmt.setString(3,Cuisine_Type);
			pstmt.setString(4,Delivery_Time);
			pstmt.setString(5,Address);
			pstmt.setInt(6,User_ID);
			pstmt.setDouble(7,Rating);
			pstmt.setBoolean(8,Is_Active);
			pstmt.setString(9,Image_Path);
		
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
	public boolean updateRestaurant(Restaurant r) {
		int i = 0;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "UPDATE restaurants SET `Name` = ? , `Cuisine_Type` = ?, `Address` = ? WHERE `Restauarant_ID` = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, r.getName());
			pstmt.setString(2, r.getCuisine_Type());
			pstmt.setString(3, r.getAddress());
			pstmt.setInt(4, r.getRestaurant_Id());
			
			i = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(i == 1)
		{
			System.out.println("Updated");
			return true;
		}
		return false;
	}
	public boolean deleteRestaurant(int Restaurant_ID) {

		int i = 0;
		try
		{
			Connection con = ConnectorFactory.requestConnection();
		
			String query = "DELETE From restaurants WHERE `Restaurant_ID` = ?";
		
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,Restaurant_ID);
			
			i = pstmt.executeUpdate();
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		if(i == 1)
		{
			return true;
		}
		
		return false;
	}
}
