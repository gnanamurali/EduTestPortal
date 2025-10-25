package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.AdminDAO;
import com.EduTestPortal.model.Admin;
import com.EduTestPortal.utils.PasswordUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminRegister")
public class AdminRegisterServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		 	String name = req.getParameter("name");
	        String email = req.getParameter("email");
	        String password = req.getParameter("password");

	        if (name == null || email == null || password == null || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
	            req.setAttribute("message", "All fields are required.");
	            RequestDispatcher rd = req.getRequestDispatcher("admin_register.jsp");
	            rd.forward(req, resp);
	            return;
	        }

	        String hashedPass = PasswordUtil.hash(password);
	        Admin admin = new Admin();
	        admin.setName(name);
	        admin.setEmail(email);
	        admin.setPassword(hashedPass);

	        AdminDAO dao = new AdminDAO();
	        boolean added = dao.addAdmin(admin);

	        if (added) 
	        {
	            req.setAttribute("message", "Admin registered successfully. Please login.");
	            RequestDispatcher rd = req.getRequestDispatcher("/adminLogin.jsp");
	            rd.forward(req, resp);
	        } 
	        else {
	            req.setAttribute("message", "Registration failed. Email may already exist.");
	            RequestDispatcher rd = req.getRequestDispatcher("/adminRegister.jsp");
	            rd.forward(req, resp);
	        }
	    }
	

}
