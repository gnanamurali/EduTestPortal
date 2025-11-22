package com.EduTestPortal.controllers;

import java.io.IOException;
import com.EduTestPortal.db.StudentDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getSession().getAttribute("currentAdmin") == null) {
            resp.sendRedirect("adminLogin.jsp");
            return;
        }

        int sid = Integer.parseInt(req.getParameter("sid"));
        StudentDAO dao = new StudentDAO();
        dao.deleteStudent(sid);

        resp.sendRedirect("ManageStudents?msg=Student+deleted+successfully");

    }
}
