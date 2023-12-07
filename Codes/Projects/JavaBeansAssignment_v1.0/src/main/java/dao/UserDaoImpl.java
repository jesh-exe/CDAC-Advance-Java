package dao;

import java.sql.*;

import customException.UserCustomException;
import pojos.User;
import static utils.DBUtils.*;

public class UserDaoImpl implements UserDao {

	public Connection conn;

	public UserDaoImpl() throws SQLException
	{
		
		conn = getConnection();
	}

	@Override
	public User validateUser(String email, String password) throws SQLException
	{
		try (PreparedStatement pst = conn.prepareStatement("select * from users where email=? and password=?"))
		{
			pst.setString(1, email);
			pst.setString(2, password);
			try (ResultSet rst = pst.executeQuery())
			{
				if (rst.next())
					return new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
						Date.valueOf(rst.getString(6)), rst.getBoolean(7), rst.getString(8));
			}
			return null;
		}
	}
	
	@Override
	public boolean registerNewVoter(User newVoter) throws SQLException {
		try(PreparedStatement pst2 = conn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)")){
		pst2.setString(1, newVoter.getFirstName());
		pst2.setString(2, newVoter.getLastName());
		pst2.setString(3, newVoter.getEmail());			
		pst2.setString(4, newVoter.getPassword());
		pst2.setDate(5, newVoter.getDobDate());
		pst2.setBoolean(6, false);
		pst2.setString(7, "voter");
		int rowCount;
		try {
			rowCount = pst2.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Email ID Already Exists!");
		}
		return rowCount == 1 ? true : false;
		}
	}

	@Override
	public boolean checkVotingStatus(User loggedInUser)
	{
		return loggedInUser.isVoteStatus();
			
	}

	@Override
	public void updateVotingStatus(User loggedInUser) throws SQLException, UserCustomException
	{
		try (PreparedStatement pst = conn.prepareStatement("update users set status=1 where email=?"))
		{
			pst.setString(1, loggedInUser.getEmail());
			int rowsAffected = pst.executeUpdate();

			if (rowsAffected == 0) throw new UserCustomException("Cannot Update Voting Status");

			loggedInUser.setVoteStatus(true);
		}
	}
	
	public void cleanUpUserDao() throws SQLException
	{
		if(conn!=null)
			conn.close();
			
	}

}
