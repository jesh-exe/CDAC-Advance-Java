package dao;

import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Player;
import pojos.Team;

public class PlayerDaoImpl implements PlayerDao{

	@Override
	public String addPlayerToTeam(Player newPlayer, int teamId) {
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		String msg = "failed";
		try {
			Team teamObj = session.get(Team.class, teamId);
			if(teamObj!=null)
			{
				teamObj.addPlayer(newPlayer);
				session.persist(newPlayer);
				msg = "success";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return msg;
	}	
	
}
