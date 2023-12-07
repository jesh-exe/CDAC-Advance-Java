package dependency;

import org.springframework.stereotype.Component;


@Component("cash")
public class Cash {

	public Cash() {
		// TODO Auto-generated constructor stub
		System.out.println("ATM Machine Loaded with Cash!\n");
	}
	
}
