package dao;

import pojos.TempTable;
import static utils.HibernateUtils.*;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class TempTableDaoImpl implements TempTableDao{

	@Override
	public void registerData(TempTable obj) {
		Session newSession = getSessionInstance().openSession();
		Transaction tx = newSession.beginTransaction();
		try {
			newSession.save(obj);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}finally {
			if(newSession!=null)
				newSession.close();
		}
	}

}
