package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/teacherViewResult")
public class TeacherViewResultServlet extends HttpServlet
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
		
		Teacher teacher = (Teacher)session.getAttribute("currentTeacher");
		int tid=teacher.getTid();
		QuizDAO qd= new QuizDAO();
		List<Quiz> quizzes=qd.getQuizzesByTeacherId(tid);
		System.out.println("[TeacherViewResultServlet] Quizzes fetched for tid: "+tid);
		System.out.println("Total quizzes fetched: " + quizzes.size());
		for (Quiz quiz : quizzes) {
		    System.out.println("Quiz found -> ID: " + quiz.getQid() + ", Title: " + quiz.getTitle());
		}
		
		req.setAttribute("quizList", quizzes);
		req.setAttribute("teacherName", teacher.getName());
		RequestDispatcher rd =req.getRequestDispatcher("/teacherViewResults.jsp");
		rd.forward(req, resp);
	}

}
