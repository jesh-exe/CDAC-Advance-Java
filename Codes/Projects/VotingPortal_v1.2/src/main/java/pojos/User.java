package pojos;

import java.sql.Date;

public class User {

	int id;
	String firstName;
	String lastName;
	String email;
	String password;
	Date dobDate;
	boolean voteStatus;
	String role;
	public User(int id, String firstName, String lastName, String email, String password, Date dobDate,
			boolean voteStatus, String role)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dobDate = dobDate;
		this.voteStatus = voteStatus;
		this.role = role;
	}
	public User(String firstName, String lastName, String email, String password, Date dobDate)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dobDate = dobDate;
		this.voteStatus = false;
		this.role = "voter";
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Date getDobDate()
	{
		return dobDate;
	}
	public void setDobDate(Date dobDate)
	{
		this.dobDate = dobDate;
	}
	public boolean isVoteStatus()
	{
		return voteStatus;
	}
	public void setVoteStatus(boolean voteStatus)
	{
		this.voteStatus = voteStatus;
	}
	public String getRole()
	{
		return role;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return "ID: " + id + "</br>FirstName: " + firstName + "</br>Last Name: " + lastName + "</br>Email: " + email
				+ "</br>Date of Birth: " + dobDate + "</br>Voting Status: " + voteStatus + "</br>Role: " + role;
	}	
	
}
