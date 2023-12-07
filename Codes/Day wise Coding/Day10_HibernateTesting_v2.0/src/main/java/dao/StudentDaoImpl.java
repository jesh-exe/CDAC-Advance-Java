package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static utils.HibernateUtils.getSessionInstance;

import pojos.Course;
import pojos.Student;

public class StudentDaoImpl implements StudentDao{

	@Override
	public String addStudent(Student obj, int courseID) {
		Session session = getSessionInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		String msg="Not Added!";
		try {
			Course cr = session.get(Course.class, courseID);
			if(cr!=null)
			{
				cr.addStudent(obj);
				session.persist(obj);
				msg = "Added!";
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
