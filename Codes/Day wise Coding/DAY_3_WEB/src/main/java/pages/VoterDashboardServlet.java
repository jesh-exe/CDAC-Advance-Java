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

import dao.CandidateDao;
import dao.CandidateDaoImpl;
import pojos.Candidate;


@WebServlet(value = "/voter_page",loadOnStartup = 3)
public class VoterDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CandidateDao cnd;
	
	@Override
	public void init() throws ServletException
	{
		System.out.println("In init of VoterDashboardServlet");
		try {
			cnd = new CandidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in INIT of "+getClass(),e);
		}
	}
	
	@Override
	public void destroy()
	{
		System.out.println("In destroy of VoterDashboardServlet");
		try {
			cnd.cleanUpCandidateDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet of VoterDashboardServlet");
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter())
		{			
			List<Candidate> listOfCandidates = cnd.getAllCandidates();
			String candidates = "";
			int i=0;
			for(Candidate obj : listOfCandidates)
			{
				i++;
				candidates += "<label>"
							+ "<input type=\"radio\" name=\"candidate\" value="+obj.getId()+">"
							+  obj.getName().toUpperCase()
							+ "</label>";
			}
			
			pw.print("<!DOCTYPE html>"
					+ "<html lang=\"en\">"
					+ ""
					+ "<head>"
					+ "    <meta charset=\"UTF-8\">"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
					+ "    <title>Voter Dashboard</title>"
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
					+ "        .dashboard-container {"
					+ "            width: 40%;"
					+ "            text-align: center;"
					+ "            background-color: #fff;"
					+ "            padding: 20px;"
					+ "            border-radius: 8px;"
					+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
					+ "        }"
					+ ""
					+ "        h1 {"
					+ "            color: #333;"
					+ "        }"
					+ ""
					+ "        .candidate-radio {"
					+ "            display: flex;"
					+ "            justify-content: center;"
					+ "            margin-top: 20px;"
					+ "        }"
					+ ""
					+ "        label {"
					+ "            padding: 10px;"
					+ "            color: #333;"
					+ "        }"
					+ ""
					+ "        button {"
					+ "            background-color: #007BFF;"
					+ "            color: #fff;"
					+ "            border: none;"
					+ "            padding: 10px 20px;"
					+ "            margin-top: 20px;"
					+ "            cursor: pointer;"
					+ "            border-radius: 4px;"
					+ "        }"
					+ ""
					+ "        button.logout-btn {"
					+ "            background-color: #dc3545;"
					+ "            margin-left: 10px;"
					+ "        }"
					+ ""
					+ "        button:hover {"
					+ "            background-color: #0056b3;"
					+ "        }"
					+ "    </style>"
					+ "</head>"
					+ ""
					+ "<body>"
					+ "    <div class=\"dashboard-container\">"
					+ "        <h1>Voter Dashboard</h1>"
					+ "        <p>Select your preferred candidate:</p>"
					+ ""
					+ "        <form action=\"voted_by_voter\" method=\"post\">"
					+ "            <div class=\"candidate-radio\">"
					+ candidates 
					+ "            </div>"
					+ "            "
					+ "            <button type=\"submit\">Vote</button>"
					+ "            <a href=\"logoutPage.html\"><button class=\"logout-btn\" type=\"button\">Logout</button></a>"
					+ "        </form>"
					+ "    </div>"
					+ "</body>"
					+ ""
					+ "</html>"
					+ "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
