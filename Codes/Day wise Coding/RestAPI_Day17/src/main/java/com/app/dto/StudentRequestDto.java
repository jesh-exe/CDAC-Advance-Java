package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

/*
 * Here we can use Validation Tags to check the incoming data from user is following the 
 * @Annotations we have provided
 * 
 * We need to write @Valid in method argument where we are writing this class reference
 * 
 */

public class StudentRequestDto {
	@NotBlank(message = "Please Enter First Name")
	private String firstName;
	@NotBlank(message = "Please Enter Last Name")
	private String lastName;
	@NotBlank(message = "Please Enter City")
	private String city;
	@NotBlank(message = "Please Enter Course Name")
	private String courseName;//	@NotBlank
	@Past(message = "Please Enter Date of Birth")
	private LocalDate date;
}
