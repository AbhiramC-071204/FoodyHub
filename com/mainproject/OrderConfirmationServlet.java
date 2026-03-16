package com.mainproject;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderconfirmation")
public class OrderConfirmationServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 HttpSession session = req.getSession();

	        if (session.getAttribute("lastOrderId") == null) {
	            resp.sendRedirect("restaurant");
	            return;
	        }

	        session.removeAttribute("customerName");
	        session.removeAttribute("customerPhone");

		
		  req.getRequestDispatcher("OrderConfirmation.jsp").forward(req, resp);
		 
	}
	
}
