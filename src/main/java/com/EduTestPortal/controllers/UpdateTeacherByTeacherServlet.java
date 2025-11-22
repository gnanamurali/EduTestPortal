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

@WebServlet("/updateTeacherByTeacher")
public class UpdateTeacherByTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherDAO teacherDAO;

    @Override
    public void init() throws ServletException {
        teacherDAO = new TeacherDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("tid") == null) {
            response.sendRedirect("teacherLogin.jsp");
            return;
        }

        int tid = (int) session.getAttribute("tid");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String subject = request.getParameter("subject");

        Teacher t = new Teacher();
        t.setTid(tid);
        t.setName(name);
        t.setEmail(email);
        t.setPhone(phone);
        t.setSubject(subject);

        boolean updated = teacherDAO.updateTeacher(t);
        if (updated) {
            session.setAttribute("successMsg", "Profile updated successfully!");
            response.sendRedirect("teacherprofile");
        } else {
            session.setAttribute("errorMsg", "Failed to update profile.");
            response.sendRedirect("editTeacherByTeacher");
        }
    }
}
