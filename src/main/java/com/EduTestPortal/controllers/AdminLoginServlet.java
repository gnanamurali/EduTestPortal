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
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        AdminDAO dao = new AdminDAO();
        Admin admin = dao.getAdminByEmail(adminEmail);

        if (admin != null && PasswordUtil.verify(adminPass, admin.getPassword())) {
            HttpSession session = req.getSession();
            session.removeAttribute("currentStudent");
            session.removeAttribute("currentTeacher");
            session.setAttribute("currentAdmin", admin);
            System.out.println("[AdminLoginServlet] Admin authenticated successfully");
            
            
            resp.sendRedirect("adminDashboard");
        } else {
            out.print("<h3 style='color:red;'>Email and password didn’t match</h3>");
            RequestDispatcher rd = req.getRequestDispatcher("/adminlogin.jsp");
            rd.include(req, resp);
            System.out.println("[AdminLoginServlet] Invalid admin credentials");
        }
    }
}
