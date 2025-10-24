package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.EduTestPortal.db.TeacherDAO;
import com.EduTestPortal.model.Teacher;
import com.EduTestPortal.utils.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginTeacher")
public class LoginTeacherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String useremail = req.getParameter("userEmail");
        String userpass = req.getParameter("userPass");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        TeacherDAO td = new TeacherDAO();
        Teacher t = td.getTeacherByEmail(useremail);

        if (t != null && PasswordUtil.verify(userpass, t.getPassword())) {
            HttpSession session = req.getSession(true);
            session.removeAttribute("currentAdmin");
            session.removeAttribute("currentStudent");

            session.setAttribute("currentTeacher", t);
            session.setAttribute("tid", t.getTid()); // ✅ important line

            System.out.println("[LoginTeacherServlet] Login successful for TID: " + t.getTid());
            resp.sendRedirect("teacherDashboard.jsp"); // ✅ redirect instead of forward
        } else {
            out.print("<h3 style='color:red;'>Invalid Email or Password</h3>");
            req.getRequestDispatcher("/teacherLogin.jsp").include(req, resp);
            System.out.println("[LoginTeacherServlet] Invalid login attempt for: " + useremail);
        }
    }
}
