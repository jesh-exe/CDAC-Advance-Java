package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TeamDao;

@Service			//To tell the SC that this is the Service Layer and make a bean as required in Controller

@Transactional		//To Tell SC that this class contains Transaction and you need to handle it
					//We don't need to start a transaction or end it.... for hibernate...it is managed by SC
public class TeamServiceImpl implements TeamService{

	/*
	 * As we know that Controller is Dependent on Service Layer..
	 * Similary ... Service Layer is Dependent upon Dao Layer
	 * 
	 *  Hence we make a reference of DAO Layer Interface and write @AutoWired so that
	 *  SC injects the dependency accordingly.
	 */
	@Autowired
	private TeamDao tmd;
	
	public TeamServiceImpl() {
		System.out.println("In Team Service Impl");
	}
	
	public List<String> getAbbrvOfAllTeams()
	{
		return tmd.getTeamsAbbreviations();
	}
	
}
