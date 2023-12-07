package dependency;

public class SmsNotification implements CustomNotification{

	public SmsNotification() {
		System.out.println("In constructor of : SmsNotification!\n");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println("Sending Message by SMS...");
		
	}

}
