package pages;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
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
import pojos.Course;
import pojos.Student;


@WebServlet("/authenticate")
public class AuthenticateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession hs = request.getSession();
			
			CourseDao csd = (CourseDaoImpl)hs.getAttribute("course");
			String firstName = request.getParameter("fname");
			String lastName = request.getParameter("lname");
			String dob = request.getParameter("dob");
			String mobile = request.getParameter("mobile");
			String address = request.getParameter("addr");
			String courseAbbrv = request.getParameter("course");
			int courseID = csd.getCourseId(courseAbbrv);
			Course obj = csd.getCourse(courseAbbrv);
			int rank = Integer.parseInt(request.getParameter("ranks"));
			if(obj.getMinRank()<rank)
			{
				hs.setAttribute("error", ("You are not eligible for this course, Cut-off Rank : "+obj.getMinRank()));
			}
			
			Student stud = new Student(firstName, lastName, Date.valueOf(dob), mobile, address, rank, courseID);
			hs.setAttribute("verifiedStudent", stud);
			
			response.sendRedirect("add_or_not");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
