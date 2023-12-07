package pages;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.establishConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDao;
import dao.CandidateDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;

@WebServlet(value = "/authenticate", initParams = {
		@WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/advjava?useSSL=false&allowPublicKeyRetrieval=true"),
		@WebInitParam(name = "username", value = "root"),
		@WebInitParam(name = "password", value = "root")
},loadOnStartup = 1)

public class LoginAuthenticate extends HttpServlet {

	private static final long serialVersionUID = 3224713911608771880L;

	private UserDao userDao;
	private CandidateDao candDao;

	/*
	 * In init we create the instance of Candidate Dao and Voter Dao and before that
	 * we call the establishConnection of DBUtils to create the connection. So that
	 * we don't need to create the instances for other servlets We will just create
	 * the HTTP Session and save them in the browser cache to Hold the Runtime data
	 * of the application.
	 * 
	 * For example we can use the logged in User in other Servlets by saving them in
	 * the Http Session.
	 * 
	 * 
	 */

	@Override
	public void init(ServletConfig sc) throws ServletException {
		System.out.println("In INIT of LoginAuthenticate!");
		try {
			establishConnection();
			candDao = new CandidateDaoImpl();
			userDao = new UserDaoImpl();
		} catch (SQLException e) {
			throw new ServletException("Error in INIT! ", e);
		}
	}

	/*
	 * Do Post method is used to get the HTML form data without actually showing the
	 * content in the URL it is mainly used to access the sensitive information from
	 * the forms
	 * 
	 */

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In DO-POST of LoginAuthenticate!");
		rs.setContentType("text/html");

		try (PrintWriter pw = rs.getWriter()) {

			String email = rq.getParameter("email"); // Request used to getParameter from HTML file using the value of
														// the Attribute
			String password = rq.getParameter("password");

			User obj = userDao.validateUser(email, password);

			HttpSession hs = rq.getSession(); // Creating the Http Session using Request
			hs.setAttribute("userDao", userDao); // Setting the Attribute to use in other servlets by giving
													// it a name and the Object we want to store in the Session
			hs.setAttribute("candidateDao", candDao);

			if (obj == null) {
				rs.sendRedirect("authentication_failed"); // Respose is given by using the sendRedirect method to open
															// another Servlet by the URL Pattern of that servlet
			} else {
				hs.setAttribute("loggedInUser", obj);
				if (obj.getRole().toUpperCase().equals("ADMIN")) {
					rs.sendRedirect("admin");
				} else {

					if (obj.isVoteStatus()) {
						rs.sendRedirect("voting_done_already");
					} else {
						rs.sendRedirect("voter_page");
					}
				}
			}

		} catch (Exception e) {
			throw new ServletException("Error in doPost! " + getClass(), e);
		}

	}

	@Override
	public void destroy() {
		System.out.println("In DESTROY of LoginAuthenticate!");
		try {
			userDao.cleanUpUserDao();
			candDao.cleanUpCandidateDao();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
