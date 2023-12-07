package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;

@WebServlet(value = "/signup", loadOnStartup = 2)

public class RegisterUser extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("In INIT of RegisterUser");
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in INIT of Register User" + getClass(), e);
		}

	}

	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In doPost of Register User");

		rs.setContentType("text/html");
		try (PrintWriter pw = rs.getWriter()) {

			if (Period.between(LocalDate.parse(rq.getParameter("dob")), LocalDate.now()).getYears() < 21) {
				pw.print("You must me be above age 21!");
			} else {
				User newUser = new User(rq.getParameter("fname"), rq.getParameter("lname"), rq.getParameter("em"),
						rq.getParameter("pass"), Date.valueOf(rq.getParameter("dob")));

				try {
					pw.print(userDao.registerNewVoter(newUser));
				} catch (SQLException e) {
					pw.print(e.getMessage());
				}
			}

		}

	}

	public void destroy() {
		try {
			userDao.cleanUpUserDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
