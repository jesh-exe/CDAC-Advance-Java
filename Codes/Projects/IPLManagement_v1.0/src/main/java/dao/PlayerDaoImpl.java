package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pojos.Player;
import static utils.DBUtils.*;

public class PlayerDaoImpl implements PlayerDao{

	private Connection conn;
	
	public PlayerDaoImpl() throws SQLException {
		conn = openConnection();
	}
	
	@Override
	public String addPlayerToTeam(Player newPlayer, int teamId) throws SQLException {
		
		try(PreparedStatement pst = conn.prepareStatement("insert into players values(default,?,?,?,?,?,?)"))
		{
			pst.setString(1, newPlayer.getFirstName());
			pst.setString(2, newPlayer.getLastName());
			pst.setDate(3, newPlayer.getDob());
			pst.setDouble(4, newPlayer.getBattingAvg());
			pst.setInt(5, newPlayer.getWicketsTaken());
			pst.setInt(6, teamId);
			int rowAffected = pst.executeUpdate();
			if(rowAffected!=1)
				return "FAIL";
			return "SUCCESS";
		}	
	}
	
	public void cleanUp() throws SQLException
	{
		if(conn!=null)
			conn.close();
	}

}
