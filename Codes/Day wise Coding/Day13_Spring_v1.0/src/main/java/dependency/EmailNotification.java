package dependency;

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
