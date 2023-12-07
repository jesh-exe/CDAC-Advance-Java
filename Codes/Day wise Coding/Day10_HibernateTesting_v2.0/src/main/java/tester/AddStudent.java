package tester;

import static utils.HibernateUtils.getSessionInstance;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudentDao;
import dao.StudentDaoImpl;
import pojos.Student;

public class AddStudent {
		
			public static void main(String[] args) {
			try(Scanner scanner = new Scanner(System.in);
					SessionFactory sf = getSessionInstance())
			{
				StudentDao std = new StudentDaoImpl();
				System.out.print("Enter FirstName, LastName, Address, DOB and Course ID: ");
				System.out.println(std.addStudent(new Student(scanner.next(), scanner.next(), scanner.next(), LocalDate.parse(scanner.next())), scanner.nextInt()));
			}
	}

}
