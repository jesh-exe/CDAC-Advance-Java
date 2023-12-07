package dependent;

import dependency.CustomNotification;
import dependency.Transport;

public class ATMImpl implements ATM{

	/*
	 * Interfaces References...
	 */

	Transport myTransport;
	CustomNotification myNotification;

	public ATMImpl(){
		System.out.println("In constructor of : ATMImpl\n");
	}  

	/*
	 * This param const is used by Param Const Based DI from config file, it must be public
	 * But it will also be used by Factory Based DI and the constr must be private.
	 */
	
	public ATMImpl(Transport myTransport, CustomNotification myNotification) {
		System.out.println("In Parameterized Constructor of : ATMImpl\n");
		this.myTransport = myTransport;
		this.myNotification = myNotification;
	}

	/*
	 *
	 * We create setters for All the Dependencies we have created in out Dependent Class
	 * We call it from configuration file and send the reference of the Dependencies class we want to make.
	 *
	 */

	public void setMyTransport(Transport myTransport) {
		System.out.println("In Setter of : Transport "+getClass().getName());
		this.myTransport = myTransport;
	}

	public void setMyNotification(CustomNotification myNotification) {
		System.out.println("In Setter of : Notification "+getClass().getName());
		this.myNotification = myNotification;
	}

	@Override
	public void deposit(double amount) {
		System.out.println("Depositing Money!");
		myTransport.informBank(null);
		myNotification.sendMessage("");		
	}

	@Override
	public void withdraw(double amount) {
		System.out.println("Withdrawing Money");
		myTransport.informBank(null);
		myNotification.sendMessage("");
		
	}

	/*
	 * This is the factory method and has a pre defined method signature and returns a new Object from the constructor
	 * of the class, also we need to give the return type same as the class. 
	 */
	
	public static ATMImpl myFactory(Transport myTransport,CustomNotification myNotification)
	{
		System.out.println("In FACTORY method of : ATMImpl\n");
		return new ATMImpl(myTransport, myNotification);
	}
	
}
