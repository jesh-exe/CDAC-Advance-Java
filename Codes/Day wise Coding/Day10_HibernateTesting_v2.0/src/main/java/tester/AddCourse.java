package tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.CourseDao;
import dao.CourseDaoImpl;
import pojos.Course;

import static utils.HibernateUtils.getSessionInstance;

public class AddCourse {

	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in);
				SessionFactory sf = getSessionInstance())
		{
			CourseDao csd = new CourseDaoImpl();
			System.out.print("Enter Course Name, Fees: ");
			csd.addCourse(new Course(scanner.next(), scanner.nextDouble()));
		}
	}
	
}
