package com.example.quiz.dao;

import com.example.quiz.model.Question;
import java.sql.*;
import java.util.*;

public class QuestionDAO {
	private static final String URL = "jdbc:mysql://localhost:8888/quizdb?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "server";

	public static List<Question> getAllQuestions() {
		List<Question> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM questions");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Question q = new Question();
				q.setId(rs.getInt("id"));
				q.setQuestionText(rs.getString("question_text"));
				q.setOptionA(rs.getString("option_a"));
				q.setOptionB(rs.getString("option_b"));
				q.setOptionC(rs.getString("option_c"));
				q.setOptionD(rs.getString("option_d"));
				q.setCorrectAnswer(rs.getString("correct_answer"));
				list.add(q);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
