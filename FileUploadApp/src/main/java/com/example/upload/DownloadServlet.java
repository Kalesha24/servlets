package com.example.upload;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int fileId = Integer.parseInt(request.getParameter("fileId"));

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8888/filedb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "server");

            ps = conn.prepareStatement("SELECT filename, filepath FROM files WHERE id=?");
            ps.setInt(1, fileId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String fileName = rs.getString("filename");
                String filePath = rs.getString("filepath");
                File file = new File(filePath);

                if (!file.exists()) {
                    response.getWriter().write("File not found on server!");
                    return;
                }

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

                try (FileInputStream in = new FileInputStream(file);
                     OutputStream out = response.getOutputStream()) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }

        } catch (Exception e) {
            throw new ServletException("Download failed", e);
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }
}
