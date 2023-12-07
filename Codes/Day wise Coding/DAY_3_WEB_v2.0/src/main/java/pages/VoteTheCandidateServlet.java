package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDao;
import dao.CandidateDaoImpl;
import dao.UserDaoImpl;

import static pages.LoginAuthenticate.loggedInUser;

@WebServlet("/voted_by_voter")

public class VoteTheCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDao cnd;
	private UserDaoImpl usd;

	@Override
	public void init() throws ServletException {
		System.out.println("In init of VoteTheCandidateServlet");

		try {
			usd = new UserDaoImpl();
			cnd = new CandidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in INIT of " + getClass(), e);
		}
	}

	@Override
	public void destroy() {
		System.out.println("In destroy of VoteTheCandidateServlet");
		try {
			cnd.cleanUpCandidateDao();
			usd.cleanUpUserDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doPost of VoteTheCandidateServlet");

		int idOfVoter = Integer.parseInt(request.getParameter("candidate"));
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			if (loggedInUser!=null && !loggedInUser.isVoteStatus()) {
				cnd.voteForCandidate(idOfVoter);
				usd.updateVotingStatus(loggedInUser);
				response.sendRedirect("voting_done");
			}else
			{
				response.sendRedirect("voting_done_already");
			}
			loggedInUser = null;
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
