package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		HttpSession hs = req.getSession(false);
		if (hs != null) 
		{
		    hs.invalidate();
		}
		out.print("<h3 style=color:green>Logged out Successfully</h3>");
		resp.sendRedirect("login.jsp");
	}
		

}