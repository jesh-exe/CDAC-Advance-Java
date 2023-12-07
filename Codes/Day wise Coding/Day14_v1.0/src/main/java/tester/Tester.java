package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependent.ATM;
import dependent.ATMImpl;

public class Tester {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext cntx = new ClassPathXmlApplicationContext("bean_config.xml")){
			
			System.out.println("By Setter Based DI");
			ATM atmSetterExample = cntx.getBean("atm",ATMImpl.class);
			atmSetterExample.deposit(123);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
