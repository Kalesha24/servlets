package com.example.quiz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.quiz.dao.QuestionDAO;
import com.example.quiz.dao.UserDAO;
import com.example.quiz.model.Question;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        List<Question> questions = QuestionDAO.getAllQuestions();
        int score = 0;

        for (Question q : questions) {
            String answer = request.getParameter("q" + q.getId());
            if (answer != null && answer.equals(q.getCorrectAnswer())) {
                score++;
            }
        }

        // save score in DB
        if (userId != null) {
            UserDAO.saveResult(userId, score, questions.size());
        }

        request.setAttribute("score", score);
        request.setAttribute("total", questions.size());
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}
