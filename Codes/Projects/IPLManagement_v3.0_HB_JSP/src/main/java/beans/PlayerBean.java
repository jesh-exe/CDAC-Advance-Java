package beans;

import java.time.LocalDate;
import java.time.Period;

import dao.PlayerDao;
import dao.PlayerDaoImpl;
import dao.TeamDao;
import dao.TeamDaoImpl;
import pojos.Player;
import pojos.Team;

public class PlayerBean {

	private String name;
	private String dob;
	private String battingAvg;
	private String wickets;
	private String team;
	private String errorMessage;

	public PlayerBean() {
		// TODO Auto-generated constructor stub
	}
	
	public PlayerBean(String name, String dob, String battingAvg, String wickets) {
		super();
		this.name = name;
		this.dob = dob;
		this.battingAvg = battingAvg;
		this.wickets = wickets;
	}
	
	public String authenticateAndAddPlayer()
	{
		TeamDao tmd = new TeamDaoImpl();
		Team myTeam= tmd.getTeamDetails(team);
		
		LocalDate dateOfBirth = LocalDate.parse(dob);
		double battingAverage = Double.parseDouble(battingAvg);
		int wicketsTaken = Integer.parseInt(wickets);
		
		if((Period.between(dateOfBirth, LocalDate.now()).getYears())>myTeam.getMaxAge())
		{
			errorMessage = "Maximum Age to get in "+myTeam.getName()+" is "+myTeam.getMaxAge();
			return "failed";
		}
		if(battingAverage<myTeam.getMinBattingAvg())
		{
			errorMessage = "Minimum Batting Average for "+myTeam.getName()+" is "+myTeam.getMinBattingAvg();
			return "failed";
		}
		if(wicketsTaken<myTeam.getMinWicketsTaken())
		{
			errorMessage = "Minimum Wickets to be taken for "+myTeam.getName()+" is "+myTeam.getMinWicketsTaken();
			return "failed";
		}
		
		PlayerDao playerDao = new PlayerDaoImpl();
		
		try {
			playerDao.addPlayerToTeam(new Player(name, dateOfBirth, battingAverage, wicketsTaken), myTeam.getId());			
		} catch (Exception e) {
			errorMessage = "Internal Errors!";
			return "failed";
		}
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBattingAvg() {
		return battingAvg;
	}

	public void setBattingAvg(String battingAvg) {
		this.battingAvg = battingAvg;
	}

	public String getWickets() {
		return wickets;
	}

	public void setWickets(String wickets) {
		this.wickets = wickets;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
		
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
