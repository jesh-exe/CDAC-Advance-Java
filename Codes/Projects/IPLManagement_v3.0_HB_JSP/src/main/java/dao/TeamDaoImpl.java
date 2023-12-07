package dao;
import static utils.HibernateUtils.getFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Team;

public class TeamDaoImpl implements TeamDao {

	@Override
	public List<String> getTeamsAbbreviations() {
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<String> abbrvList = new ArrayList<String>();
		try {
			abbrvList = session.createQuery("select abbreviation from Team t", String.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return abbrvList;
	}

	@Override
	public Team getTeamDetails(String abbreviation) {
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Team obj = null;
		try {
			obj = session.createQuery("select t from Team t where t.abbreviation = :abbrv", Team.class).setParameter("abbrv", abbreviation).getSingleResult();
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return obj;
	}
	
}
