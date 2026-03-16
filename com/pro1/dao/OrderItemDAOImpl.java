package com.pro1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pro1.connector.ConnectorFactory;
import com.pro1.dto.Order_Item;

public class OrderItemDAOImpl {

	List<Order_Item> getOrderItems()
	{
		ArrayList<Order_Item> orderitemlist = new ArrayList<Order_Item>();
		try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM orderitem";
			Statement stmt = con.createStatement();
			ResultSet res= stmt.executeQuery(query);
		
			
			while(res.next())
			{
				int order_Item_ID = res.getInt(1);
				int order_ID = res.getInt(2);
				int menu_ID = res.getInt(3);
				int quantity = res.getInt(4);
				double total_Amount = res.getDouble(5);
				
				Order_Item oi1 = new Order_Item(order_Item_ID,order_ID,menu_ID,quantity,total_Amount);
				
				orderitemlist.add(oi1);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return orderitemlist;
	}
	  public Order_Item getOrderItem(int Order_Item_ID)
	  {
		  Order_Item oi2 = null;
		 try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM orderitem WHERE Order_Item_ID = ?";
			PreparedStatement pstmt1 = con.prepareStatement(query);
			pstmt1.setInt(1, Order_Item_ID);
			
			ResultSet res = pstmt1.executeQuery();
			
			res.next();
			
			oi2 = new Order_Item(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),res.getDouble(5));
			
		 } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		 }
		 
		 return oi2;
		 
	  }
	  public boolean insertOrderItem(Order_Item oi)
	  {
		  int i = 0;
		  
		  try {
		  Connection con = ConnectorFactory.requestConnection();
		  String query = "INSERT INTO orderitem (Order_Item_ID, Order_ID, Menu_ID, Total_Amount) VALUES (?,?,?,?);";
		  PreparedStatement pstmt = con.prepareStatement(query);
		  
		  pstmt.setInt(1, oi.getOrder_Item_ID());
		  pstmt.setInt(2, oi.getOrder_ID());
		  pstmt.setInt(3, oi.getMenu_ID());
		  pstmt.setDouble(4, oi.getTotal_Amount());
		  
		  i = pstmt.executeUpdate();
		  
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  
		  if(i == 1)
		  {
			  return true;
		  }
		  return false;
	  }
	  
	  public boolean updateOrderItem(Order_Item oi) {
		  int i = 0;
			try {
				Connection con = ConnectorFactory.requestConnection();
		        String query = "UPDATE orderitem SET Order_ID = ?, Menu_ID = ?, Quantity = ?, Total_Amount = ? WHERE Order_Item_ID = ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				
				pstmt.setInt(1, oi.getOrder_ID());
		        pstmt.setInt(2, oi.getMenu_ID());
		        pstmt.setInt(3, oi.getQuantity());
		        pstmt.setDouble(4, oi.getTotal_Amount());
		        pstmt.setInt(5, oi.getOrder_Item_ID());
		        
				i = pstmt.executeUpdate();
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return i == 1;
	  }
	  
	  public boolean deleteOrderItem(int Order_Item_ID) {
		  int i = 0;
			try
			{
				Connection con = ConnectorFactory.requestConnection();
			
				String query = "DELETE From `orderitem` WHERE `Order_Item_ID` = ?";
			
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Order_Item_ID);
				i = pstmt.executeUpdate();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
			return (i == 1);
				
	  }
}
