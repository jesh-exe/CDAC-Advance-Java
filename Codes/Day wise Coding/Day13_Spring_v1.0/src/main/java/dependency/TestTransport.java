package dependency;

public class TestTransport implements Transport {
	public TestTransport() {
		System.out.println("In constructor of : TestTransport\n");
	}

	@Override
	public void informBank(byte[] data) {
		System.out.println("informing bank using " + getClass().getName() + " layer");

	}

}
