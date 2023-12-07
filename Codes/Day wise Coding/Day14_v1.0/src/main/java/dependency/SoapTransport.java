package dependency;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("soap")
@Scope("prototype")
public class SoapTransport implements Transport {
	public SoapTransport() {
		System.out.println("In constructor of : SoapTransport\n");
	}

	@Override
	public void informBank(byte[] data) {
		System.out.println("informing bank using " + getClass().getName() + " layer");

	}

}
