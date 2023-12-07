package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Team;

@Repository		//To tell SC that it is our DAO Layer Class
public class TeamDaoImpl implements TeamDao{

	/*
	 * Similarly DAO Layer is dependent upon SessionFactory to manage POJO's
	 * But SessionFactory is the part of Hibernate Vendor.... SpringBoot doesn't
	 * support lower hierarchy.... we need to climb up the ladder to the top which is
	 * JPA (Java Persistance API) which has a SessionFactory attribute named as
	 * EntityManager.... 
	 * 
	 * So Now DAO is DEPENDENT upon EntityManager..
	 */
	@Autowired
	private EntityManager em;
	
	public TeamDaoImpl() {
		System.out.println("In Team Dao Impl");
	}

	@Override
	public List<String> getTeamsAbbreviations() {
		System.out.println("in getAbbrv of Team Dao");
		String jpql = "select t.abbreviation from Team t";
		
		//We don't need to createSession or OpenSession... managed my EntityManager itself
		//Neither we need to start a TRANSACTIOn or close it...Handled By Service Layer bcoz of @Transactional
		
		List<String> abbrvList = em.createQuery(jpql, String.class).getResultList();
		return abbrvList;
	}

	@Override
	public Team getTeamIdByAbbrv(String abbrv) {
		System.out.println("in get Team Id of Team Dao");
		Team tobj = em.createQuery("select t from Team t where t.abbreviation = :ab",Team.class).setParameter("ab", abbrv).getSingleResult();
		return tobj;
	}
	
}
