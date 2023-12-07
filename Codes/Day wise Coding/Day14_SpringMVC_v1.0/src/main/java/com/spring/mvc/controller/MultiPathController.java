package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class MultiPathController {

	public MultiPathController() {
		System.out.println("In MultiPathController Contructor");
	}
	
	@GetMapping("/jesh")
	public String handleJesh(){
		System.out.println("In Handle Jesh Method!");
		return "/test/jesh";
	}
	
}
