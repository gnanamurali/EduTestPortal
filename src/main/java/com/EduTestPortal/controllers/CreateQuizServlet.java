package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Quiz;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/createQuiz")
public class CreateQuizServlet  extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		String quiztitle=req.getParameter("quizTitle");
		String quizsubject=req.getParameter("quizSubject");
		String batch[]=req.getParameterValues("batch");
		
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("currentTeacher") == null) {
		    resp.sendRedirect("teacherLogin.jsp");
		    return;
		}
		Teacher t=(Teacher) session.getAttribute("currentTeacher");
		int tid=t.getTid();
		Quiz q= new Quiz();
		q.setTitle(quiztitle);
		q.setSubject(quizsubject);
		q.setTid(tid);
		
		QuizDAO qd= new QuizDAO();
		int createdQuizId=qd.addQuiz(q);
		
		if(batch!=null && createdQuizId>0)
		{
			boolean assignQuiz=qd.assignQuizToBatch(createdQuizId, batch);
			if(assignQuiz==true)
			{
				System.out.println("[CreateQuizServlet] Quiz Created and batches assigned successfully");
				RequestDispatcher rd=req.getRequestDispatcher("/viewQuizzes.jsp");
				rd.forward(req, resp);
			}
			
			else
			{
				System.out.println("[CreateQuizServlet] Created quiz but Couldnt assign batches :some error occured");
				out.print("<h3 style=color:red>Some error occoured while creating quiz</h3>");
				RequestDispatcher rd=req.getRequestDispatcher("/createQuiz.jsp");
				rd.forward(req,resp);
			}
		}
		
		else if(batch==null && createdQuizId>0)
		{
			System.out.println("[CreateQuizServlet] Quiz Created and but batch not mentioned");
			RequestDispatcher rd=req.getRequestDispatcher("/viewQuizzes.jsp");
			rd.forward(req, resp);
			
		}
		else
		{
			System.out.println("[CreateQuizServlet] Couldnt create quiz:some error occured");
			req.setAttribute("message", "Quiz creation failed.");
			RequestDispatcher rd=req.getRequestDispatcher("/createQuiz.jsp");
			rd.forward(req,resp);
			
		}
		
	}

}
