package dependency;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("email")
//@Scope is used to declare the scope of the instance of the class
@Scope("prototype")
//@Lazy is used to decide the lazy-init
@Lazy(false)

public class EmailNotification implements CustomNotification{

	public EmailNotification() {
		// TODO Auto-generated constructor stub
		System.out.println("In constructor of : EmailNotification\n");
	}
	
	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println("Sending Message by Email...");
	}

}
