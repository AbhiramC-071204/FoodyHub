package com.pro1.dto;

public class CartItem {
	
	private int itemId;
	private String name;
	private int quantity;
	private double price;
	private String imagePath; 
	
	public CartItem() {

	}

	public CartItem(int itemId, String name, int quantity, double price,String imagePath) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.imagePath = imagePath;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	 public String getImagePath() {
	        return imagePath;
	  }

	 public void setImagePath(String imagePath) {
	      this.imagePath = imagePath;
	  }
	
	@Override
	public String toString() {
		return itemId + " " + name + " " + quantity + " " + price;
	}

}
