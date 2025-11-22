package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Quiz;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ManageQuizzes")
public class ManageQuizzesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentAdmin") == null) {
            resp.sendRedirect("adminLogin.jsp");
            return;
        }

        QuizDAO dao = new QuizDAO();
        List<Quiz> quizList = dao.getAllQuizzes();

        req.setAttribute("quizList", quizList);
        RequestDispatcher rd = req.getRequestDispatcher("/manageQuizzes.jsp");
        rd.forward(req, resp);
    }
}
