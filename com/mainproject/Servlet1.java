package com.mainproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pro1.connector.ConnectorFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class Servlet1 extends HttpServlet{
		
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		
		String name = req.getParameter("fullname");
		String email = req.getParameter("emailaddress");
		String password = req.getParameter("password");
		String confirmpassword = req.getParameter("confirmpassword");
		
		String phone = req.getParameter("phone") != null ? req.getParameter("phone") : "";
	    String address = req.getParameter("address") != null ? req.getParameter("address") : "";
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

		if(!email.matches(emailPattern)){
		    
		    req.setAttribute("msg","<h3>Invalid Email Format</h3>");
		    RequestDispatcher rd = req.getRequestDispatcher("index.html");
		    rd.forward(req, resp);
		    return;
		}
		
		String passwordPattern = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";

		if(!password.matches(passwordPattern)){
		    
		    req.setAttribute("msg","<h3>Password must contain 8 characters, one uppercase letter and one number</h3>");
		    RequestDispatcher rd = req.getRequestDispatcher("index.html");
		    rd.forward(req, resp);
		    return;
		}
		
		if(email == null || password == null || !password.equals(confirmpassword)) {
			out.println("Invalid input or password do not match");
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.forward(req, resp);
			return;
		}
		
		try {
			
			Connection con = ConnectorFactory.requestConnection();
			
			// DATABASE CONNECTION MESSAGE
			if(con != null) {
				System.out.println("Database connected successfully");
			} else {
				System.out.println("Database not connected");
			}
			
			String query = "SELECT COUNT(*) FROM `user` WHERE Email = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,email);
			
			ResultSet res = pstmt.executeQuery();
			
			res.next();
			int count = res.getInt(1);
			
			if(count > 0) {
				
				req.setAttribute("msg","<h3>"+ name +" Already Registrated. Kindly Login.</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req, resp);
				return;
			}
			
			else{
				String insertquery = "INSERT INTO user (User_ID,User_Name,Password, Email, Phone, Address, Role) VALUES (?,?,?,?,?,?,?)";

				PreparedStatement insertpstmt = con.prepareStatement(insertquery);

				int userid = (int)(Math.random()*10000); // simple id generation

				insertpstmt.setInt(1, userid);
				insertpstmt.setString(2,name);
				insertpstmt.setString(3,password);
				insertpstmt.setString(4,email);
				insertpstmt.setString(5, phone);
				insertpstmt.setString(6, address);
				insertpstmt.setString(7, "Customer");
				
				insertpstmt.executeUpdate();
				
				req.setAttribute("msg","<h3>" + name + " your Registered Successfully. Kindly Login.</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req, resp);
				
				insertpstmt.close();
				res.close();
				pstmt.close();
			}
			
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("Database not connected");
		}
	}
}