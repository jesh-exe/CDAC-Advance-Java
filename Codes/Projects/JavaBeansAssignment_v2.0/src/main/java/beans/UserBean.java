package beans;

import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;

import static utils.DBUtils.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
 /*
  * This is the bean class which is used in JSP
  * Class name always ends with Bean.
  */

public class UserBean {

	/*
	 * All the variables coming from the JSP should have the same name as the attribute name in
	 * HTML tags
	 * We need to make Setters to auto initialize the variables in the Bean class from the JSP.
	 * 
	 */
	UserDao usd;
	String firstName;
	String lastName;
	String email;
	String password;
	String dobDate;
	
	public UserBean() throws SQLException {
		establishConnection();
		usd = new UserDaoImpl();
	}
	
	public User authenticate() throws SQLException
	{
		System.out.println("Inside Authenticate of UserBean");
		User loggedUser = usd.validateUser(email, password);
		if(loggedUser==null)
			return null;
		return loggedUser;
		
	}
	

	public boolean addVoter() throws SQLException
	{
		if((Period.between(LocalDate.parse(dobDate), LocalDate.now()).getYears())<21)
			throw new SQLException("Age Limit is 21 Years!");
		return usd.registerNewVoter(new User(firstName, lastName, email, password, Date.valueOf(dobDate)));
	}
	
	public UserDao getUsd() {
		return usd;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDobDate() {
		return (dobDate);
	}
	
	
	public void setUsd(UserDao usd) {
		this.usd = usd;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDobDate(String dobDate) {
		this.dobDate = dobDate;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		System.out.println("Setting Email");
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("Setting Password");
		this.password = password;
	}

	
	
}
