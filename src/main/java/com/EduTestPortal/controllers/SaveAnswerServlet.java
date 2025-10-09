package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SaveAnswerServlet")
public class SaveAnswerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("[SaveAnswerServlet] ❌ No session found");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // ✅ Always ensure map exists
        Map<Integer, String> answersMap =
                (Map<Integer, String>) session.getAttribute("answersMap");
        if (answersMap == null) {
            answersMap = new HashMap<>();
        }

        String qidStr = request.getParameter("questionId");
        String selectedOption = request.getParameter("selectedOption");
        String remainingStr = request.getParameter("remainingTime");

        // ✅ Update selected answer if present
        if (qidStr != null && selectedOption != null) {
            try {
                int questionId = Integer.parseInt(qidStr);
                answersMap.put(questionId, selectedOption);
                System.out.println("[SaveAnswerServlet] 💾 Saved Q" + questionId + " = " + selectedOption);
            } catch (NumberFormatException e) {
                System.out.println("[SaveAnswerServlet] Invalid questionId");
            }
        }

        // ✅ Update remaining time in session
        if (remainingStr != null) {
            try {
                int remaining = Integer.parseInt(remainingStr);
                session.setAttribute("remainingTime", remaining);
                System.out.println("[SaveAnswerServlet] ⏱ Updated remainingTime = " + remaining + " (Session ID: " + session.getId() + ")");
            } catch (NumberFormatException e) {
                System.out.println("[SaveAnswerServlet] Invalid remainingTime value");
            }
        }

        // ✅ Persist changes back
        session.setAttribute("answersMap", answersMap);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
