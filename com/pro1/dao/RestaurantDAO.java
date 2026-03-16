package com.pro1.dao;

import java.util.List;

import com.pro1.dto.Restaurant;

public interface RestaurantDAO {

	List<Restaurant> getRestaurants();
	Restaurant getRestaurant(int Restaurant_ID);
	boolean insertRestaurant(int Restaurant_ID, String Name, String Cuisine_Type, String Delivery_Time, String Address,int User_ID, double Rating, boolean Is_Active, String Image_Path);
	boolean updateRestaurant(Restaurant r);
	boolean deleteRestaurant(int Restaurant_ID);
	
}
