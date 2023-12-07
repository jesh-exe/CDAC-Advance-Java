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
import javax.servlet.http.HttpSession;

import customException.IPLException;
import dao.PlayerDao;
import dao.PlayerDaoImpl;
import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Player;
import pojos.Team;

/**
 * Servlet implementation class AuthenticateIPLServlet
 */
@WebServlet("/authenticate")
public class AuthenticateIPLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter pw = response.getWriter()) {
			try {
				HttpSession hs = request.getSession();
				TeamDao tmd = (TeamDaoImpl) hs.getAttribute("teamDao");
				PlayerDao pyd = (PlayerDaoImpl) hs.getAttribute("playerDao");

				String abrv = request.getParameter("team");
				Team team = tmd.getTeamDetails(abrv);
				Player player = new Player();

				String[] name = request.getParameter("name").split(" ");
				player.setFirstName(name[0]);
				player.setLastName(name[1]);

				String dob = request.getParameter("dob");
				if (Period.between(LocalDate.parse(dob), LocalDate.now()).getYears() > team.getMaxAge())
					throw new IPLException(
							"Age Limit Not Satisfied for the Team should be less than " + team.getMaxAge());
				player.setDob(Date.valueOf(dob));

				double batAvg = Double.parseDouble(request.getParameter("battingAvg"));
				if (batAvg < team.getMinBattingAvg())
					throw new IPLException("Batting Average must be above " + team.getMinBattingAvg());
				player.setBattingAvg(batAvg);

				int minWckt = Integer.parseInt(request.getParameter("wickets"));
				if (minWckt < team.getMinWicketsTaken())
					throw new IPLException("Minimum Wickets Taken must be " + team.getMinWicketsTaken());
				player.setWicketsTaken(minWckt);
				player.setTeamId(team.getTeamId());
				pyd.addPlayerToTeam(player, team.getTeamId());
				response.sendRedirect("authentication_done");

			} catch (IPLException e) {
				pw.print("<!DOCTYPE html>"
						+ "<html lang=\"en\">"
						+ ""
						+ "<head>"
						+ "    <meta charset=\"UTF-8\">"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
						+ "    <title>Error</title>"
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
						+ "            color: #ff0000;"
						+ "            text-decoration: underline;"
						+ "        }"
						+ ""
						+ "        p,"
						+ "        div{"
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
						+ "        footer {"
						+ "            text-align: center;"
						+ "            width: 100%;"
						+ "            background-color: rgb(84, 147, 255);"
						+ "            color: rgb(255, 255, 255);"
						+ "            font-size: medium;"
						+ "            position: fixed;"
						+ "            bottom: 0;"
						+ "        }"
						+ "    </style>"
						+ "</head>"
						+ ""
						+ "<body>"
						+ "    <div class=\"logout-container\">"
						+ "        <h1>Failed To Register!</h1>"
						+ "        <div style=\"font-weight: bolder;\">Reason: "+e.getMessage()+" </div>"
						+ "        <div>Please Retry!.</div>"
						+ "        <p><button type=\"button\" onclick=\"history.back()\">Go Back</button></p>"
						+ "    </div>"
						+ "    <footer>"
						+ "        Designed by @Jayesh Murodiya"
						+ "    </footer>"
						+ "</body>"
						+ ""
						+ "</html>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
