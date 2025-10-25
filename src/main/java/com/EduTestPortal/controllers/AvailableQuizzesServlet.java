package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Quiz;
import com.EduTestPortal.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/availableQuizzes")
public class AvailableQuizzesServlet extends HttpServlet
{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("currentStudent") == null)
		{
		    resp.sendRedirect("login.jsp");
		    return;
		}
		
		Student student=(Student)session.getAttribute("currentStudent");
		String batch=student.getBatch();
		
		QuizDAO qd=new QuizDAO();
		List<Quiz> availableQuizzes=qd.getQuizzesByBatch(batch);
		
		System.out.println("[AvailableQuizzesServlet] Quizzes fetched");
		req.setAttribute("quizzes", availableQuizzes);
		req.setAttribute("batch", batch);
		RequestDispatcher rd=req.getRequestDispatcher("/availableQuizzes.jsp");
		rd.forward(req, resp);
		
	}

}
