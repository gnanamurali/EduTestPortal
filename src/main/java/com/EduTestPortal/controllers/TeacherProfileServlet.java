package com.EduTestPortal.controllers;

import java.io.IOException;
import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/teacherprofile")
public class TeacherProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherDAO teacherDAO;

    @Override
    public void init() throws ServletException {
        teacherDAO = new TeacherDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("tid") == null) {
            response.sendRedirect("teacherLogin.jsp");
            return;
        }

        int tid = (int) session.getAttribute("tid");
        Teacher teacher = teacherDAO.getTeacherById(tid);

        if (teacher == null) {
            session.setAttribute("errorMsg", "Unable to load profile details.");
            response.sendRedirect("teacherDashboard.jsp");
            return;
        }

        request.setAttribute("teacher", teacher);
        request.getRequestDispatcher("teacherProfile.jsp").forward(request, response);
    }
}
