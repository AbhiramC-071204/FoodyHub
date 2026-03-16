package com.pro1.dto;

public class Restaurant {
	
	private int Restaurant_Id;
	private String Name;
	private String Cuisine_Type;
	private String Delivery_Time;
	private String Address;
	private int User_ID;
	private double Rating;
	private boolean Is_Active;
	private String Image_Path;
	

	
	public Restaurant() {
		
	}

	public Restaurant(int Restaurant_Id, String Name, String Cuisine_Type, String Delivery_Time, String Address,int User_ID, double Rating, boolean Is_Active, String Image_Path) {
		super();
		this.Restaurant_Id = Restaurant_Id;
		this.Name = Name;
		this.Cuisine_Type = Cuisine_Type;
		this.Delivery_Time = Delivery_Time;
		this.Address = Address;
		this.User_ID = User_ID;
		this.Rating = Rating;
		this.Is_Active = Is_Active;
		this.Image_Path = Image_Path;
	}

	public int getRestaurant_Id() {
		return Restaurant_Id;
	}

	public void setRestaurantId(int Restaurant_Id) {
		this.Restaurant_Id = Restaurant_Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getCuisine_Type() {
		return Cuisine_Type;
	}

	public void setCuisine_Type(String Cuisine_Type) {
		this.Cuisine_Type = Cuisine_Type;
	}

	public String getDelivery_Time() {
		return Delivery_Time;
	}

	public void setDelivery_Time(String Delivery_Time) {
		this.Delivery_Time = Delivery_Time;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public int getAdmin_User_ID() {
		return User_ID;
	}

	public void setAdmin_User_ID(int User_ID) {
		this.User_ID = User_ID;
	}

	public double getRating() {
		return Rating;
	}

	public void setRating(double Rating) {
		this.Rating = Rating;
	}

	public boolean isIs_Active() {
		return Is_Active;
	}

	public void setIs_Active(boolean Is_Active) {
		this.Is_Active = Is_Active;
	}

	public String getImage_Path() {
		return Image_Path;
	}

	public void setImage_Path(String Image_Path) {
		this.Image_Path = Image_Path;
	}
	
	@Override
	public String toString() {
		return Restaurant_Id + " " + Name + " " + Cuisine_Type+ " " + Delivery_Time + " " + Address + " " + User_ID + " " + Image_Path;
	}
	
}
