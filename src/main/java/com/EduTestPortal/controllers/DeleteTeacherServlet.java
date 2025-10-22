package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.TeacherDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteTeacher")
public class DeleteTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("tid"));
        TeacherDAO dao = new TeacherDAO();
        dao.deleteTeacher(tid);

        resp.sendRedirect("ManageTeachers?msg=Teacher+deleted+successfully");
    }
}
