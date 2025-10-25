package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ManageTeachers")
public class ManageTeachersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherDAO dao = new TeacherDAO();
        List<Teacher> teacherList = dao.getAllTeachers();

        req.setAttribute("teacherList", teacherList);
        RequestDispatcher rd = req.getRequestDispatcher("/manageTeachers.jsp");
        rd.forward(req, resp);
    }
}
