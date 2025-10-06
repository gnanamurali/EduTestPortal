package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

import com.EduTestPortal.db.QuestionsDAO;
import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Question;
import com.EduTestPortal.model.Quiz;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/startQuiz")
public class StartQuizServlet extends HttpServlet
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
		
		int qid=Integer.parseInt(req.getParameter("qid"));
		QuizDAO qd=new QuizDAO() ;
		QuestionsDAO qued=new QuestionsDAO();
		
		Quiz q=qd.getQuizById(qid);
		List<Question> questions=qued.getQuestionsByQuizId(qid);
		
		if (q == null) {
		    session.setAttribute("message", "Quiz not found!");
		    resp.sendRedirect("availableQuizzes");
		    return;
		}

		if (questions == null || questions.isEmpty()) 
		{
		    session.setAttribute("message", "This quiz has no questions yet.");
		    resp.sendRedirect("availableQuizzes");
		    return;
		}
	
			
	        System.out.println("[StartQuizServlet] Questions for quiz "+qid+" fetched successfully");
			System.out.println("[StartQuizServlet] " + questions.size() + " questions fetched");
		
			req.setAttribute("qid", qid);
			req.setAttribute("quizDetails", q);
			req.setAttribute("quizQuestions", questions);
			RequestDispatcher rd=req.getRequestDispatcher("/startQuiz.jsp");
			rd.forward(req, resp);
	}

}
