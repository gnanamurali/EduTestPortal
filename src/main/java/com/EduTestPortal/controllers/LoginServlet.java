package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.EduTestPortal.db.StudentDAO;
import com.EduTestPortal.model.Student;
import com.EduTestPortal.utils.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String useremail = req.getParameter("userEmail");
        String userpass = req.getParameter("userPass");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        StudentDAO sd = new StudentDAO();
        Student s = sd.getStudentByEmail(useremail);

        if (s != null && PasswordUtil.verify(userpass, s.getPassword())) {
            HttpSession session = req.getSession(true);
            session.removeAttribute("currentAdmin");
            session.removeAttribute("currentTeacher");
            session.setAttribute("currentStudent", s);
            session.setAttribute("sid", s.getSid());

            System.out.println("✅ Login Success: SID = " + s.getSid());
            resp.sendRedirect("studentDashboard.jsp"); // Redirect — not forward
        } else {
            out.print("<h3 style='color:red;text-align:center;'>Invalid Email or Password</h3>");
            req.getRequestDispatcher("/login.jsp").include(req, resp);
            System.out.println("❌ Invalid login attempt for: " + useremail);
        }
    }
}
