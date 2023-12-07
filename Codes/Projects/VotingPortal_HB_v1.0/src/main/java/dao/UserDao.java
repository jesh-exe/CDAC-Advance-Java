package dao;

import pojos.User;

public interface UserDao {

	User validateUser(String email,String password);
	boolean checkVotingStatus(User loggedInUser);
	void updateVotingStatus(User loggedInUser);
	boolean registerNewVoter(User newVoter);
	
}
