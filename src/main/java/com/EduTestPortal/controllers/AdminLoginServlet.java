package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.EduTestPortal.db.AdminDAO;
import com.EduTestPortal.model.Admin;
import com.EduTestPortal.utils.PasswordUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String adminEmail = req.getParameter("email");
        String adminPass = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        AdminDAO dao = new AdminDAO();
        Admin admin = dao.getAdminByEmail(adminEmail);

        if (admin != null && PasswordUtil.verify(adminPass, admin.getPassword())) {
            HttpSession session = req.getSession(true);
            session.removeAttribute("currentStudent");
            session.removeAttribute("currentTeacher");

            session.setAttribute("currentAdmin", admin);
            session.setAttribute("aid", admin.getAid()); // ✅ added

            System.out.println("[AdminLoginServlet] Admin login successful, AID: " + admin.getAid());
            resp.sendRedirect("adminDashboard"); // ✅ direct redirect, not /adminDashboard servlet unless you have one
        } else {
            out.print("<h3 style='color:red;'>Email or password didn’t match</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/adminlogin.jsp");
            rd.include(req, resp);
            System.out.println("[AdminLoginServlet] Invalid admin credentials");
        }
    }
}
