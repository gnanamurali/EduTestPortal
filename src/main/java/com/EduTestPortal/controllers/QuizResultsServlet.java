package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.db.ResultsDAO;
import com.EduTestPortal.model.Quiz;
import com.EduTestPortal.model.QuizStats;
import com.EduTestPortal.model.Result;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/quizResults")
public class QuizResultsServlet extends HttpServlet
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
		
		Teacher teacher = (Teacher) session.getAttribute("currentTeacher");

		String qidParam = req.getParameter("qid");
		if (qidParam == null || qidParam.isEmpty()) 
	        {
	            session.setAttribute("message", "Invalid quiz selection.");
	            resp.sendRedirect("teacherDashboard.jsp");
	            return;
	        }
		
		int qid = Integer.parseInt(qidParam);
        QuizDAO qd = new QuizDAO();
        Quiz quiz = qd.getQuizById(qid);
        

        if (quiz == null) 
        {
            session.setAttribute("message", "Quiz not found.");
            resp.sendRedirect("teacherDashboard.jsp");
            return;
        }

        if (quiz.getTid() != teacher.getTid()) 
        {
            session.setAttribute("message", "You are not authorized to view this quiz's results.");
            resp.sendRedirect("teacherDashboard.jsp");
            return;
        }

		ResultsDAO resDAO = new ResultsDAO();
		List<Result> resultsList = resDAO.getResultsByQuizId(qid);
		System.out.println("[QuizResultsServlet] Results fetched for quiz ID: " + qid + " | Total results: " + resultsList.size());
		QuizStats stats = resDAO.getQuizStatistics(qid); 
       
		req.setAttribute("quizStats", stats);
        req.setAttribute("quiz", quiz);
        req.setAttribute("resultsList", resultsList);
        req.setAttribute("teacherName", teacher.getName());

       
        RequestDispatcher rd = req.getRequestDispatcher("/quizResults.jsp");
        rd.forward(req, resp);
		


	}

}
