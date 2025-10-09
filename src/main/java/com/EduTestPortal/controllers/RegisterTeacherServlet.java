package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.model.Teacher;
import com.EduTestPortal.utils.PasswordUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/registerTeacher")
public class RegisterTeacherServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("userName");
		String useremail=req.getParameter("userEmail");
		String userphone=req.getParameter("userPhone");
		String userpass=req.getParameter("userPass");
		String hashedpass=PasswordUtil.hash(userpass);
		System.out.println("Password hashed successfully for teacher: "+ useremail);
		String usersub=req.getParameter("userSub");
		Teacher t=new Teacher();
		t.setName(username);
		t.setEmail(useremail);
		t.setPhone(userphone);
		t.setPassword(hashedpass);
		t.setSubject(usersub);
		
		TeacherDAO td=new TeacherDAO();
		boolean success=td.addTeacher(t);
		
		if (success==true)
		{
			RequestDispatcher rd=req.getRequestDispatcher("/success.jsp");
			rd.forward(req, resp);
			
			
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("/failure.jsp");
			rd.forward(req, resp);
		}
		
		System.out.println("[TeacherRegisterServlet] Teacher registration " + (success ? "succeeded" : "failed"));
	}
}
