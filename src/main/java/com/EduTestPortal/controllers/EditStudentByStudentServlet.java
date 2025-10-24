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

@WebServlet("/editStudentByStudent")
public class EditStudentByStudentServlet extends HttpServlet {
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
        if (session == null || session.getAttribute("sid") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int sid = (int) session.getAttribute("sid");
        Student student = studentDAO.getStudentById(sid);

        if (student == null) {
            session.setAttribute("errorMsg", "Unable to load profile details.");
            response.sendRedirect("studentprofile");
            return;
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("editStudentByStudent.jsp").forward(request, response);
    }
}
