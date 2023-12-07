package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course_tbl")
public class Course extends BaseEntity{

	@Column(name = "course_name")
	private String courseName;
	@Column(name = "course_fees")
	private double courseFees;
	@OneToMany(mappedBy = "myCourse")
	List<Student> students = new ArrayList<Student>();
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String courseName, double courseFees) {
		super();
		this.courseName = courseName;
		this.courseFees = courseFees;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + getId() +", courseName=" + courseName + ", courseFees=" + courseFees + "]";
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(double courseFees) {
		this.courseFees = courseFees;
	}

	public void removeStudent(Student obj) {
		obj.setMyCourse(null);
		students.remove(obj);
	}

	public void addStudent(Student obj) {
		obj.setMyCourse(this);
		students.add(obj);
	}
	
	
	
}
