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
import jakarta.servlet.http.HttpSessionActivationListener;


@WebServlet("/updateQuiz")
public class UpdateQuizServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		int qid=Integer.parseInt(req.getParameter("qid"));
		String title=req.getParameter("quizTitle");
		String subject=req.getParameter("quizSubject");
		
		QuizDAO qd=new QuizDAO();
		Quiz q=new Quiz();
		
		q.setQid(qid);
		q.setTitle(title);
		q.setSubject(subject);
		
		boolean update=qd.updateQuiz(q);
		
		HttpSession session = req.getSession();
		if(update)
		{
			System.out.println("[UpdateQuizServlet] quiz:"+title+"edited successfully");
			session.setAttribute("message", ""+ title + " updated successfully!");
		}
		
		else
		{
			System.out.println("[UpdateQuizServlet] quiz:"+title+"was not edited");
			session.setAttribute("message",""+title+" was not be edited ");
			
		}
		resp.sendRedirect("viewQuizzes");
		
	}

}
