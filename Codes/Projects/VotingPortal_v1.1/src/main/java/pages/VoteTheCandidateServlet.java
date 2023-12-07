package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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

@WebServlet("/voted_by_voter")

public class VoteTheCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In doPost of VoteTheCandidateServlet");
		
		HttpSession hs = request.getSession();
		
		CandidateDao cnd = (CandidateDaoImpl)hs.getAttribute("candidateDao");
		UserDao usd = (UserDaoImpl)hs.getAttribute("userDao");
		User currentUser = (User)hs.getAttribute("loggedInUser");

		int idOfVoter = Integer.parseInt(request.getParameter("candidate"));
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()) {
			if (!currentUser.isVoteStatus()) {
				cnd.voteForCandidate(idOfVoter);
				usd.updateVotingStatus(currentUser);
				response.sendRedirect("voting_done");
			}else
			{
				response.sendRedirect("voting_done_already");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
