package com.EduTestPortal.controllers;

import java.io.IOException;
import com.EduTestPortal.db.QuizDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ToggleQuizVisibility")
public class ToggleQuizVisibilityServlet extends HttpServlet {
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
        String visibleParam = req.getParameter("visible");

        if (qidParam == null || qidParam.isEmpty() || visibleParam == null || visibleParam.isEmpty()) {
            resp.sendRedirect("viewQuizzes?msg=Invalid+quiz+toggle+request");
            return;
        }

        try {
            int qid = Integer.parseInt(qidParam);
            boolean visible = Boolean.parseBoolean(visibleParam);

            QuizDAO dao = new QuizDAO();
            boolean success = dao.toggleQuizVisibility(qid, visible);

            
            if (session.getAttribute("currentAdmin") != null) {
                if (success)
                    resp.sendRedirect("ManageQuizzes?msg=Quiz+visibility+updated");
                else
                    resp.sendRedirect("ManageQuizzes?msg=Failed+to+update+visibility");
            } else {
                if (success)
                    resp.sendRedirect("viewQuizzes?msg=Quiz+visibility+updated");
                else
                    resp.sendRedirect("viewQuizzes?msg=Failed+to+update+visibility");
            }

        } catch (NumberFormatException e) {
            resp.sendRedirect("viewQuizzes?msg=Invalid+parameters");
        }
    }
}
