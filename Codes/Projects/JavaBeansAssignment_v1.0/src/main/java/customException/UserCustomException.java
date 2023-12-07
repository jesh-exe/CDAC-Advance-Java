package customException;

public class UserCustomException extends Exception{

	private static final long serialVersionUID = -1906055508762673952L;

	public UserCustomException(String errMsg)
	{
		super(errMsg);
	}
	
}
