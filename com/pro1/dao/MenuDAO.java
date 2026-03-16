package com.pro1.dao;

import java.util.List;

import com.pro1.dto.Menu;

public interface MenuDAO {

	List getMenus();
	List getAllMenusByRestaurantId(int Restaurant_Id);
	Menu getMenu(int Menu_Id);
	boolean insertMenu(int Menu_Id, int Restaurant_Id, String Item_Name, String Description, double Price, boolean Is_Available, boolean Is_Veg,String Image_Path);
	boolean updateMenu(Menu m);
	boolean deleteMenu(int Menu_Id);
}
