package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.QuestionsDAO;
import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Quiz;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteQuestion")
public class DeleteQuestionServlet extends HttpServlet 
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
		
		int qid = Integer.parseInt(req.getParameter("qid"));
		
		Teacher teacher=(Teacher)session.getAttribute("currentTeacher");
		QuizDAO qd=new QuizDAO();
		Quiz q=qd.getQuizById(qid);
		
		if(q==null)
		{
			session.setAttribute("message", "Quiz not found");
			resp.sendRedirect("teacherDashboard.jsp");
			return;
		}
		
		if(q.getTid()==teacher.getTid())
		{		
		
		try 
		{
		
			int quesid = Integer.parseInt(req.getParameter("quesId"));
		    QuestionsDAO qued=new QuestionsDAO();
		    boolean delete=qued.deleteQuestion(quesid);
			
			if(delete)
			{
				System.out.println("[DeleteQuestionServlet] Question deleted successfully");
				session.setAttribute("message", "Question deleted successfully!");
			}
			else
			{
				System.out.println("[DeleteQuestionServlet] Question deletion failed");
				session.setAttribute("message", "Question deletion failed!");
			}
			resp.sendRedirect("manageQuestions?qid="+qid);
		} 
		catch (NumberFormatException e) 
		{
		    session.setAttribute("message", "Invalid question ID!");
		    resp.sendRedirect("manageQuestions?qid="+qid);
		    return;
		}
		
		}
		
		else
		{
			session.setAttribute("message", "You are not authorized to manage this quiz");
			resp.sendRedirect("teacherDashboard.jsp");
		}
		
	
	
	}

}
