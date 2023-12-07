package dao;

import java.sql.SQLException;
import java.util.List;

import customException.CandidateCustomException;
import pojos.Candidate;

public interface CandidateDao {

	void addCandidate(String name,String party) throws SQLException,CandidateCustomException;
	List<Candidate> getAllCandidates() throws SQLException;
	void voteForCandidate(int id) throws SQLException,CandidateCustomException;
	void cleanUpCandidateDao() throws SQLException;
}
