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

@WebServlet("/updateStudentByStudent")
public class UpdateStudentByStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sid") == null) {
            response.sendRedirect("studentLogin.jsp");
            return;
        }

        int sid = (int) session.getAttribute("sid");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        int yearOfStudy = Integer.parseInt(request.getParameter("yearOfStudy"));

        Student existing = studentDAO.getStudentById(sid);

        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setEmail(email);
        s.setPhone(phone);
        s.setDepartment(department);
        s.setYearOfStudy(yearOfStudy);
        s.setBatch(existing != null ? existing.getBatch() : null);

        boolean updated = studentDAO.updateStudent(s);
        if (updated) {
            session.setAttribute("successMsg", "Profile updated successfully!");
            response.sendRedirect("studentprofile");
        } else {
            session.setAttribute("errorMsg", "Failed to update profile.");
            response.sendRedirect("editStudentByStudent");
        }
    }
}
