package com.EduTestPortal.controllers;

import java.io.IOException;

import com.EduTestPortal.db.QuestionsDAO;
import com.EduTestPortal.db.QuizDAO;
import com.EduTestPortal.model.Question;
import com.EduTestPortal.model.Quiz;
import com.EduTestPortal.model.Teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/editQuestion")
public class EditQuestionServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentTeacher") == null)
        {
            resp.sendRedirect("teacherLogin.jsp");
            return;
        }

        int qid = Integer.parseInt(req.getParameter("qid"));
        int queid = Integer.parseInt(req.getParameter("quesId"));

        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");

        QuizDAO qd = new QuizDAO();
        Quiz q = qd.getQuizById(qid);

        if (q == null)
        {
            session.setAttribute("message", "Quiz not found");
            resp.sendRedirect("teacherDashboard.jsp");
            return;
        }

        if (q.getTid() == teacher.getTid())
        {
            QuestionsDAO questionDAO = new QuestionsDAO();
            Question question = questionDAO.getQuestionById(queid);

            if (question == null) {
                session.setAttribute("message", "Question not found");
                resp.sendRedirect("manageQuestions?qid=" + qid);
                return;
            }

            req.setAttribute("question", question);
            req.setAttribute("qid", qid);
            req.setAttribute("quiz", q);
            RequestDispatcher rd = req.getRequestDispatcher("/editQuestion.jsp");
            rd.forward(req, resp);
        }
        else
        {
            session.setAttribute("message", "You are not authorized to manage this quiz");
            resp.sendRedirect("teacherDashboard.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentTeacher") == null)
        {
            resp.sendRedirect("teacherLogin.jsp");
            return;
        }

        int qid = Integer.parseInt(req.getParameter("qid"));
        int quesid = Integer.parseInt(req.getParameter("quesId"));
        String quetxt = req.getParameter("questionText");
        String opta = req.getParameter("optionA");
        String optb = req.getParameter("optionB");
        String optc = req.getParameter("optionC");
        String optd = req.getParameter("optionD");
        String correctoption = req.getParameter("correctOption");

        Teacher teacher = (Teacher) session.getAttribute("currentTeacher");

        QuizDAO qd = new QuizDAO();
        Quiz q = qd.getQuizById(qid);

        if (q == null)
        {
            session.setAttribute("message", "Quiz not found");
            resp.sendRedirect("teacherDashboard.jsp");
            return;
        }

        if (q.getTid() == teacher.getTid())
        {
            Question que = new Question();
            que.setQid(qid);
            que.setQueId(quesid);
            que.setQuestionText(quetxt);
            que.setOptionA(opta);
            que.setOptionB(optb);
            que.setOptionC(optc);
            que.setOptionD(optd);
            que.setCorrectOption(correctoption);

            QuestionsDAO qued = new QuestionsDAO();
            boolean update = qued.updateQuestion(que);

            if (update)
            {
                System.out.println("[EditQuestionServlet] Question edited successfully");
                session.setAttribute("message", "Question updated Successfully");
            }
            else
            {
                System.out.println("[EditQuestionServlet] Question editing failed");
                session.setAttribute("message", "Question was not updated");
            }
            resp.sendRedirect("manageQuestions?qid=" + qid);
        }
        else
        {
            session.setAttribute("message", "You are not authorized to edit this quiz");
            resp.sendRedirect("teacherDashboard.jsp");
        }
    }
}

