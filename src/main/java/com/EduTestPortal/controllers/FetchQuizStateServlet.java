package com.EduTestPortal.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/FetchQuizStateServlet")
public class FetchQuizStateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("[FetchQuizStateServlet] Session ID: " + session.getId());
        System.out.println("[FetchQuizStateServlet] remainingTime before send = " + session.getAttribute("remainingTime"));


        // ✅ If no valid session, return empty state
        if (session == null || session.getAttribute("currentStudent") == null) {
            response.getWriter().print("{\"remainingTime\":null, \"answers\":{}}");
            return;
        }

        // ✅ Get current session data
        Map<Integer, String> answersMap =
                (Map<Integer, String>) session.getAttribute("answersMap");
        Integer remainingTime = (Integer) session.getAttribute("remainingTime");

        // ✅ Fallback 1: If remainingTime is missing, use quizDuration
        if (remainingTime == null) {
            Integer quizDuration = (Integer) session.getAttribute("quizDuration");
            if (quizDuration != null) {
                remainingTime = quizDuration; // initialize from total duration
                session.setAttribute("remainingTime", remainingTime);
            } else {
                remainingTime = 0; // absolute fallback
            }
        }

        // ✅ Fallback 2: If answersMap missing, create empty one
        if (answersMap == null) {
            answersMap = new HashMap<>();
            session.setAttribute("answersMap", answersMap);
        }

        // ✅ Build JSON manually
        PrintWriter out = response.getWriter();
        out.print("{\"remainingTime\": " + remainingTime + ", \"answers\": {");

        boolean first = true;
        for (Map.Entry<Integer, String> entry : answersMap.entrySet()) {
            if (!first) out.print(",");
            out.print("\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"");
            first = false;
        }
        out.print("}}");
        out.flush();
    }
}
