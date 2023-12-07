package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customException.CandidateCustomException;
import pojos.Candidate;
import static utils.DBUtils.*;

public class CandidateDaoImpl implements CandidateDao {

	Connection conn;

	public CandidateDaoImpl() throws SQLException
	{
		conn = getConnection();
	}

	@Override
	public void addCandidate(String name, String party) throws SQLException, CandidateCustomException
	{
		
		try (PreparedStatement pst = conn.prepareStatement("insert into candidates (name,party,votes) values ( ? , ? , ? )"))
		{
			pst.setString(1, name);
			pst.setString(2, party);
			pst.setInt(3, 0);
			int rowAffected = pst.executeUpdate();

			if (rowAffected == 0) 
				throw new CandidateCustomException("Candidate not added!");
		}
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException
	{
		try(PreparedStatement pst = conn.prepareStatement("select * from candidates order by votes desc"))
		{
			List<Candidate> list = new ArrayList<Candidate>();
			ResultSet rst = pst.executeQuery();
			
			while(rst.next())
			{
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
			return list;
		}
	}

	@Override
	public void voteForCandidate(int id) throws SQLException,CandidateCustomException
	{
		try(PreparedStatement pst = conn.prepareStatement("update candidates set votes = votes+1 where id = ?"))
		{
			pst.setInt(1, id);
			int rowsAffected = pst.executeUpdate();
			if(rowsAffected==0)
				throw new CandidateCustomException("No such Candidate Found!");
		}
	}
	
	public void cleanUpCandidateDao() throws SQLException
	{
		if(conn!=null)
			conn.close();
	}

}
