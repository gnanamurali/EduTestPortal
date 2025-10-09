package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.model.Student;
import com.EduTestPortal.utils.PasswordUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String username=req.getParameter("userName");
		String useremail=req.getParameter("userEmail");
		String userphone=req.getParameter("userPhone");
		String userpass=req.getParameter("userPass");
		String hashedpass=PasswordUtil.hash(userpass);
		System.out.println("Password hashed successfully for user: "+ useremail);
		String userdept=req.getParameter("userDept");
		int yos=Integer.parseInt(req.getParameter("userYos"));
		String userbatch=req.getParameter("userBatch");
		
		Student s= new Student();
		s.setName(username);
		s.setEmail(useremail);
		s.setPhone(userphone);
		s.setPassword(hashedpass);
		s.setDepartment(userdept);
		s.setYearOfStudy(yos);
		s.setBatch(userbatch);
		
		StudentDAO sd=new StudentDAO();
		boolean success=sd.addStudent(s);
		
		if(success==true)
		{
			RequestDispatcher rd=req.getRequestDispatcher("/success.jsp");
			rd.forward(req, resp);
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("/failure.jsp");
			rd.forward(req, resp);
		}
		
		System.out.println("[RegisterServlet] Student registration " + (success ? "succeeded" : "failed"));

	}

}
