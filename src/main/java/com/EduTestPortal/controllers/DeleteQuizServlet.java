package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.QuizDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteQuiz")
public class DeleteQuizServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("currentTeacher") == null)
		{
		    resp.sendRedirect("teacherLogin.jsp");
		    return;
		}
		
		try 
		{
		    int qid = Integer.parseInt(req.getParameter("qid"));
		    QuizDAO qd=new QuizDAO();
			boolean delete=qd.deleteQuiz(qid);
			
			if(delete)
			{
				System.out.println("[DeleteQuizServlet] Quiz deleted successfully");
				session.setAttribute("message", "Quiz deleted successfully!");
			}
			else
			{
				System.out.println("[DeleteQuizServlet] Quiz deletion failed");
				session.setAttribute("message", "Quiz deletion failed!");
			}
			resp.sendRedirect("viewQuizzes");
		} 
		catch (NumberFormatException e) 
		{
		    session.setAttribute("message", "Invalid quiz ID!");
		    resp.sendRedirect("viewQuizzes");
		    return;
		}
		
	}
	

}
