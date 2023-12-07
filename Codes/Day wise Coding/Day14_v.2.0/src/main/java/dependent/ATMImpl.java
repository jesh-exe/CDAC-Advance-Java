package dependent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dependency.CustomNotification;
import dependency.Transport;

/*
 * Component tag act as the normal bean tag with in parameter as id in the tag
 */

@Component("atm")
public class ATMImpl implements ATM{


	/*
	 * We use @AutoWired on Constructor, Field, or Setter level to tell that the below entity is
	 * used for Dependency Injection
	 * @AutoWired alone is not sufficient, we need to add the @Qualifier("") annotation to tell the 
	 * Dependency class unique id name
	 * Another way to use AutoWired and @Qualifier is to use 
	 * @Resource(name="") in place of above both
	 */
	
	@Autowired
	@Qualifier("http")
	private Transport myTransport;
	
	@Autowired
	@Qualifier("sms")
	private CustomNotification myNotification;

	public ATMImpl(){
		System.out.println("In constructor of : ATMImpl\n");
	}  
	
	public ATMImpl(Transport myTransport, CustomNotification myNotification) {
		System.out.println("In Parameterized Constructor of : ATMImpl\n");
		this.myTransport = myTransport;
		this.myNotification = myNotification;
	}


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
	 * @PostConstruct act as the init-method attribute of the bean tag
	 * @PreDestroy act as the destroy-method attribute of the bean tag
	 * 
	 */
	
	@PostConstruct
	public void start()
	{
		System.out.println("In Init");
	}
	
	@PreDestroy
	public void end()
	{
		System.out.println("In Destroy");
	}
	
	public static ATMImpl myFactory(Transport myTransport,CustomNotification myNotification)
	{
		System.out.println("In FACTORY method of : ATMImpl\n");
		return new ATMImpl(myTransport, myNotification);
	}
	
}
