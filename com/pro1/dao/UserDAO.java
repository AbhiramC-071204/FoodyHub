package com.pro1.dao;

import java.util.List;

import com.pro1.dto.User;

public interface UserDAO {

	List getUsers();
	User getUser(int User_ID);
	//boolean insertUser(int User_Id,String UserName,String Password,String Email,String Phone,String Address,String Role);
	boolean insertUser(User u);	
	boolean UpdateUser(User u);
	boolean DeleteUser(int UserID);
	User getUserByEmail(String email);
}
