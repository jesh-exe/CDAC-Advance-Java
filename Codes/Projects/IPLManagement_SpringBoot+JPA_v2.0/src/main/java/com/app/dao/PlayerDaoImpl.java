package com.app.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Player;
import com.app.pojos.Team;

@Repository
public class PlayerDaoImpl implements PlayerDao{

	@Autowired
	private EntityManager em;
	
	public PlayerDaoImpl() {
		System.out.println("In Constructor of PlayerDao");
	}
	
	@Override
	public String addPlayerToTeam(Player obj,int teamId) {
		System.out.println("in Add Player Dao");
		Team teamObj = em.createQuery("select t from Team t where t.id = :id",Team.class).setParameter("id", teamId).getSingleResult();
		teamObj.addPlayer(obj);
		em.persist(obj);
		return "success";
	}

}
