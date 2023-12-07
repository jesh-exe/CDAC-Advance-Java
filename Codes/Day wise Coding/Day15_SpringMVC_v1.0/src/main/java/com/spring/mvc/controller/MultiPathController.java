package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

/*
 * If we want to tell the other folder hierarchy rather than the view resolver have been told to
 * append and prepend (SubFolders must be children of the prefix of viewResolver)
 * 
 * Suppose we have given view Resolver to prepend : /WEB-INF/views
 * But we want to separate jsp file from other files, so we can create another folder
 * in views, suppose "test"
 * New hierarchy : /WEB-INF/views/test and this containes a jsp names "jesh.jsp"
 * So rather than writing everytime full name as /test/jesh in the Mappers
 * we can write a common Request Mapping at Class level so that we can handle JSP or other files
 * of TEST folder Separately in other Controller
 * 
 * Typical Example
 * 	|-WEB-INF
 * 		 |-views
 * 		  	 |-employee
 * 					|- employee related .jsp files
 * 			 |-department
 * 					| department related .jsp files
 * 
 */
@RequestMapping("/test")
public class MultiPathController {

	public MultiPathController() {
		System.out.println("In MultiPathController Contructor");
	}
	
	//We will just write the end name of whole URI pattern
	@GetMapping("/jesh")
	public String handleJesh(){
		System.out.println("In Handle Jesh Method!");
		return "/test/jesh";
	}
	
}
