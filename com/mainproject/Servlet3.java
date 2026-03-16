package com.mainproject;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet3 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		
		String name = req.getParameter("fullname");
		
		PrintWriter out = resp.getWriter();
		
		out.println("Oops! " + name + " Your Account is Blocked. Kindly contact Admin");
		
	}
	
}
