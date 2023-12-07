package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDao;
import dao.CourseDaoImpl;
import dao.StudentDao;
import dao.StudentDaoImpl;
import pojos.Student;

/**
 * Servlet implementation class ValidatingStudentServlet
 */
@WebServlet("/add_or_not")
public class ValidatingStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			HttpSession hs = request.getSession();
			String errMsg = (String) hs.getAttribute("error");
			if (errMsg == null) {
				StudentDao std = (StudentDaoImpl)hs.getAttribute("student");
				CourseDao csd = (CourseDaoImpl)hs.getAttribute("course");
				Student verifiedStudent = (Student)hs.getAttribute("verifiedStudent");
				try {
					std.addStudent(verifiedStudent);
					pw.print("<!DOCTYPE html>"
							+ "<html lang=\"en\">"
							+ ""
							+ "<head>"
							+ "    <meta charset=\"UTF-8\">"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
							+ "    <title>Registered</title>"
							+ "    <style>"
							+ "        body {"
							+ "            font-family: Arial, sans-serif;"
							+ "            background-color: #f4f4f4;"
							+ "            margin: 0;"
							+ "            padding: 0;"
							+ "            display: flex;"
							+ "            justify-content: center;"
							+ "            align-items: center;"
							+ "            height: 100vh;"
							+ "        }"
							+ ""
							+ "        .logout-container {"
							+ ""
							+ "            width: 40%;"
							+ "            text-align: center;"
							+ "            background-color: #fff;"
							+ "            padding: 20px;"
							+ "            border-radius: 8px;"
							+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
							+ "        }"
							+ ""
							+ "        h1 {"
							+ "            color: #007BFF;"
							+ "        }"
							+ ""
							+ "        p { "
							+ "            color: #555;"
							+ "        }"
							+ ""
							+ "        a {"
							+ "            color: #007BFF;"
							+ "            text-decoration: none;"
							+ "        }"
							+ ""
							+ "        a:hover {"
							+ "            text-decoration: underline;"
							+ "        }"
							+ ""
							+ "        footer{"
							+ "			text-align: center;"
							+ "			width: 100%;"
							+ "			background-color: rgb(84, 147, 255);"
							+ "			color: white;"
							+ "			font-size: medium;"
							+ "			position: fixed;"
							+ "			bottom: 0;"
							+ "		}"
							+ "    </style>"
							+ "</head>"
							+ ""
							+ "<body>"
							+ "    <div class=\"logout-container\">"
							+ "        <h1>Registration Successful!</h1>"
							+ "        <p>Congratulations! "+verifiedStudent.getFirstName()+" You have been allocated Admission in "+csd.getCourse(csd.getCourseAbbrv(verifiedStudent.getCourse_id())).getCourseName()+" <br> We are happy to have you in our institute </p>"
							+ "        <p>CDAC Office will be contacting you soon on "+verifiedStudent.getPhone()+".</p>"
							+ "        <p> <button type=\"button\" onclick=\"history.back()\">Go Back!</button> </p>"
							+ "    </div>"
							+ "    <footer>"
							+ "		<div>Designed by @Jayesh Murodiya</div>"
							+ "	</footer>"
							+ "</body>"
							+ ""
							+ "</html>"
							+ "");
				} catch (SQLException e) { 
					hs.setAttribute("error", e.getMessage());
				}
			}
			errMsg = (String)hs.getAttribute("error");
			if(errMsg!=null){
				hs.setAttribute("error", null);
				pw.print("<!DOCTYPE html>"
						+ "<html lang=\"en\">"
						+ ""
						+ "<head>"
						+ "    <meta charset=\"UTF-8\">"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
						+ "    <title>Error</title>"
						+ "    <style>"
						+ "        body {"
						+ "            font-family: Arial, sans-serif;"
						+ "            background-color: #f4f4f4;"
						+ "            margin: 0;"
						+ "            padding: 0;"
						+ "            display: flex;"
						+ "            justify-content: center;"
						+ "            align-items: center;"
						+ "            height: 100vh;"
						+ "        }"
						+ ""
						+ "        .logout-container {"
						+ ""
						+ "            width: 40%;"
						+ "            text-align: center;"
						+ "            background-color: #fff;"
						+ "            padding: 20px;"
						+ "            border-radius: 8px;"
						+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
						+ "        }"
						+ ""
						+ "        h1 {"
						+ "            color: #ff0000;"
						+ "            text-decoration: underline;"
						+ "        }"
						+ ""
						+ "        p,"
						+ "        div{"
						+ "            color: #555;"
						+ "        }"
						+ ""
						+ "        a {"
						+ "            color: #007BFF;"
						+ "            text-decoration: none;"
						+ "        }"
						+ ""
						+ "        a:hover {"
						+ "            text-decoration: underline;"
						+ "        }"
						+ ""
						+ "        footer {"
						+ "            text-align: center;"
						+ "            width: 100%;"
						+ "            background-color: rgb(84, 147, 255);"
						+ "            color: rgb(255, 255, 255);"
						+ "            font-size: medium;"
						+ "            position: fixed;"
						+ "            bottom: 0;"
						+ "        }"
						+ "    </style>"
						+ "</head>"
						+ ""
						+ "<body>"
						+ "    <div class=\"logout-container\">"
						+ "        <h1>Failed To Register!</h1>"
						+ "        <div style=\"font-weight: bolder;\">Reason: "+errMsg+" </div>"
						+ "        <div>Please Retry!.</div>"
						+ "        <p><button type=\"button\" onclick=\"history.back()\">Go Back</button></p>"
						+ "    </div>"
						+ "    <footer>"
						+ "        Designed by @Jayesh Murodiya"
						+ "    </footer>"
						+ "</body>"
						+ ""
						+ "</html>");
			}
			
		}
	}

}
