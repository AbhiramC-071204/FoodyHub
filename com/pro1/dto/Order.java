package com.pro1.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//import java.time.LocalDate;

public class Order {

	private int Order_Id;
	private int User_Id;
	private int Restaurant_Id;
	private Timestamp Order_Date;
	private double Total_Amount;
	private String Address;
	private String Payment_Mode;
	private String Status;
	private List<Order_Item> orderItems;
	
	
	public Order() {
		super();
		this.Order_Date = new Timestamp(System.currentTimeMillis());
		this.orderItems = new ArrayList<>();
	}

	public Order(int User_Id, int Restaurant_Id, Timestamp Order_Date, double Total_Amount, String Address,String Payment_Mode,String Status,List<Order_Item>orderItems) {
		super();
		this.User_Id = User_Id;
		this.Restaurant_Id = Restaurant_Id;
		this.Order_Date = Order_Date;
		this.Total_Amount = Total_Amount;
		this.Address = Address;
		this.Payment_Mode = Payment_Mode;
		this.Status = Status;
		this.orderItems = orderItems;
	}
	
	public Order(int Order_Id, int User_Id, int Restaurant_Id, Timestamp Order_Date, double Total_Amount, String Address,String Payment_Mode, String Status) {
		super();
		this.Order_Id = Order_Id;
		this.User_Id = User_Id;
		this.Restaurant_Id = Restaurant_Id;
		this.Order_Date = Order_Date;
		this.Total_Amount = Total_Amount;
		this.Address = Address;
		this.Payment_Mode = Payment_Mode;
		this.Status = Status;
	}

	public int getOrder_Id() {
		return Order_Id;
	}

	public void setOrder_Id(int Order_Id) {
		this.Order_Id = Order_Id;
	}

	public int getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(int UserId) {
		this.User_Id = UserId;
	}

	public int getRestaurant_Id() {
		return Restaurant_Id;
	}

	public void setRestaurant_Id(int Restaurant_Id) {
		this.Restaurant_Id = Restaurant_Id;
	}

	public Timestamp getOrder_Date() {
		return Order_Date;
	}

	public void setOrder_Date(Timestamp Order_Date) {
		this.Order_Date = Order_Date;
	}

	public double getTotal_Amount() {
		return Total_Amount;
	}

	public void setTotal_Amount(double Total_Amount) {
		this.Total_Amount = Total_Amount;
	}
	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getStatus() {
		return Status;
	}
 
	public void setStatus(String Status) {
		this.Status = Status;
	}

	public String getPayment_Mode() {
		return Payment_Mode;
	}

	public void setPayment_Mode(String Payment_Mode) {
		this.Payment_Mode = Payment_Mode;
	}

	@Override
	public String toString() {
		return Order_Id + " " + User_Id + " " + Restaurant_Id + " " + Order_Date + " " + Total_Amount + " " + Status + " " + Payment_Mode;
	}
	
}
