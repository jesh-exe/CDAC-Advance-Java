package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Course;

public interface CourseDao {
	
	List<Course> getAllCourses() throws SQLException;
	List<String> getAllAbbrv () throws SQLException;
	int getCourseId(String abbvr) throws SQLException;
	void cleanUp() throws SQLException;
	Course getCourse(String abbrv) throws SQLException;
	String getCourseAbbrv(int courseID) throws SQLException;


}
