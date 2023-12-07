package dao;

import java.sql.SQLException;

import pojos.Student;

public interface StudentDao {
	
	boolean addStudent(Student obj) throws SQLException;
	void cleanUp() throws SQLException;

}
