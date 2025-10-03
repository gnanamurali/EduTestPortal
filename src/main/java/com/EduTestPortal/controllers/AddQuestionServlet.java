package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.QuestionsDAO;
import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Question;
import com.EduTestPortal.model.Quiz;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	
		int qid=Integer.parseInt(req.getParameter("qid"));
		
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("currentTeacher") == null)
		{
		    resp.sendRedirect("teacherLogin.jsp");
		    return;
		}
		
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
			req.setAttribute("qid", qid);
			req.setAttribute("quiz", q);
			RequestDispatcher rd=req.getRequestDispatcher("/addQuestion.jsp");
			rd.forward(req, resp);
		}
		
		else
		{
			session.setAttribute("message", "You are not authorized to manage this quiz");
			resp.sendRedirect("teacherDashboard.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("currentTeacher") == null)
		{
		    resp.sendRedirect("teacherLogin.jsp");
		    return;
		}
		
		int qid=Integer.parseInt(req.getParameter("qid"));
		String questiontxt=req.getParameter("questionText");
		String optA=req.getParameter("optionA");
		String optB=req.getParameter("optionB");
		String optC=req.getParameter("optionC");
		String optD=req.getParameter("optionD");
		String crtopt=req.getParameter("correctOption");
		
		Question que=new Question();
		que.setQid(qid);
		que.setQuestionText(questiontxt);
		que.setOptionA(optA);
		que.setOptionB(optB);
		que.setOptionC(optC);
		que.setOptionD(optD);
		que.setCorrectOption(crtopt);
		
		QuestionsDAO qd=new QuestionsDAO();
		int success=qd.addQuestion(que);
		
		if (success>0)
		{
			session.setAttribute("message", "Question added Successfully");
		}
		else
		{
			session.setAttribute("message", "Question was not added");
		}
		resp.sendRedirect("addQuestion?qid=" + qid);
		
		
	}

}
