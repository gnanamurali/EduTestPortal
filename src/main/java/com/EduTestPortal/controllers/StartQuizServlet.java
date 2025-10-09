package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.EduTestPortal.db.QuestionsDAO;
import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Question;
import com.EduTestPortal.model.Quiz;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/startQuiz")
public class StartQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentStudent") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int qid = Integer.parseInt(req.getParameter("qid"));
        QuizDAO qd = new QuizDAO();
        QuestionsDAO qued = new QuestionsDAO();

        Quiz q = qd.getQuizById(qid);
        List<Question> questions = qued.getQuestionsByQuizId(qid);

        if (q == null || questions == null || questions.isEmpty()) {
            session.setAttribute("message", "Quiz not found or empty.");
            resp.sendRedirect("availableQuizzes");
            return;
        }

        // ✅ Check if timer is already active in this session
        if (session.getAttribute("remainingTime") == null ||
            session.getAttribute("quizId") == null ||
            !session.getAttribute("quizId").equals(qid)) {

            // Initialize new quiz state
            session.setAttribute("quizId", qid);
            session.setAttribute("quizStartTime", System.currentTimeMillis());
            session.setAttribute("quizDuration", q.getDuration() * 60); // total seconds
            session.setAttribute("remainingTime", q.getDuration() * 60); // initial remaining time
            session.setAttribute("answersMap", new HashMap<Integer, String>());

            System.out.println("[StartQuizServlet] Timer initialized for " + (q.getDuration() * 60) + " seconds (Quiz ID: " + qid + ")");
        } else {
            System.out.println("[StartQuizServlet] Timer already exists, skipping reset for Quiz ID: " + qid);
            System.out.println("[StartQuizServlet] Remaining time currently: " + session.getAttribute("remainingTime"));
        }

        // ✅ Set attributes for JSP
        req.setAttribute("qid", qid);
        req.setAttribute("quizDetails", q);
        req.setAttribute("quizQuestions", questions);

        // ✅ Forward to JSP page
        RequestDispatcher rd = req.getRequestDispatcher("/startQuiz.jsp");
        rd.forward(req, resp);
    }
}
