package com.EduTestPortal.controllers;

import java.io.IOException;
import java.util.List;

import com.EduTestPortal.db.QuestionsDAO;
import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.db.ResultsDAO;
import com.EduTestPortal.model.Question;
import com.EduTestPortal.model.Quiz;
import com.EduTestPortal.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/submitQuiz")
public class SubmitQuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("currentStudent");
        int sid = student.getSid();
        int qid = Integer.parseInt(req.getParameter("qid"));
        int score = 0;

        QuestionsDAO qd = new QuestionsDAO();
        List<Question> questions = qd.getQuestionsByQuizId(qid);

        // ✅ Calculate score
        for (Question q : questions) {
            String userAnswer = req.getParameter("answer_" + q.getQueId());
            if (userAnswer != null && userAnswer.equalsIgnoreCase(q.getCorrectOption())) {
                score++;
            }
        }

        // ✅ Calculate percentage
        double percentage = ((double) score / questions.size()) * 100.0;

        ResultsDAO r = new ResultsDAO();
        boolean attempt = r.hasAttemptedQuiz(sid, qid);
        if (!attempt) {
        		boolean save = r.saveResult(sid, qid, score, percentage);

            if (save) {
                System.out.println("[SubmitQuizServlet] Quiz score saved");

                QuizDAO quizdao = new QuizDAO();
                Quiz quiz = quizdao.getQuizById(qid);

                // ✅ Send all data to results.jsp
                req.setAttribute("quizTitle", quiz.getTitle());
                req.setAttribute("quizSubject", quiz.getSubject());
                req.setAttribute("quizScore", score);
                req.setAttribute("totalQuestions", questions.size());
                req.setAttribute("percentage", String.format("%.2f", percentage)); // formatted to 2 decimals

                // Clear session quiz data
                session.removeAttribute("answersMap");
                session.removeAttribute("remainingTime");
                session.removeAttribute("quizStartTime");
                session.removeAttribute("quizDuration");
                System.out.println("[SubmitQuizServlet] Cleared quiz session data after submission");

                RequestDispatcher rd = req.getRequestDispatcher("/results.jsp");
                rd.forward(req, resp);
            } else {
                System.out.println("[SubmitQuizServlet] Quiz score was not saved");
                session.setAttribute("message", "Your score was not saved. Take quiz again!");
                resp.sendRedirect("availableQuizzes");
                return;
            }
        } else {
            System.out.println("[SubmitQuizServlet] Quiz already attempted");
            session.setAttribute("message", "Quiz already attempted!");
            resp.sendRedirect("availableQuizzes");
            return;
        }
    }
}
