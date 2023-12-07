package dao;

import java.sql.SQLException;

import customException.UserCustomException;
import pojos.User;

public interface UserDao {

	User validateUser(String email,String password) throws SQLException;
	boolean checkVotingStatus(User loggedInUser);
	void updateVotingStatus(User loggedInUser) throws SQLException,UserCustomException;
	void cleanUpUserDao() throws SQLException;
	String registerNewVoter(User newVoter) throws SQLException;
}
