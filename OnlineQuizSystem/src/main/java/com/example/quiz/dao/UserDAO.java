package com.example.quiz.dao;

import java.sql.*;

public class UserDAO {
	private static final String URL = "jdbc:mysql://localhost:8888/quizdb?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "server";

	public static int validateUser(String username, String password) {
		int userId = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM users WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("id");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}

	public static void saveResult(int userId, int score, int total) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO results(user_id,score,total) VALUES(?,?,?)");
			ps.setInt(1, userId);
			ps.setInt(2, score);
			ps.setInt(3, total);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
