package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.db.QuizDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentAdmin") == null) {
            resp.sendRedirect("adminLogin.jsp");
            return;
        }

        StudentDAO studentDAO = new StudentDAO();
        TeacherDAO teacherDAO = new TeacherDAO();
        QuizDAO quizDAO = new QuizDAO();

        int totalStudents = studentDAO.getStudentCount();
        int totalTeachers = teacherDAO.getTeacherCount();
        int totalQuizzes = quizDAO.getTotalQuizCount();
        int visibleQuizzes = quizDAO.getVisibleQuizCount();

        req.setAttribute("totalStudents", totalStudents);
        req.setAttribute("totalTeachers", totalTeachers);
        req.setAttribute("totalQuizzes", totalQuizzes);
        req.setAttribute("visibleQuizzes", visibleQuizzes);

        RequestDispatcher rd = req.getRequestDispatcher("/adminDashboard.jsp");
        rd.forward(req, resp);
    }
}
