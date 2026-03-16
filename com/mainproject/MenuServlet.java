package com.mainproject;

import java.io.IOException;
import java.util.List;

import com.pro1.dao.MenuDAOImpl;
import com.pro1.dao.RestaurantDAO;
import com.pro1.dao.RestaurantDAOImpl;
import com.pro1.dto.Menu;
import com.pro1.dto.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		
		RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
		int restaurantID = Integer.parseInt(req.getParameter("restaurantId"));
		
		// Fetch restaurant
		Restaurant restaurant = restaurantDAO.getRestaurant(restaurantID);
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		List<Menu> allMenusByRestaurantId = menuDAOImpl.getAllMenusByRestaurantId(restaurantID);
		
		// Set attributes
		req.setAttribute("restaurant", restaurant);
		
		for(Menu menu : allMenusByRestaurantId)
		{
			System.out.println(menu);
		}
		
		//send to front end
		req.setAttribute("allMenusByRestaurantId",allMenusByRestaurantId);
		
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
		
	}
	
}
