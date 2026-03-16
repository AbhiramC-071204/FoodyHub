package com.pro1.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import com.pro1.connector.ConnectorFactory;
import com.pro1.dto.Order;
public class OrderDAOImpl {

	public List<Order> getOrders() {
	 ArrayList<Order> orderlist =  new ArrayList<Order>();;
		 try {
			
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM `order`";
			Statement stmt = con.createStatement();
			ResultSet res= stmt.executeQuery(query);
			
			while(res.next()==true)
			{
				int orderid = res.getInt(1);
				int userid = res.getInt(2);
				int restaurantid = res.getInt(3);
				Timestamp Order_date = res.getTimestamp(4);
				double total_amount = res.getDouble(5);
				String address = res.getString(6);
				String payment_mode = res.getString(7);
				String status = res.getString(8);
				
				Order o1 = new Order(orderid,userid,restaurantid,Order_date,total_amount,address,status,payment_mode);
				
				orderlist.add(o1);
			}
			
		 } catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		 }
		 return orderlist;
	}
	 public Order getOrder(int Order_Id) {
		 
		 Order o2 = null;
		 try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "SELECT * FROM `order` WHERE Order_Id = ?";
			PreparedStatement pstmt1 = con.prepareStatement(query);
			pstmt1.setInt(1, Order_Id);
			ResultSet res = pstmt1.executeQuery();
			
			if(res.next()) {			
			o2 = new Order(res.getInt(1),res.getInt(2),res.getInt(3),res.getTimestamp(4),res.getDouble(5),res.getString(6),res.getString(7),res.getString(8));
			} 
		 } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		 }
		 return o2;
	 }
	 
	 public int insertOrder(Order order) {
		 int i = 0,orderid = 0;
		 
		 try {
			Connection con = ConnectorFactory.requestConnection();
			String query = "INSERT INTO `order` (Order_Id,User_Id, Restaurant_Id, Order_Date, Total_Amount,Address,Payment_Mode, Status) VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, order.getOrder_Id());
			pstmt.setInt(2, order.getUser_Id());
			pstmt.setInt(3, order.getRestaurant_Id());
			pstmt.setTimestamp(4, order.getOrder_Date());
			pstmt.setDouble(5,order.getTotal_Amount());
			pstmt.setString(6, order.getAddress());
			pstmt.setString(7, order.getPayment_Mode());
			pstmt.setString(8, order.getStatus());
			
			i = pstmt.executeUpdate();
			
			if(i == 0)
			{
				throw new SQLException("creating order failed,no rows affected.");
			}
			
			try(ResultSet generatedKeys = pstmt.getGeneratedKeys())
			{
				//ResultSet Traversing
				if(generatedKeys.next())
				{
					orderid = generatedKeys.getInt(1);
				}
				else
				{
					throw new SQLException("Creating order failed, no ID obtained");
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		 
		return orderid;
	 }
	 
	 boolean updateOrder(Order o) {
		 int i = 0;
			try {
				Connection con = ConnectorFactory.requestConnection();
				 String query = "UPDATE `order` SET User_Id=?, Restaurant_Id=?, Order_Date=?, Total_Amount=?, Address=?, Payment_Mode=?, Status=? WHERE Order_Id=?";
				PreparedStatement pstmt = con.prepareStatement(query);
				
				pstmt.setInt(1, o.getUser_Id());
		        pstmt.setInt(2, o.getRestaurant_Id());
		        pstmt.setTimestamp(3, o.getOrder_Date());
		        pstmt.setDouble(4, o.getTotal_Amount());
		        pstmt.setString(5, o.getAddress());
		        pstmt.setString(6, o.getPayment_Mode());
		        pstmt.setString(7, o.getStatus());
		        pstmt.setInt(8, o.getOrder_Id());
				
				i = pstmt.executeUpdate();
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return i == 1;
	 }
	 
	 boolean deleteOrder(int Order_Id) {
		 int i = 0;
			try
			{
				Connection con = ConnectorFactory.requestConnection();
			
				String query = "DELETE From `order` WHERE `Order_Id` = ?";
			
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Order_Id);
				i = pstmt.executeUpdate();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
			return i == 1;
				
	 }
}
