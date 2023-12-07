package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;


@WebServlet(value="/authenticate",loadOnStartup = 1)

public class LoginAuthenticate extends HttpServlet {
	
	public static User loggedInUser;
	private static final long serialVersionUID = 3224713911608771880L;
	UserDao userDao;

	@Override
	public void init(ServletConfig sc) throws ServletException
	{
		System.out.println("In INIT of LoginAuthenticate!");
		try {
			userDao = new UserDaoImpl();
		} catch (SQLException e) {
			throw new ServletException("Error in INIT! ",e);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException
	{
		System.out.println("In DO-POST of LoginAuthenticate!");
		rs.setContentType("text/html");
		
		try(PrintWriter pw = rs.getWriter())
		{
			String email = rq.getParameter("email");
			String password = rq.getParameter("password");
			
			User obj = userDao.validateUser(email, password);
			
			if(obj==null)
			{
				rs.sendRedirect("authentication_failed");
			}
			else
			{
				if(obj.isVoteStatus())
				{
					rs.sendRedirect("voting_done_already");
				}
				else {
					loggedInUser = obj;
					rs.sendRedirect("voter_page");					
				}
			}
			
		}catch (Exception e) {
			throw new ServletException("Error in doPost! "+getClass(),e);
		}
		
	}
	
	@Override
	public void destroy()
	{
		System.out.println("In DESTROY of LoginAuthenticate!");
		try {
			userDao.cleanUpUserDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
