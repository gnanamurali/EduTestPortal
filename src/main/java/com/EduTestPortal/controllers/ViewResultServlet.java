package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

import com.EduTestPortal.db.ResultsDAO;
import com.EduTestPortal.model.Result;
import com.EduTestPortal.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewResult")
public class ViewResultServlet extends HttpServlet 
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
		
		Student student = (Student) session.getAttribute("currentStudent");
		int sid = student.getSid();
		
		ResultsDAO resd= new ResultsDAO();
		List<Result> studentresults=resd.getResultsByStudentId(sid);
		if (studentresults.isEmpty()) 
		{
		    session.setAttribute("message", "You haven’t attempted any quizzes yet!");
		}
		else {
		System.out.println("[ViewResultsServlet] Results found for SID: S"+sid);
		req.setAttribute("results",studentresults);
		req.setAttribute("studentName", student.getName());
		req.setAttribute("studentBatch", student.getBatch());
		RequestDispatcher rd = req.getRequestDispatcher("/viewResults.jsp");
		rd.forward(req, resp);
		}

	}

}
