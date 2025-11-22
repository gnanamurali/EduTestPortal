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
public class QuizResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || 
        		   (session.getAttribute("currentTeacher") == null && session.getAttribute("currentAdmin") == null)) {
        		    resp.sendRedirect("teacherLogin.jsp");
        		    return;
        	}
        String qidParam = req.getParameter("qid");
        if (qidParam == null || qidParam.isEmpty()) {
            resp.sendRedirect("viewQuizzes?msg=Invalid+quiz+selection");
            return;
        }

        int qid = Integer.parseInt(qidParam);
        QuizDAO qd = new QuizDAO();
        Quiz quiz = qd.getQuizById(qid);

        if (quiz == null) {
            resp.sendRedirect("viewQuizzes?msg=Quiz+not+found");
            return;
        }

        // ✅ If it's a teacher, verify ownership
        if (session.getAttribute("currentTeacher") != null) {
            Teacher teacher = (Teacher) session.getAttribute("currentTeacher");
            if (quiz.getTid() != teacher.getTid()) {
                resp.sendRedirect("teacherDashboard.jsp?msg=Unauthorized+access");
                return;
            }
            req.setAttribute("userRole", "teacher");
        }

        
        if (session.getAttribute("currentAdmin") != null) {
            req.setAttribute("userRole", "admin");
        }

        ResultsDAO resDAO = new ResultsDAO();
        List<Result> resultsList = resDAO.getResultsByQuizId(qid);
        QuizStats stats = resDAO.getQuizStatistics(qid);

        req.setAttribute("quizStats", stats);
        req.setAttribute("quiz", quiz);
        req.setAttribute("resultsList", resultsList);

        RequestDispatcher rd = req.getRequestDispatcher("/quizResults.jsp");
        rd.forward(req, resp);
    }
}
