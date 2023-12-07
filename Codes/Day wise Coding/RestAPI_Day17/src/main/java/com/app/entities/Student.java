package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok is a third party vendor which provides with annotation to reduce the code of class
 * such as getters,setter,contructors,toString
 * Lombok need to be added in the dependency of pom.xml
 * 
 * We can use few of the annotations and then we dont need to write the whole implementation of
 * the following annotations
 * 
 */

@Entity
@Table(name = "stud")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roll;
	@Column(unique = true)
	private String firstName;
	private String lastName;
	private String city;
	private String courseName;
	private LocalDate date;
	
}




