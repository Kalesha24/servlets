import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Collect form data
		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String[] languages = request.getParameterValues("language");
		String duration = request.getParameter("duration");
		String comments = request.getParameter("comments");

		if (fullname == null || phone == null || gender == null || languages == null || duration == null
				|| comments == null || fullname.isEmpty() || phone.isEmpty() || gender.isEmpty()
				|| languages.length == 0 || duration.isEmpty() || comments.isEmpty()) {
			out.println("<h3 style='color:red;'>Error: Please fill all fields.</h3>");
			return;
		}

		// Display back to user
		out.println("<html><body>");
		out.println("<h2>Form Details Submitted:</h2>");
		out.println("Full Name: " + fullname + "<br>");
		out.println("Phone Number: " + phone + "<br>");
		out.println("Gender: " + gender + "<br>");

		out.println("Languages to Learn: ");
		if (languages != null) {
			for (String lang : languages) {
				out.print(lang + " ");
			}
		} else {
			out.print("None");
		}
		out.println("<br>");

		out.println("Course Duration: " + duration + "<br>");
		out.println("Additional Comments: " + comments + "<br>");
		out.println("</body></html>");
	}
}
