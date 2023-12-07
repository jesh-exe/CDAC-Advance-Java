package com.app.dao;

import com.app.pojos.Player;
import com.app.pojos.Team;

public interface PlayerDao {

	String addPlayerToTeam(Player obj,int teamId);
	
}
