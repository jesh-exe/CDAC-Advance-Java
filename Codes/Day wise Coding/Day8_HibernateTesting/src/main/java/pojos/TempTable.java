package pojos;

import java.time.LocalDate;

import javax.persistence.*;

@Entity		//To tell the compiler that this class is the POJO class and make table of it if not already.
//But we need to add mapping tag in xml file, so that everytime we make SessionFactory instance...it will
//make the table in DB
public class TempTable {
	//Id tag is used to specify the Primary Key of the Table, always write the annotation above specifiv
	//column you want to apply on
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//this will make our id auto increment
	Integer id;
	@Column(name = "first_name",length = 40) //It gives a name to column and length gives size of the variable (default=255)
	String firstName;
	@Column(name = "last_name",length = 40)
	String lastName;
	@Column(name = "email_id",length = 100,unique = true)
	String email;
	LocalDate dob;
	boolean status;
	
	public TempTable() {
		// TODO Auto-generated constructor stub
	}
	
	public TempTable(String firstName, String lastName, String email, LocalDate dob, boolean status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TempTable [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", dob=" + dob + ", status=" + status + "]";
	}
	
	
	
	
}
