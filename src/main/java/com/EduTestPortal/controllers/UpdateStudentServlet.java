package com.EduTestPortal.controllers;

import java.io.IOException;
import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.model.Student;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UpdateStudent")
public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getSession().getAttribute("currentAdmin") == null) {
            resp.sendRedirect("adminLogin.jsp");
            return;
        }

        int sid = Integer.parseInt(req.getParameter("sid"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String department = req.getParameter("department");
        int yearOfStudy = Integer.parseInt(req.getParameter("yearOfStudy"));
        String batch = req.getParameter("batch");

        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setEmail(email);
        s.setPhone(phone);
        s.setDepartment(department);
        s.setYearOfStudy(yearOfStudy);
        s.setBatch(batch);

        StudentDAO dao = new StudentDAO();
        boolean updated = dao.updateStudent(s);

        if (updated) {
            resp.sendRedirect("ManageStudents?msg=Student+details+updated+successfully");
        } else {
            resp.sendRedirect("ManageStudents?msg=Update+failed.+Try+again.");
        }

    }
}

