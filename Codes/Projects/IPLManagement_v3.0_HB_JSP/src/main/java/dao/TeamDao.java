package dao;

import java.util.List;

import pojos.Team;

public interface TeamDao {
	
	List<String> getTeamsAbbreviations();
	Team getTeamDetails(String abbreviation);
}
