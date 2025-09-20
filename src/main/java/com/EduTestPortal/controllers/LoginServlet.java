package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String useremail=req.getParameter("userEmail");
		String userpass=req.getParameter("userPass");
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		StudentDAO sd=new StudentDAO();
		Student s=sd.getStudentByEmail(useremail);
		
		if(s!=null && s.getPassword().equals(userpass))
		{
			HttpSession session =req.getSession();
			session.setAttribute("currentStudent", s);
			System.out.println("student found");
			RequestDispatcher rd=req.getRequestDispatcher("/studentDashboard.jsp");
			rd.forward(req,resp);
		}
		
		else
		{
			out.print("<h3 style=color:red>Email and password didnt match</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/login.jsp");
			rd.include(req,resp);
			System.out.println("Student not found");
			
		}
		
	}

}
