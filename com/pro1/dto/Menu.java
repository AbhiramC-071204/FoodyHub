package com.pro1.dto;

public class Menu {
	
	private int Menu_Id;
	private int Restaurant_Id;
	private String Item_Name;
	private String Description;
	private double Price;
	private boolean Is_Available;
	private boolean Is_Veg;
	private String Image_Path;
	
	
	public Menu() {
		super();
	}
	public Menu(int Menu_Id, int Restaurant_Id, String Item_Name,
            String Description, double Price,
            boolean Is_Available, boolean Is_Veg, String Image_Path) {

    this.Menu_Id = Menu_Id;
    this.Restaurant_Id = Restaurant_Id;
    this.Item_Name = Item_Name;
    this.Description = Description;
    this.Price = Price;
    this.Is_Available = Is_Available;
    this.Is_Veg = Is_Veg;
    this.Image_Path = Image_Path;
}
	public int getMenu_Id() {
		return Menu_Id;
	}
	public void setMenu_Id(int Menu_Id) {
		this.Menu_Id = Menu_Id;
	}
	public int getRestaurant_Id() {
		return Restaurant_Id;
	}
	public void setRestaurantId(int Restaurant_Id) {
		this.Restaurant_Id = Restaurant_Id;
	}
	public String getItem_Name() {
		return Item_Name;
	}
	public void setItem_Name(String Item_Name) {
		this.Item_Name = Item_Name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		this.Description = Description;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double Price) {
		this.Price = Price;
	}
	public boolean getIs_Available() {
		return Is_Available;
	}
	public void setIs_Available(boolean Is_Available) {
		this.Is_Available = Is_Available;
	}
	
	public boolean getIs_Veg() {
		return Is_Veg;
	}

	public void setIs_Veg(boolean Is_Veg) {
		this.Is_Veg = Is_Veg;
	}

	public String getImage_Path() {
		return Image_Path;
	}
	public void setImage_Path(String Image_Path) {
		this.Image_Path = Image_Path;
	}

	@Override
	public String toString() {
		return Menu_Id + " " + Restaurant_Id + " " + Item_Name + " " + Description + " " + Price + " " + Is_Available + " " + Image_Path;
	}
	
	
}
