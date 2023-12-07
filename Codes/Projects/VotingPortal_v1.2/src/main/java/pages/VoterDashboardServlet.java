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

import dao.CandidateDao;
import dao.CandidateDaoImpl;
import pojos.Candidate;
import pojos.User;


@WebServlet("/voter_page")
public class VoterDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doGet of VoterDashboardServlet");
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter())
		{			
			HttpSession hs = request.getSession();									//Creating the Session,if session already exists all the attributes will be accessible
			CandidateDao cnd = (CandidateDaoImpl)hs.getAttribute("candidateDao");	//Accessing the Attribute we saved previously in other servlet
			User currentUser = (User)hs.getAttribute("loggedInUser");				//We access it using getAttribute with parameter of the attribute name we have given earlier 

			
			List<Candidate> listOfCandidates = cnd.getAllCandidates();
			String candidates = "";
			for(Candidate obj : listOfCandidates)									//To add the candidate details dynamically Store the data in string with proper HTML
			{																		//and add it in between the HTML
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
					+		  	  "text-align: center;"
					+ "            width: 50%;"
					+ "            background-color: #fff;"
					+ "            padding: 20px;"
					+ "            border-radius: 8px;"
					+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
					+ "            display: flex;"
					+ "            flex-direction: column;"
					+ "            overflow: auto;"
					+ "            flex-wrap: wrap;"
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
					+ " 		   flex-grow: 1;"
					+ "            flex-wrap: wrap;"
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
					+ "        <h1>Voter Dashboard, Welcome "
					+ "</h1>"
					+ "        <p>Select your preferred candidate:</p>"
					+ ""
					+ "        <form action=\"voted_by_voter\" method=\"post\">"
					+ "            <div class=\"candidate-radio\">"
					+ candidates
					+ "            </div>"
					+ "            "
					+ "            <button type=\"submit\">Vote</button>"
					//We can write a double submit button with other action in it, using formaction , button type should be submit only
					+ "            <button class=\"logout-btn\" type=\"submit\" formaction=\"exit\">Logout</button>"
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
