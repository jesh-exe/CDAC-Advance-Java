package beans;

import java.util.List;

import dao.TeamDao;
import dao.TeamDaoImpl;

public class TeamBean {
	
	TeamDao tmd = new TeamDaoImpl();

	public TeamBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getTeamAbbrv()
	{
		return tmd.getTeamsAbbreviations();
	}
	
}
