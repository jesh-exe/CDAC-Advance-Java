package dao;

import org.hibernate.*;

import static utils.HibernateUtils.*;

import javax.persistence.NoResultException;

import pojos.User;

public class UserDaoImpl implements UserDao {
//Boiler Plate
//	Session session = getSessionFactory().getCurrentSession();
//	Transaction tx = session.beginTransaction();
//
//	try {
//		
//		tx.commit();
//	} catch (RuntimeException e) {
//		if(tx!=null)
//			tx.rollback();
//		throw e;
//	}
	private Session session;
	private Transaction tx;
	private String jpqlQuery;

	@Override
	public User validateUser(String email, String password) {
		session = getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		User obj = null;
		jpqlQuery = "select u from User u where u.email = :em and u.password = :pass";
		try {
			obj = 	session
					.createQuery(jpqlQuery, User.class)		//Used to create a sql query, takes the clas type it will return
					.setParameter("em", email)				//SETS the :param in the query
					.setParameter("pass", password)			
					.getSingleResult();						//To get only single return
			tx.commit();									//Saves the data in DB
		}catch (NoResultException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		} 
		catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return obj;
	}

	@Override
	public boolean registerNewVoter(User newVoter) {
		session = getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try {
			session.save(newVoter);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean checkVotingStatus(User loggedInUser) {

		return false;
	}

	@Override
	public void updateVotingStatus(User loggedInUser) {
		session = getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		try {
			User obj = session.get(User.class, loggedInUser.getId());
			obj.setStatus(true);
			loggedInUser.setStatus(true);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
	}

}
