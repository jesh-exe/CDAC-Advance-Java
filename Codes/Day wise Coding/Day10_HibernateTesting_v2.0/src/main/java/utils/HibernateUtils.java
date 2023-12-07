package utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtils {
	
	private static SessionFactory session;
	//Making a singleton Instance of Session Factory
	static {
		session = new Configuration()
				.configure()			//Takes data from the hibernate config file we stored in resources
				.buildSessionFactory();	//Makes Session using all the mappings of data of out config file
		System.out.println("Session Initialization Done!");
	}
	
	public static SessionFactory getSessionInstance()
	{
		return session;
	}

}
