package com.pro1.dto;

public class Order_Item {
	
	private int Order_Item_ID;
	private int Order_ID;
	private int Menu_ID;
	private int Quantity;
	private double Total_Amount;
	
	public Order_Item() {
		super();
	}

	public Order_Item(int Order_Item_ID, int Order_ID, int Menu_ID,int Quantity, double Total_Amount) {
		super();
		this.Order_Item_ID = Order_Item_ID;
		this.Order_ID = Order_ID;
		this.Menu_ID = Menu_ID;
		this.Quantity = Quantity;
		this.Total_Amount = Total_Amount;
	}

	public int getOrder_Item_ID() {
		return Order_Item_ID;
	}

	public void setOrder_Item_ID(int Order_Item_ID) {
		this.Order_Item_ID = Order_Item_ID;
	}

	public int getOrder_ID() {
		return Order_ID;
	}

	public void setOrder_ID(int Order_ID) {
		this.Order_ID = Order_ID;
	}

	public int getMenu_ID() {
		return Menu_ID;
	}

	public void setMenu_ID(int Menu_ID) {
		this.Menu_ID = Menu_ID;
	}
	
	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public double getTotal_Amount() {
		return Total_Amount;
	}

	public void setTotal_Amount(double Total_Amount) {
		this.Total_Amount = Total_Amount;
	}
	
	@Override
	public String toString() {
		return Order_Item_ID + " " + Order_ID + " " + Menu_ID + " " + Quantity + " " +Total_Amount;
	}
	
}
