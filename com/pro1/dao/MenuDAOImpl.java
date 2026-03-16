package com.pro1.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.pro1.connector.ConnectorFactory;
import com.pro1.dto.Menu;
import com.pro1.dto.Restaurant;

import java .sql.ResultSet;
import java.sql.SQLException;
public class MenuDAOImpl {

	public List<Menu> getMenus() {
		ArrayList<Menu> menulist = new ArrayList<Menu>();;
		try {
			
		Connection con = ConnectorFactory.requestConnection();
		String query = "SELECT * FROM menu";
		Statement stmt = con.createStatement();
		ResultSet res =	stmt.executeQuery(query);
		
		while(res.next()==true)
		{
			int menu_id = res.getInt(1);
			int restaurant_id = res.getInt(2);
			String item_name = res.getString(3);
			String description = res.getString(4);
			double price = res.getDouble(5);
			boolean is_available = res.getBoolean(6);
			boolean Is_Veg = res.getBoolean(7);
			String image_path = res.getString(8);
			
			Menu m1 = new Menu(menu_id,restaurant_id,item_name,description,price,is_available, Is_Veg,image_path);
			
			menulist.add(m1);
		}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return menulist;
	}
	
	public List<Menu> getAllMenusByRestaurantId(int Restaurant_Id)
	{
		ArrayList<Menu> menulist = null;
		try {
			Connection con = ConnectorFactory.requestConnection();

			String query = "SELECT * FROM menu WHERE Restaurant_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Restaurant_Id);
			ResultSet res = pstmt.executeQuery();
		
			menulist = new ArrayList<Menu>();
			
			while(res.next()==true)
			{
				int menuid = res.getInt(1);
				int restaurantid = res.getInt(2);
				String item_name = res.getString(3);
				String description = res.getString(4);
				double price = res.getDouble(5);
				boolean is_available = res.getBoolean(6);
				boolean Is_Veg = res.getBoolean(7);
				String image_path = res.getString(8);
				
				Menu m1 = new Menu(menuid,restaurantid,item_name,description,price,is_available,Is_Veg,image_path);
				
				menulist.add(m1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return menulist;
	}
	
	
	public Menu getMenu(int Menu_Id) {
		Menu m2 = null;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM menu WHERE Menu_Id = ?";
			PreparedStatement pstmt1 = con.prepareStatement(query);
			pstmt1.setInt(1, Menu_Id);
			
			ResultSet res = pstmt1.executeQuery();
			res.next();
			
			m2 = new Menu(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4),res.getDouble(5),res.getBoolean(6),res.getBoolean(6),res.getString(7));
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return m2;
	}
	
	public boolean insertMenu(int Menu_Id, int Restaurant_Id, String Item_Name, String Description, double Price, boolean Is_Available,boolean Is_Veg, String Image_Path) {
		int i = 0;
		
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "INSERT INTO menu (Menu_Id, Restaurant_Id, Item_Name, Description, Price, Is_Available,Is_Veg, Image_Path) VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Menu_Id);
			pstmt.setInt(2, Restaurant_Id);
			pstmt.setString(3, Item_Name);
			pstmt.setString(4, Description);
			pstmt.setDouble(5, Price);
			pstmt.setBoolean(6, Is_Available);
			pstmt.setBoolean(7, Is_Veg);
			pstmt.setString(8, Image_Path);
			
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
	public boolean updateMenu(Menu m)
	{
		int i = 0;
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "UPDATE menu SET `Item_Name` = ? , `Description` = ?, Price = ?, Is_Available = ? WHERE `Restauarant_Id` = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getItem_Name());
			pstmt.setString(2, m.getDescription());
			pstmt.setDouble(3, m.getPrice());
			pstmt.setBoolean(4, m.getIs_Available());
			pstmt.setInt(5, m.getMenu_Id());
			
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
 
	public boolean deleteMenu(int Menu_Id) {
		int i = 0;
		try
		{
			Connection con = ConnectorFactory.requestConnection();
		
			String query = "DELETE From `menu` WHERE `Menu_Id` = ?";
		
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Menu_Id);
			i = pstmt.executeUpdate();
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		return i == 1;	
		}

}
