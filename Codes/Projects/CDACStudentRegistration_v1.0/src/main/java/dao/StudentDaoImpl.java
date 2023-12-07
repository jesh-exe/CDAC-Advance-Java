package dao;

import java.sql.*;
import pojos.Student;

import static utils.DBUtils.*;

public class StudentDaoImpl implements StudentDao{

	private Connection conn;
	
	public StudentDaoImpl() {
		System.out.println("Student Dao Created!");
		conn = getCn();
	}
	
	@Override
	public boolean addStudent(Student obj) throws SQLException {
		
		try(PreparedStatement pst = conn.prepareStatement("insert into students values(default,?,?,?,?,?,?,?)"))
		{
			pst.setString(1, obj.getFirstName());
			pst.setString(2, obj.getLastName());
			pst.setDate(3, obj.getDob());
			pst.setString(4, obj.getPhone());
			pst.setString(5, obj.getAddress());
			pst.setInt(6, obj.getRank());
			pst.setInt(7, obj.getCourse_id());
			int rowAffected = pst.executeUpdate();
			return rowAffected==1 ? true : false;
		}
		
	}

	@Override
	public void cleanUp() throws SQLException {
		if(conn!=null)
			conn.close();
	}

}
