package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static utils.HibernateUtils.getSessionInstance;
import pojos.Course;

public class CourseDaoImpl implements CourseDao{

	@Override
	public String addCourse(Course obj) {
		Session session = getSessionInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(obj);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return "Added!";
	}

}
