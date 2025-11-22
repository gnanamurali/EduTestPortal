package com.EduTestPortal.controllers;

import java.io.IOException;
import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.model.Student;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/EditStudent")
public class EditStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getSession().getAttribute("currentAdmin") == null) {
            resp.sendRedirect("adminLogin.jsp");
            return;
        }

        int sid = Integer.parseInt(req.getParameter("sid"));
        StudentDAO dao = new StudentDAO();
        Student student = dao.getStudentById(sid);

        req.setAttribute("student", student);
        RequestDispatcher rd = req.getRequestDispatcher("editStudent.jsp");
        rd.forward(req, resp);
    }
}
