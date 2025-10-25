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

@WebServlet("/updateAdminByAdmin")
public class UpdateAdminByAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("aid") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        int aid = (int) session.getAttribute("aid");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Admin admin = new Admin();
        admin.setAid(aid);
        admin.setName(name);
        admin.setEmail(email);

        boolean updated = adminDAO.updateAdmin(admin);
        if (updated) {
            session.setAttribute("successMsg", "Profile updated successfully!");
            response.sendRedirect("adminprofile");
        } else {
            session.setAttribute("errorMsg", "Failed to update profile.");
            response.sendRedirect("editAdminByAdmin");
        }
    }
}
