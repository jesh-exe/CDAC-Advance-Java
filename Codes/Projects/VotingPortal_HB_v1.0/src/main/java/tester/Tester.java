package tester;

import org.hibernate.SessionFactory;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;

import static utils.HibernateUtils.*;
import java.util.Scanner;

public class Tester {
	
	public static void main(String[] args) {
		try (SessionFactory sf = getSessionFactory();
				Scanner scanner = new Scanner(System.in)){
			UserDao usd = new UserDaoImpl();
//			usd.registerNewVoter(new User("Jayesh", "Murodiya", "jrm@gmail.com", "jesh#123", LocalDate.parse("2001-01-24")));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
