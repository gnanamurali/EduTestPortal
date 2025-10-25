package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Quiz;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editQuiz")
public class EditQuizServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		    Quiz q=qd.getQuizById(qid);
		    if(q !=null)
		    {
		    		req.setAttribute("quiz",q);
		    		RequestDispatcher rd=req.getRequestDispatcher("editQuiz.jsp");
		    		rd.forward(req, resp);
		    }
		    else
		    {
		    		System.out.println("[EditQuizServlet] Quiz not found");
		    		session.setAttribute("message", "Quiz not found");
		    		 resp.sendRedirect("viewQuizzes");
		    }
		}
		catch (NumberFormatException e) 
		{
			System.out.println("[EditQuizServlet] Invalid QuizID ");
		    session.setAttribute("message", "Invalid quiz ID!");
		    resp.sendRedirect("viewQuizzes");
		    return;
		}
	}
}
