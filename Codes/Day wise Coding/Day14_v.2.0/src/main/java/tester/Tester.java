package tester;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.MyConfiguration;

public class Tester {

	public static void main(String[] args) {
		/*
		 * This tag is to load the configuration class we have made
		 */
		try(AnnotationConfigApplicationContext apx = new AnnotationConfigApplicationContext(MyConfiguration.class))
		{
			apx.getBean("atm");
		}
	}
}
