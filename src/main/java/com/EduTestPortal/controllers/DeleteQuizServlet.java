package com.EduTestPortal.controllers;

import java.io.IOException;
import com.EduTestPortal.db.QuizDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteQuiz")
public class DeleteQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || 
           (session.getAttribute("currentTeacher") == null && session.getAttribute("currentAdmin") == null)) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String qidParam = req.getParameter("qid");
        if (qidParam == null || qidParam.isEmpty()) {
            resp.sendRedirect("viewQuizzes?msg=Invalid+quiz+ID");
            return;
        }

        try {
            int qid = Integer.parseInt(qidParam);
            QuizDAO qd = new QuizDAO();
            boolean deleted = qd.deleteQuiz(qid);

            if (session.getAttribute("currentAdmin") != null) {
               
                if (deleted)
                    resp.sendRedirect("ManageQuizzes?msg=Quiz+deleted+successfully");
                else
                    resp.sendRedirect("ManageQuizzes?msg=Quiz+deletion+failed");
            } else {
                
                if (deleted)
                    resp.sendRedirect("viewQuizzes?msg=Quiz+deleted+successfully");
                else
                    resp.sendRedirect("viewQuizzes?msg=Quiz+deletion+failed");
            }

        } catch (NumberFormatException e) {
            resp.sendRedirect("viewQuizzes?msg=Invalid+quiz+ID");
        }
    }
}
