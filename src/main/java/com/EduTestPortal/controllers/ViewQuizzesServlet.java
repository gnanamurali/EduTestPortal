package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/viewQuizzes")
public class ViewQuizzesServlet extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	if (session == null || session.getAttribute("currentTeacher") == null)
	{
	    resp.sendRedirect("teacherLogin.jsp");
	    return;
	}
	String message = (String) session.getAttribute("message");
	if (message != null) {
	    req.setAttribute("message", message);
	    session.removeAttribute("message");
	}
	
	Teacher t= (Teacher)session.getAttribute("currentTeacher");
	int tid=t.getTid();
	QuizDAO qd= new QuizDAO();
	List<Quiz> quizzes = qd.getQuizzesByTeacherId(tid);
	req.setAttribute("quizList", quizzes);
	RequestDispatcher rd=req.getRequestDispatcher("/viewQuizzes.jsp");
	rd.forward(req, resp);
}

}
