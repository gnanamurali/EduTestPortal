package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.model.Teacher;
import com.EduTestPortal.utils.PasswordUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginTeacher")
public class LoginTeacherServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String useremail=req.getParameter("userEmail");
		String userpass=req.getParameter("userPass");
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		TeacherDAO td=new TeacherDAO();
		Teacher t=td.getTeacherByEmail(useremail);
		
		if(t!=null && PasswordUtil.verify(userpass,t.getPassword()))
		{
			HttpSession session =req.getSession();
			session.removeAttribute("currentAdmin");
			session.removeAttribute("currentStudent");
			session.setAttribute("currentTeacher", t);
			System.out.println("[LoginTeacherServlet]  teacher found");
			RequestDispatcher rd=req.getRequestDispatcher("/teacherDashboard.jsp");
			rd.forward(req,resp);
		}
		
		else
		{
			out.print("<h3 style=color:red>Email and password didnt match</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/teacherLogin.jsp");
			rd.include(req,resp);
			System.out.println("[LoginTeacherServlet] Student not found");
			
		}
	}
	
}
	
	


