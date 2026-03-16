package com.mainproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pro1.connector.ConnectorFactory;
import com.pro1.dao.UserDAOImpl;
import com.pro1.dto.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		
		//String name = req.getParameter("fullname");
		String email = req.getParameter("emailaddress");
		String password = req.getParameter("password");
		UserDAOImpl userDAO = new UserDAOImpl();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		if(email == null || password == null)
		{
			 req.setAttribute("msg", "Invalid input. Please try again.");
	         RequestDispatcher rd =  req.getRequestDispatcher("login.jsp");
	         rd.forward(req, resp);
	         return;
		}
		
		HttpSession session = req.getSession();
		
		Integer attempts = (Integer)session.getAttribute("attempts");
		
		if(attempts == null)
		{
			attempts = 0;
		}
		
		if(attempts >= 3)
		{
			req.setAttribute("msg", " your account locked. Too many failed attempts.");
			  //req.setAttribute("msg", name + " your account locked. Too many failed attempts.");
			  RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			  rd.forward(req, resp);
	          return;
		}
		
		try {
			Connection con = ConnectorFactory.requestConnection();
			
			String query = "SELECT Password FROM `user` WHERE Email = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			
			ResultSet res = pstmt.executeQuery();
			
			if(res.next()) {
				
				String ogpassword = res.getString("Password");
				
				if(ogpassword != null && password.equals(ogpassword))
				{
					session.setAttribute("attempts", 0);
					User user = userDAO.getUserByEmail(email); 
					session.setAttribute("user", user);
					session.setAttribute("email", email);
					resp.sendRedirect("restaurant");
//				RequestDispatcher rd = req.getRequestDispatcher("/restaurant");
//				rd.forward(req, resp);
				}
				else{
					attempts++;
					session.setAttribute("attempts", attempts);
					
					req.setAttribute("msg", "wrong password. Attempts left: " + (3 - attempts));
					
					RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
					rd.forward(req, resp);
					
				}
			}
			else {
				
				 // Email not registered
                req.setAttribute("msg", "User not registered. Please register.");
                RequestDispatcher rd = req.getRequestDispatcher("index.html");
                rd.forward(req, resp);

			}
			
			res.close();
			con.close();
			pstmt.close();
			
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			out.println("<h3>Server error. Try again later.</h3>");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
