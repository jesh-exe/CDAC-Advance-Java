package pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stud_tbl")
public class Student extends BaseEntity{

	@Column(name = "fname",length = 40)
	private String firstName;
	@Column(name = "lname",length = 40)
	private String lastName;
	@Column(name = "addr",length = 40)
	private String address;
	private LocalDate dob;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course myCourse;
	
	public Student() {

	}

	public Student(String firstName, String lastName, String address, LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Course getMyCourse() {
		return myCourse;
	}

	public void setMyCourse(Course myCourse) {
		this.myCourse = myCourse;
	}

	@Override
	public String toString() {
		return "Student [ "+"id="+getId()+", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", dob=" + dob
				+ ", myCourse=" + myCourse + "]";
	}
	
	
	
}
