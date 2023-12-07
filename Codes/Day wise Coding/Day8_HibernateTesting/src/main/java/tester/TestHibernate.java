package tester;

import org.hibernate.SessionFactory;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar;

import dao.TempTableDao;
import dao.TempTableDaoImpl;
import pojos.TempTable;

import static utils.HibernateUtils.*;

import java.time.LocalDate;
import java.util.Scanner;


public class TestHibernate {

	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in);
				SessionFactory sf = getSessionInstance())		//Making a instance of Session Factory(Auto Closable)
		//After creating instance...it will create the table if we have specified in the xml file using mapping tag
		{
			System.out.println("Table Created! " + sf);
			TempTableDao ttd = new TempTableDaoImpl();
			System.out.println("Enter FirstName, LastName, Email, DOB");
			ttd.registerData(new TempTable(scanner.next(), scanner.next(), scanner.next(), LocalDate.parse(scanner.next()),true));
		}catch (RuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
