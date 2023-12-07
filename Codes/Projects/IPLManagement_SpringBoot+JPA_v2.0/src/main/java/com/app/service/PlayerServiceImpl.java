package com.app.service;

import java.time.LocalDate;
import java.time.Period;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.PlayerDao;
import com.app.dao.TeamDao;
import com.app.pojos.Player;
import com.app.pojos.Team;

@Service			//To tell the SC that this is the Service Layer and make a bean as required in Controller

@Transactional		//To Tell SC that this class contains Transaction and you need to handle it
					//We don't need to start a transaction or end it.... for hibernate...it is managed by SC
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao pyd;
	@Autowired
	private TeamDao tmd;
	
	public PlayerServiceImpl() {
		System.out.println("In Constructor of PlayerService");
	}
	
	@Override
	public String addPlayerToTeam(Player obj,String abbrv) {
		
		Team myTeam = tmd.findByAbbreviation(abbrv).orElseThrow();
		
		if((Period.between(obj.getDob(), LocalDate.now()).getYears())>myTeam.getMaxAge())
			return "Maximum Age to get in "+myTeam.getName()+" is "+myTeam.getMaxAge();
		if(obj.getBattingAvg() < myTeam.getMinBattingAvg())
			return "Minimum Batting Average for "+myTeam.getName()+" is "+myTeam.getMinBattingAvg();
		if(obj.getWicketsTaken() < myTeam.getMinWicketsTaken())
			return "Minimum Wickets to be taken for "+myTeam.getName()+" is "+myTeam.getMinWicketsTaken();
		
		return pyd.addPlayerToTeam(obj, myTeam.getId());

	}

}
