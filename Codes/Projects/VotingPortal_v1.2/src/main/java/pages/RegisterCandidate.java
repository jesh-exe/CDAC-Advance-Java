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

import customException.CandidateCustomException;
import dao.CandidateDao;
import dao.CandidateDaoImpl;


@WebServlet("/get_candy")
public class RegisterCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter())
		{
			HttpSession hs = request.getSession();
			CandidateDao cnd = (CandidateDaoImpl)hs.getAttribute("candidateDao");
			String name = request.getParameter("name");
			String party = request.getParameter("party");
			
			try {
				cnd.addCandidate(name, party);
				response.sendRedirect("admin");
			} catch (CandidateCustomException e) {
				pw.print("<h2>"+e.getMessage()+"</h2>");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
