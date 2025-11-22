package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditTeacher")
public class EditTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("tid"));
        TeacherDAO dao = new TeacherDAO();
        Teacher teacher = dao.getTeacherById(tid);

        req.setAttribute("teacher", teacher);
        RequestDispatcher rd = req.getRequestDispatcher("/editTeacher.jsp");
        rd.forward(req, resp);
    }
}
