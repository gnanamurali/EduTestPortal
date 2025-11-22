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

@WebServlet("/ViewTeacherQuizzes")
public class ViewTeacherQuizzesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        int tid = Integer.parseInt(req.getParameter("tid"));
        QuizDAO dao = new QuizDAO();
        List<Quiz> quizList = dao.getQuizzesByTeacherId(tid);

        req.setAttribute("quizList", quizList);
        req.setAttribute("tid", tid);

        RequestDispatcher rd = req.getRequestDispatcher("/viewTeacherQuizzes.jsp");
        rd.forward(req, resp);
    }
}
