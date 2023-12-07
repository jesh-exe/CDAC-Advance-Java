package pojos;

import java.sql.Date;

public class Student {

	int id;
	String firstName;
	String lastName;
	Date dob;
	String phone;
	String address;
	int rank;
	int courseID;
	public Student(String firstName, String lastName, Date dob, String phone, String address, int rank,
			int course_id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.rank = rank;
		this.courseID = course_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getCourse_id() {
		return courseID;
	}
	public void setCourse_id(int course_id) {
		this.courseID = course_id;
	}
	
	
	
	
}
