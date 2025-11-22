package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateTeacher")
public class UpdateTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("tid"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String subject = req.getParameter("subject");

        Teacher t = new Teacher();
        t.setTid(tid);
        t.setName(name);
        t.setEmail(email);
        t.setPhone(phone);
        t.setSubject(subject);

        TeacherDAO dao = new TeacherDAO();
        boolean updated = dao.updateTeacher(t);

        if (updated) {
            resp.sendRedirect("ManageTeachers?msg=Teacher+details+updated+successfully");
        } else {
            resp.sendRedirect("ManageTeachers?msg=Update+failed.+Try+again.");
        }
    }
}
