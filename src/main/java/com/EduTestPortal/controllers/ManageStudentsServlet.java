package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ManageStudents")
public class ManageStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedBatch = req.getParameter("batch");
        StudentDAO dao = new StudentDAO();
        List<Student> studentList;

        if (selectedBatch != null && !selectedBatch.equals("ALL")) {
            studentList = dao.getStudentsByBatch(selectedBatch);
        } else {
            studentList = dao.getAllStudents();
        }

        req.setAttribute("studentList", studentList);
        req.setAttribute("selectedBatch", selectedBatch == null ? "ALL" : selectedBatch);

        RequestDispatcher rd = req.getRequestDispatcher("/manageStudents.jsp");
        rd.forward(req, resp);
    }
}
