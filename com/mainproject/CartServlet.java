package com.mainproject;

import java.io.IOException;

import com.pro1.dao.MenuDAOImpl;
import com.pro1.dto.Cart;
import com.pro1.dto.CartItem;
import com.pro1.dto.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Cart cart = (Cart)session.getAttribute("cart");  //OBJECT TYPE REFERNCE BUT I WANT CART
		
		Integer oldRestaurantId = (Integer)session.getAttribute("oldRestaurantId"); // convert to integer
		
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));//string value i twill return
		
		if(cart == null || oldRestaurantId != restaurantId)
		{
			cart = new Cart();
			
			session.setAttribute("cart", cart);//1st - 22 ,2nd - 29
			session.setAttribute("oldRestaurantId", restaurantId);
			
		}
		
		String action = req.getParameter("action");
		
		if("add".equals(action))
		{
			addItemToCart(req,cart);
		}
		else if("update".equals(action))
		{
			updateItemToCart(req,cart);
		}
		else if("delete".equals(action))
		{
			deleteItemtoCart(req,cart);
		}
		
		//to send control to the cart.jsp
		resp.sendRedirect("cart.jsp");
		
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		
		int itemId = Integer.parseInt(req.getParameter("itemId")); //return string so Integer.parseInt()
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		String imagePath = req.getParameter("imagePath"); 
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		
		Menu menu = menuDAOImpl.getMenu(itemId);
		
		String itemName = menu.getItem_Name();
		double itemPrice = menu.getPrice();
		
		CartItem cartItem = new CartItem(itemId, itemName, quantity, itemPrice,imagePath);
		cart.addItem(cartItem);
	}
	
	private void updateItemToCart(HttpServletRequest req, Cart cart) {
		
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(itemId, quantity);
		
	}
	
	private void deleteItemtoCart(HttpServletRequest req, Cart cart) {
	
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId);
		
	}
	
}
