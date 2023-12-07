package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependent.ATM;
import dependent.ATMImpl;

public class Tester {

	public static void main(String[] args) {
		/*
		 * 
		 * Here the ClassPathXmlApplicationContext is used to load the configuration file
		 * to the runtime path of StandAlone Application
		 * We use it to load configuration file for StandAlone Application
		 * 
		 * We use the getBean method to get the instance of the DEPENDENT class,So that
		 * we can use all the API,
		 * But before that we should note that our
		 * DEPENDENT CLASS  -> ATM is dependent on Transport and CustomNotifications subClasses
		 * DEPENDENCY CLASS -> HTTP,SOAP,TEST and EMAIL,SMS
		 * 
		 * We want to achieve loose coupling in Dependent Class, so that if we change the
		 * Dependency Class in future, we will not change the code in Dependent Class
		 * It should work for any instance or CHILD of Transport and Notification Interface
		 * We will just change the instance to be loaded in XML Configuration File
		 * 
		 * So to achieve Loose Coupling and Inversion of Control we use 3 Techniques : 
		 * 1. Setter Based Dependency Injection
		 * 2. Parameterized Constructor based Dependency Injection 
		 * 3. Factory Method Based Dependency Injection
		 * 
		 * 
		 */
		try (ClassPathXmlApplicationContext cntx = new ClassPathXmlApplicationContext("bean_config_file.xml")){
			
			System.out.println("By Setter Based DI");
			ATM atmSetterExample = cntx.getBean("atmSetter",ATMImpl.class);
			atmSetterExample.deposit(123);
			
			System.out.println("\n\nBy Parameterized Constructor Based DI");
			ATM atmParamConstructor = cntx.getBean("atmParamConstr", ATMImpl.class);
			atmParamConstructor.deposit(1);
			
			System.out.println("\n\nBy Factory Method Based DI");
			ATM atmFactoryMethod = cntx.getBean("atmFactory",ATMImpl.class);
			atmFactoryMethod.deposit(0);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
