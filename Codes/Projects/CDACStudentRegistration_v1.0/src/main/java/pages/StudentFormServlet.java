package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDao;
import dao.CourseDaoImpl;
import dao.StudentDao;
import dao.StudentDaoImpl;
import pojos.Course;

import static utils.DBUtils.*;

@WebServlet("/form")

public class StudentFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter())
		{
			System.out.println("Inside doGet MainLander");
			HttpSession hs = request.getSession();
			CourseDao csd = (CourseDaoImpl)hs.getAttribute("course");
			StudentDao std = (StudentDaoImpl)hs.getAttribute("student");
			
			String courseTag="";
			for(Course obj : csd.getAllCourses())
				courseTag += "<option value=\""+obj.getCourseAbbrv()+"\">"+obj.getCourseName()+"</option>";
			
			pw.print("<!DOCTYPE html>"
					+ "<html lang=\"en\">"
					+ ""
					+ "<head>"
					+ "  <meta charset=\"UTF-8\">"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
					+ "  <title>CDAC Addmission</title>"
					+ "  <style>"
					+ "    body {"
					+ "      margin: 0;"
					+ "      padding: 0;"
					+ "      font-family: 'Segoe UI';"
					+ "      background: #f2f2f2;"
					+ "      color: #000000;"
					+ "    }"
					+ ""
					+ "    .container {"
					+ "      max-width: 400px;"
					+ "      margin: 50px auto;"
					+ "      background: rgba(255, 255, 255, 0.8);"
					+ "      padding: 20px;"
					+ "      border-radius: 10px;"
					+ "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
					+ "      display: flex;"
					+ "      flex-direction: column;"
					+ "      justify-content: center;"
					+ "    }"
					+ ""
					+ "    h1 {"
					+ "      text-align: center;"
					+ "      color: #1976D2;"
					+ "    }"
					+ ""
					+ "    form {"
					+ "      display: grid;"
					+ "      gap: 15px;"
					+ "    }"
					+ ""
					+ "    label {"
					+ "      font-weight: bold;"
					+ "      font-size: small;"
					+ "    }"
					+ ""
					+ "    input,"
					+ "    select {"
					+ "      width: 100%;"
					+ "      padding: 10px;"
					+ "      box-sizing: border-box;"
					+ "      border: 1px solid #ccc;"
					+ "      border-radius: 5px;"
					+ "    }"
					+ ""
					+ "    button {"
					+ "      width: 30%;"
					+ "      background: #1976D2;"
					+ "      color: #fff;"
					+ "      padding: 10px;"
					+ "      border: none;"
					+ "      border-radius: 5px;"
					+ "      cursor: pointer;"
					+ "    }"
					+ ""
					+ "    button:hover {"
					+ "      background: #1565C0;"
					+ "    }"
					+ ""
					+ "    #btn-cnt {"
					+ "      display: flex;"
					+ "      justify-content: center;"
					+ "    }"
					+ ""
					+ "    @media screen and (max-width: 600px) {"
					+ "      .container {"
					+ "        padding: 10px;"
					+ "        width: 80%;"
					+ "      }"
					+ "    }"
					+ "    footer {"
					+ "        text-align: center;"
					+ "        width: 100%;"
					+ "        background-color: rgb(84, 147, 255);"
					+ "        color: rgb(255, 255, 255);"
					+ "        font-size: medium;"
					+ "        position: fixed;"
					+ "        bottom: 0;"
					+ "    }"
					+ "  </style>"
					+ "</head>"
					+ ""
					+ "<body>"
					+ ""
					+ "  <div class=\"container\">"
					+ "    <h1>CDAC Addmission Form</h1>"
					+ ""
					+ "    <form action=\"authenticate\" method=\"post\">"
					+ ""
					+ "      <label for=\"fname\">First Name</label>"
					+ "      <input type=\"text\" id=\"fname\" name=\"fname\" required>"
					+ ""
					+ "      <label for=\"lname\">Last Name</label>"
					+ "      <input type=\"text\" id=\"lname\" name=\"lname\" required>"
					+ ""
					+ "      <label for=\"dob\">Date of Birth:</label>"
					+ "      <input type=\"date\" id=\"dob\" name=\"dob\" required>"
					+ ""
					+ "      <label for=\"mobile\">Mobile Number</label>"
					+ "      <input type=\"text\" id=\"mobileNumber\" name=\"mobile\" pattern=\"[1-9]{1}[0-9]{9}\" required>"
					+ ""
					+ "      <label for=\"addr\">Address</label>"
					+ "      <input type=\"text\" id=\"address\" name=\"addr\" required>"
					+ ""
					+ "      <label for=\"ranks\">CCAT Rank</label>"
					+ "      <input type=\"number\" id=\"rank\" name=\"ranks\" min=\"1\" max=\"5000\" required>"
					+ ""
					+ "      <label for=\"course\">Select Course:</label>"
					+ "      <select id=\"course\" name=\"course\">"
					+ courseTag
					+ "      </select>"
					+ ""
					+ "      <div id=\"btn-cnt\">"
					+ "        <button type=\"submit\">Add Student</button>"
					+ "        &nbsp&nbsp<button type=\"reset\">Clear</button>"
					+ "      </div>"
					+ "    </form>"
					+ "  </div>"
					+ "    <footer>"
					+ "        Designed by @Jayesh Murodiya"
					+ "    </footer>"
					+ ""
					+ "</body>"
					+ ""
					+ "</html>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
