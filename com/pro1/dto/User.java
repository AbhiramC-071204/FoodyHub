package com.pro1.dto;

public class User {
	
	private int User_ID;
	private String User_Name;
	private String Password;
	private String Email;
	private String Phone;
	private String Address;
	private String Role;
	
	public User() {
		
	}
	public User(int User_ID,String User_Name,String Password,String Email,String Phone,String Address,String Role) {
		
		this.User_ID = User_ID;
		this.User_Name = User_Name;
		this.Password = Password;
		this.Email = Email;
		this.Phone = Phone;
		this.Address = Address;
		this.Role = Role;
	}
	public int getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(int User_ID) {
		this.User_ID = User_ID;
		}
	public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String User_Name) {
		this.User_Name = User_Name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String Phone) {
		this.Phone = Phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String Role) {
		this.Role = Role;
	}
	
	@Override
	public String toString() 
	{
		return User_ID + " " + User_Name + " " + Password + " " + Email + " " + Phone + " " + Address + " " + Role;
	}
	
}
