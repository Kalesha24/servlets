package com.example.upload;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/upload")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 5 * 1024 * 1024,   // 5MB
    maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class UploadServlet extends HttpServlet {

    // ✅ Use user’s home directory for uploads (cross-platform safe)
    private static final String UPLOAD_DIR = System.getProperty("user.home") + File.separator + "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // create if not exists
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Part filePart = request.getPart("file");
            String fileName = new File(filePart.getSubmittedFileName()).getName();
            String filePath = UPLOAD_DIR + File.separator + fileName;

            // Save file on disk
            filePart.write(filePath);

            long fileSize = filePart.getSize();

            // Save file metadata in DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8888/filedb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "server"); // ✅ use same password everywhere

            ps = conn.prepareStatement("INSERT INTO files (filename, filepath, filesize) VALUES (?, ?, ?)");
            ps.setString(1, fileName);
            ps.setString(2, filePath);
            ps.setLong(3, fileSize);
            ps.executeUpdate();

            request.setAttribute("message", "File uploaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "File upload failed: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }

        getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
    }
}
