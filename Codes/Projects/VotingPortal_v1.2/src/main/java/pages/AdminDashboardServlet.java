package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ant.jmx.JMXAccessorQueryTask;

import dao.CandidateDao;
import dao.CandidateDaoImpl;
import pojos.Candidate;
import pojos.User;

/**
 * Servlet implementation class AdminDashboardServlet
 */
@WebServlet("/admin")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter())
		{
			HttpSession hs = request.getSession();
			CandidateDao cnd = (CandidateDaoImpl)hs.getAttribute("candidateDao");
			List<Candidate> cndList= cnd.getAllCandidates();
			User user = (User)hs.getAttribute("loggedInUser");
			String candidate ="";
			int i=0;
			for(Candidate obj : cndList)
			{
				candidate +="<tr>"
						+ "<td>"+(++i)+"</td>"
						+ "<td>"+obj.getName().toUpperCase()+"</td>"
						+ "<td>"+obj.getParty().toUpperCase()+"</td>"
						+ "<td>"+obj.getVotes()+"</td>"
						+ "</tr>"; 
			}
			
			
			
			pw.print("<!DOCTYPE html>"
					+ "<html lang=\"en\">"
					+ ""
					+ "<head>"
					+ "    <meta charset=\"UTF-8\">"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
					+ "    <title>Admin Dashboard</title>"
					+ "    <style>"
					+ "        body {"
					+ "            font-family: Arial, sans-serif;"
					+ "            margin: 0;"
					+ "            padding: 0;"
					+ "            background-color: #f4f4f4;"
					+ "        }"
					+ ""
					+ "        .dashboard-container {"
					+ "            max-width: 800px;"
					+ "            margin: 20px auto;"
					+ "            background-color: #fff;"
					+ "            padding: 20px;"
					+ "            border-radius: 8px;"
					+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
					+ "        }"
					+ ""
					+ "        h1 {"
					+ "            color: #333;"
					+ "            text-align: center;"
					+ "        }"
					+ ""
					+ "        table {"
					+ "            width: 100%;"
					+ "            border-collapse: collapse;"
					+ "            margin-top: 20px;"
					+ "        }"
					+ ""
					+ "        th, td {"
					+ "            border: 1px solid #ddd;"
					+ "            padding: 10px;"
					+ "            text-align: left;"
					+ "        }"
					+ ""
					+ "        th {"
					+ "            background-color: #007BFF;"
					+ "            color: #fff;"
					+ "        }"
					+ ""
					+ "        tr:nth-child(even) {"
					+ "            background-color: #f2f2f2;"
					+ "        }"
					+ ""
					+ "        .action-buttons {"
					+ "            display: flex;"
					+ "            justify-content: space-between;"
					+ "            margin-top: 20px;"
					+ "        }"
					+ ""
					+ "        button {"
					+ "            background-color: #007BFF;"
					+ "            color: #fff;"
					+ "            border: none;"
					+ "            padding: 10px 20px;"
					+ "            cursor: pointer;"
					+ "            border-radius: 4px;"
					+ "        }"
					+ ""
					+ "        button.logout-btn {"
					+ "            background-color: #dc3545;"
					+ "        }"
					+ ""
					+ "        button:hover {"
					+ "            background-color: #0056b3;"
					+ "        }"
					+ ""
					+ "        @media (max-width: 600px) {"
					+ "            table {"
					+ "                font-size: 14px;"
					+ "            }"
					+ "        }"
					+ "    </style>"
					+ "</head>"
					+ ""
					+ "<body>"
					+ "    <div class=\"dashboard-container\">"
					+ "        <h1>Admin Dashboard</h1>"
					+ "        "
					+ "        <div style=\"text-align: right; color: rgb(77, 77, 77);\">Welcome, "+user.getFirstName()+" </div>"
					+ ""
					+ "        <table>"
					+ "            <thead>"
					+ "                <tr>"
					+ "                    <th>ID</th>"
					+ "                    <th>Name</th>"
					+ "                    <th>Party</th>"
					+ "                    <th>Votes</th>"
					+ "                </tr>"
					+ "            </thead>"
					+ "            <tbody>"
					+ candidate
					+ "            </tbody>"
					+ "        </table>"
					+ "        <div class=\"action-buttons\">"
					+ "            <a href=\"registerCandidatePage.html\"><button type=\"button\">Add Candidate</button></a>"
					+ "             <form action=\"exit\" method=\"post\">\r\n"
					+ "                <button class=\"logout-btn\" type=\"submit\">Logout</button>\r\n"
					+ "            </form>\r\n"
					+ "        </div>"
					+ "    </div>"
					+ "</body>"
					+ ""
					+ "</html>"
					+ "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
