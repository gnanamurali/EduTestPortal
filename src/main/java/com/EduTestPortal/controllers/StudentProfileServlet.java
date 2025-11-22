package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/studentprofile")
public class StudentProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Integer sid = (Integer) session.getAttribute("sid");
        if (sid == null) {
            Object cs = session.getAttribute("currentStudent");
            if (cs instanceof Student) sid = ((Student) cs).getSid();
        }
        if (sid == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Student student = studentDAO.getStudentById(sid);
        if (student == null) {
            session.setAttribute("errorMsg", "Unable to load profile. Please try again.");
            response.sendRedirect("studentDashboard.jsp");
            return;
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("studentProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
