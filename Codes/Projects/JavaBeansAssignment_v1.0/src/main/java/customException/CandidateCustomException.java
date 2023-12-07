package customException;

public class CandidateCustomException extends Exception{

	private static final long serialVersionUID = -9162625557629774401L;

	public CandidateCustomException(String errMsg)
	{
		super(errMsg);
	}
	
}
