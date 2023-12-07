package com.app.dao;

import java.util.List;

import com.app.pojos.Team;

public interface TeamDao {

	List<String> getTeamsAbbreviations();
	Team getTeamIdByAbbrv(String abbrv);
	
}
