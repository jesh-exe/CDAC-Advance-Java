package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Course;

import static utils.DBUtils.*;

public class CourseDaoImpl implements CourseDao{
	
	private Connection conn;
	
	public CourseDaoImpl() {
		System.out.println("CourseDao Created!");
		conn = getCn();
	}

	@Override
	public List<Course> getAllCourses() throws SQLException {

		try(PreparedStatement pst = conn.prepareStatement("select * from courses");
				ResultSet rst = pst.executeQuery())
		{
			List<Course> courseList = new ArrayList<Course>();
			while(rst.next())
			{
				Course obj = new Course(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getInt(4));
				courseList.add(obj);
			}			
			return courseList;
		}
	}

	@Override
	public List<String> getAllAbbrv() throws SQLException {
		try(PreparedStatement pst = conn.prepareStatement("select course_abbrv from courses");
				ResultSet rst = pst.executeQuery()
				)
		{
			List<String> abbrvList = new ArrayList<String>();
			while(rst.next())
			{
				abbrvList.add(rst.getString(1));
			}
			return abbrvList;
		}
	}
	
	public int getCourseId(String abbvr) throws SQLException
	{
		try(PreparedStatement pst = conn.prepareStatement("select course_id from courses where course_abbrv=?"))
		{
			System.out.println(abbvr);
			pst.setString(1, abbvr);
			try(ResultSet rst = pst.executeQuery())
			{
				rst.next();
				int n = rst.getInt(1);
				System.out.println(n);
				return n;
			}
		}
	}
	
	public String getCourseAbbrv(int courseID) throws SQLException
	{
		try(PreparedStatement pst = conn.prepareStatement("select course_abbrv from courses where course_id=?"))
		{
			pst.setInt(1, courseID);
			try(ResultSet rst = pst.executeQuery())
			{
				rst.next();
				return rst.getString(1);
			}
		}
	}
	
	public Course getCourse(String abbrv) throws SQLException
	{
		try(PreparedStatement pst = conn.prepareStatement("select * from courses where course_abbrv=?"))
		{
			pst.setString(1,abbrv);
			try(ResultSet rst = pst.executeQuery())
			{
				rst.next();
				return new Course(rst.getInt(1), rst.getString(2), rst.getString(3),rst.getInt(4));
			}
		}
	}

	@Override
	public void cleanUp() throws SQLException {
		if(conn!=null)
			conn.close();
		
	}
	
	

}
