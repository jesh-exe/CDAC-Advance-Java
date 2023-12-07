package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;


@WebServlet("/signup")
public class RegisterUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In DOPOST of Register User");

		rs.setContentType("text/html");
		try (PrintWriter pw = rs.getWriter()) {
			
			UserDao userDao = new UserDaoImpl();
			
			if (Period.between(LocalDate.parse(rq.getParameter("dob")), LocalDate.now()).getYears() < 21) {
				pw.print("You must me be above age 21!");
			} else {
				User newUser = new User(rq.getParameter("fname"), rq.getParameter("lname"), rq.getParameter("em"),
						rq.getParameter("pass"), Date.valueOf(rq.getParameter("dob")));

				try {
					if(userDao.registerNewVoter(newUser))
					{
						pw.print("<!DOCTYPE html>"
								+ "<html lang=\"en\">"
								+ ""
								+ "<head>"
								+ "    <meta charset=\"UTF-8\">"
								+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
								+ "    <title>Register Done</title>"
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
								+ "        <h1>User Registered!</h1>"
								+ "        <p>You have successfully created account on Voting Portal.</p>"
								+ "        <p>You can now cast your vote!</p>"
								+ "        <p>Click here to go on <a href=\"loginPage.html\">Login Page</a></p>"
								+ "    </div>"
								+ "    <footer>"
								+ "		<div>Designed by @Jayesh Murodiya</div>"
								+ "	</footer>"
								+ "</body>"
								+ ""
								+ "</html>"
								+ "");
					}
				} catch (SQLException e) {
					pw.print("<h2>"+e.getMessage()+"</h2>");
				}
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}


}
