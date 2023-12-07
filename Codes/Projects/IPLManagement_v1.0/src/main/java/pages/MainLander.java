package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PlayerDao;
import dao.PlayerDaoImpl;
import dao.TeamDao;
import dao.TeamDaoImpl;

import static utils.DBUtils.*;


@WebServlet(value = "/index.html",loadOnStartup = 2)
public class MainLander extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamDao tmd;
	private PlayerDao pyd;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Inside the INIT of MainLander");
		try {
			openConnection();
			tmd = new TeamDaoImpl();
			pyd = new PlayerDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in the INIT of MainLander",e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Inside doGet");
		resp.setContentType("text/html");
		try(PrintWriter pw = resp.getWriter())
		{
			HttpSession hs = req.getSession();
			hs.setAttribute("teamDao", tmd);
			hs.setAttribute("playerDao", pyd);
			List<String> teamAbr = tmd.getTeamsAbbreviations();
			String abrString="";
			
			for(String str : teamAbr)
			{
				abrString += "<option value=\""+str+"\">"+str+"</option>";
			}
			
			pw.print("<!DOCTYPE html>"
					+ "<html lang=\"en\">"
					+ "<head>"
					+ "  <meta charset=\"UTF-8\">"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
					+ "  <title>IPL Team Management</title>"
					+ "  <style>"
					+ "    body {"
					+ "      margin: 0;"
					+ "      padding: 0;"
					+ "    font-family:  'Segoe UI';"
					+ "      background: #f2f2f2;"
					+ "      color: #000000;"
					+ "    }"
					+ ""
					+ "    .container {"
					+ "      /* width: 30%; */"
					+ "      max-width: 400px;"
					+ "      margin: 50px auto;"
					+ "      background: rgba(255, 255, 255, 0.8);"
					+ "      padding: 20px;"
					+ "      border-radius: 10px;"
					+ "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
					+ "      display: flex;"
					+ "      flex-direction: column;"
					+ "      justify-content: center;"
					+ "    }"
					+ ""
					+ "    h1 {"
					+ "      text-align: center;"
					+ "      color: #1976D2;"
					+ "    }"
					+ ""
					+ "    form {"
					+ "      display: grid;"
					+ "      gap: 15px;"
					+ "    }"
					+ ""
					+ "    label {"
					+ "      font-weight: bold;"
					+ "      font-size: small;"
					+ "    }"
					+ ""
					+ "    input, select {"
					+ "      width: 100%;"
					+ "      padding: 10px;"
					+ "      box-sizing: border-box;"
					+ "      border: 1px solid #ccc;"
					+ "      border-radius: 5px;"
					+ "    }"
					+ ""
					+ "    button {"
					+ "        width: 30%;"
					+ "      background: #1976D2;"
					+ "      color: #fff;"
					+ "      padding: 10px;"
					+ "      border: none;"
					+ "      border-radius: 5px;"
					+ "      cursor: pointer;"
					+ "    }"
					+ ""
					+ "    button:hover {"
					+ "      background: #1565C0;"
					+ "    }"
					+ ""
					+ "    #btn-cnt{"
					+ "        display: flex;"
					+ "        justify-content: center;"
					+ "    }"
					+ ""
					+ "    @media screen and (max-width: 600px) {"
					+ "      .container {"
					+ "        padding: 10px;"
					+ "        width: 80%;"
					+ "      }"
					+ "    }"
					+ "  </style>"
					+ "</head>"
					+ "<body>"
					+ ""
					+ "  <div class=\"container\">"
					+ "    <h1>IPL Team Management</h1>"
					+ ""
					+ "    <form action =\"authenticate\" method=\"post\">"
					+ "      <label for=\"team\">Select Team:</label>"
					+ "      <select id=\"team\" name=\"team\">"
					+		abrString
					+ "      </select>"
					+ ""
					+ "      <label for=\"name\">Player Name:</label>"
					+ "      <input type=\"text\" id=\"name\" name=\"name\" required>"
					+ ""
					+ "      <label for=\"dob\">Date of Birth:</label>"
					+ "      <input type=\"date\" id=\"dob\" name=\"dob\" required>"
					+ ""
					+ "      <label for=\"battingAvg\">Batting Average:</label>"
					+ "      <input type=\"number\" id=\"battingAvg\" name=\"battingAvg\" step=\"0.01\" min=\"0\" max=\"100\" required>"
					+ ""
					+ "      <label for=\"wickets\">Wickets Taken:</label>"
					+ "      <input type=\"number\" id=\"wickets\" name=\"wickets\" min=\"0\" required>"
					+ ""
					+ "      <div id=\"btn-cnt\">"
					+ "          <button type=\"submit\">Add Player</button>"
					+"          &nbsp&nbsp<button type=\"reset\">Clear</button>"
					+ "      </div>"
					+ "    </form>"
					+ "  </div>"
					+ ""
					+ "</body>"
					+ "</html>"
					+ "");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			tmd.cleanUp();
			pyd.cleanUp();
			closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}




}
