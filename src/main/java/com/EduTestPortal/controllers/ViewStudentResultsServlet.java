package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;
import com.EduTestPortal.db.ResultsDAO;
import com.EduTestPortal.model.Result;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ViewStudentResults")
public class ViewStudentResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentAdmin") == null) {
            resp.sendRedirect("adminLogin.jsp");
            return;
        }

        int sid = Integer.parseInt(req.getParameter("sid"));
        ResultsDAO dao = new ResultsDAO();
        List<Result> results = dao.getResultsByStudentId(sid);

        req.setAttribute("results", results);
        req.setAttribute("sid", sid);

        RequestDispatcher rd = req.getRequestDispatcher("viewStudentResults.jsp");
        rd.forward(req, resp);
    }
}
