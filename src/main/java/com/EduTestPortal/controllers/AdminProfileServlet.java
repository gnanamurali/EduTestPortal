package com.EduTestPortal.controllers;

import java.io.IOException;
import com.EduTestPortal.db.AdminDAO;
import com.EduTestPortal.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminprofile")
public class AdminProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("aid") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        int aid = (int) session.getAttribute("aid");
        Admin admin = adminDAO.getAdminById(aid);

        if (admin == null) {
            session.setAttribute("errorMsg", "Unable to load profile details.");
            response.sendRedirect("adminDashboard.jsp");
            return;
        }

        request.setAttribute("admin", admin);
        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
    }
}
