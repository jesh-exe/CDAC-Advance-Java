package pages;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.openConnection;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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


@WebServlet(value = "/land-page", loadOnStartup = 1, initParams = {
		@WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/advjava?useSSL=false&allowPublicKeyRetrieval=true"),
		@WebInitParam(name = "userName", value = "root"),
		@WebInitParam(name = "password", value = "root")
		})

public class LandingPage extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	private CourseDao csd;
	private StudentDao std;

	@Override
	public void init() throws ServletException {
		System.out.println("Inside INIT of LandingPage");
		try {
			openConnection();
			csd = new CourseDaoImpl();
			std = new StudentDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in INIT of MAIN Lander",e);
		}
	}

	@Override
	public void destroy() {
		System.out.println("Inside DESTROY of LandingPage");
		try {
			std.cleanUp();
			csd.cleanUp();
			closeConnection();
		} catch (Exception e) {
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet of LandingPage");
		HttpSession hs = request.getSession();
		hs.setAttribute("course", csd);
		hs.setAttribute("student", std);
		response.sendRedirect("form");
	}

}
