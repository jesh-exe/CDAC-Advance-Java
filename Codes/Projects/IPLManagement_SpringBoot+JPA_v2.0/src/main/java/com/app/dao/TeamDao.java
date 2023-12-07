package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Team;

//This is the example of Spring Data JPA Repository Use.....

/*
 *	We just need to extend the Dao Layer Interface by JpaRepository<T,ID>
 *	where T --> Class Type of Dao
 *		 ID --> ID type (Integer,String...)
 *
 *	We can run queries without even implementing the TeamDao interface anywhere
 *	There are 3 Major ways to use Spring Data JPA
 *	1. Using the API of JPARepository
 *	2.
 *	3. Using @Query
 *
 *
 */

public interface TeamDao extends JpaRepository<Team, Integer>{

	/*
	 * All we have to do is write the method name and 
	 * use @Query(value="jpql") to pass the Query you wanted to
	 * run in that method
	 * SC will automatically Do all the things and return you the result
	 * 
	 */
	
	@Query(value = "select t.abbreviation from Team t")
	List<String> getTeamsAbbreviations();
	
	/*
	 * There is much more simpler way to get a Team by any Attribute of it...
	 * But to do that there is specific Method Signature to be used
	 * 
	 * Method Return Type --> Optional<T>
	 * Method Name --> "findBy" at the front, followed by the Attribute name of your
	 * 					POJO class by which you want to access it with First Letter Capital.
	 * Method Argument --> Datatype of Attribute you want to fetch the Object from
	 * 
	 *  If you want to find Team by more than one attribute, just add And between attribute
	 *  name of the method name you will be writing , also you need to add the method argument
	 *  sequence wise.
	 */
	
	Optional<Team> findByAbbreviation(String abbrv);
	
	Optional<Team> findByNameAndOwner(String name, String Owner);

}
