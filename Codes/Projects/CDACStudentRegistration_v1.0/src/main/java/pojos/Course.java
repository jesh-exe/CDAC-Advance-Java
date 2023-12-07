package pojos;

public class Course {

	int courseID;
	String courseName;
	String courseAbbrv;
	int minRank;
	
	public Course(int courseID, String courseName, String courseAbbrv,int minRank) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseAbbrv = courseAbbrv;
		this.minRank = minRank;
	}

	public int getMinRank() {
		return minRank;
	}

	public void setMinRank(int minRank) {
		this.minRank = minRank;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseAbbrv() {
		return courseAbbrv;
	}

	public void setCourseAbbrv(String courseAbbrv) {
		this.courseAbbrv = courseAbbrv;
	}
	
	
	
}
