package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.StudentRequestDto;
import com.app.service.StudentService;

//Rest Controller is main annotation to start the rest API application
//Basically RESTAPI are used to handle only back end data.
//Rest talks with front end with the help of the JSON file which is automatically parsed by
//Java's tool names Jackson which does the MARSHALLING (SERIALIZATION) Java object --> JSON
//Similary it can do the reverse too.
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService stdService;

	public StudentController() {
		System.out.println("Student Controller Initiated!");
	}

	/*
	 * ResponseEntity is a special class which is used to send a status code as well
	 * as out body or object we want to send to the client. It is a good way of
	 * programming to tell the Front End about the status code according to the
	 * output
	 * 
	 * @RequestBody is used by Jackson to parse the incoming data from Front end in
	 * the form of JSON file and convert it to Java World Object, this process is
	 * called UNMARSHALLING or DESERIALIZATION
	 * 
	 * @Valid annotation is used to activate the VALIDATION tag we have provided in
	 * DTO Class
	 * 
	 * DTO (DATA TRANSFER OBJECT) Basically in GET or PUT or POST, client and server
	 * talks in REQUEST and RESPONSE, so when the client sends a request with data
	 * in JSON file, that data needs to be mapped with the Object World, we can
	 * directly map it with the Entities/POJOS class but for separation of
	 * Validation Tag and Hibernate Tag, we don't use that approach
	 * 
	 * What we do is we make a DTO class of both Request and Response for Client and
	 * Server where the incoming data attribute for particular request handling
	 * method are declare in RequestDto, and all the incoming values are stored in
	 * object of RequestDto
	 * 
	 * Similary after our final processing, we store the Response in the Object
	 * representation of ResponseDto and we declare the properties and attributes
	 * required to send to client in DTO
	 * 
	 * On success , we send the ResponseEntity Object which consists of 2 major
	 * parts 1.Object(ResponseDto) 2.HttpStatus(ENUM) --> Consists of Constant
	 * String with Code.
	 * 
	 * But in case a exception is caught, we need to make sure that client dont get
	 * the whole stack trace rather we would just send him the message with date and
	 * time of the exception/problem/error.
	 * 
	 * This is done in ApiResponse class which mainly has TIME,MESSAGE as parameters
	 * whenever exception is caught, we create a ApiResponse object with current
	 * time, and the exception message in it and send it to the client with
	 * HTTPStatus code as Error.
	 * 
	 * 
	 */
	@PostMapping("/addUsingDTO")
	public ResponseEntity<?> addStudent(@RequestBody @Valid StudentRequestDto requestDto) {
		System.out.println("In side Add Student");
		return new ResponseEntity<>(stdService.addStudent(requestDto), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getByName/{firstName}")
	public ResponseEntity<?> getStudentByName(@PathVariable String firstName) {
		return new ResponseEntity<>(stdService.getStudentByName(firstName), HttpStatus.OK);
	}

	@DeleteMapping("/del/{roll}")
	public ResponseEntity<?> deleteByRoll(@PathVariable Integer roll) {

		stdService.deleteStudentByRoll(roll);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
