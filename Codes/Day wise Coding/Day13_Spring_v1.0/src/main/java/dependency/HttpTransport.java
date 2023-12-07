package dependency;

public class HttpTransport implements Transport {
	public HttpTransport() {
		System.out.println("In constructor of : HttpTransport\n");
	}

	@Override
	public void informBank(byte[] data) {
		System.out.println("informing bank using " + getClass().getName() + " layer");

	}

}
