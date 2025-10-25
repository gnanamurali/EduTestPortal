package com.EduTestPortal.controllers;

import java.io.IOException;

import java.util.List;

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

@WebServlet("/manageQuestions")
public class ManageQuestionsServlet extends HttpServlet
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
		
		Teacher currentTeacher = (Teacher) session.getAttribute("currentTeacher");
		int qid=Integer.parseInt(req.getParameter("qid"));
		QuizDAO qd=new QuizDAO();
		Quiz q=qd.getQuizById(qid);
		
		if (q == null) {
		    session.setAttribute("message", "Quiz not found");
		    resp.sendRedirect("teacherDashboard.jsp");
		    return;
		}
		
		if(q.getTid()==currentTeacher.getTid())
		{
		QuestionsDAO que=new QuestionsDAO();
		List<Question> questions=que.getQuestionsByQuizId(qid);
		System.out.println("[ManageQuestionsServlet] Quiz "+qid+" found");
		req.setAttribute("questionList", questions);
		req.setAttribute("qid", qid);
		req.setAttribute("quiz", q);
		RequestDispatcher rd=req.getRequestDispatcher("/manageQuestions.jsp");
		rd.forward(req, resp);
		}
		
		
		else
		{
			session.setAttribute("message", "Quiz "+qid+" could not be accessed");
			System.out.println("[ManageQuestionsServlet] teacher not authorized");
			resp.sendRedirect("teacherDashboard.jsp");
		}
	}

}
