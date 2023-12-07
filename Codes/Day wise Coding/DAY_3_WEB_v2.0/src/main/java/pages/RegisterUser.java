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
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;


public class RegisterUser extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In DOPOST of Register User");

		rs.setContentType("text/html");
		try (PrintWriter pw = rs.getWriter()) {

			HttpSession hs = rq.getSession();
			UserDao userDao = (UserDaoImpl)hs.getAttribute("userDao");
			
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


}
