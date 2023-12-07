package com.spring.mvc.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modelView")
public class ModelAndViewBetterController {

	public ModelAndViewBetterController() {
		System.out.println("In Contructor of Model and view Better Controller!");
	}

	
	@RequestMapping("byModelMap")
	/*
	 * It is a better way to setAttribute in Request Scope
	 * We need to add Model obj in the argument of out method.... when we add it
	 * The SC will provide us a Map of Attributes to be added in it...and will be EMPTY as it was
	 * Injected By Dependency at the Start of SC...
	 * 
	 * use the addAttribute API to add the attriute...same as request.setAttribute(name,val)
	 * 
	 * We can add n number of attributes...without returning the Map.....
	 * 
	 * Model Map will be implicitly returned to the DS..
	 */
	
	public String handleModelMap(Model obj)
	{
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		obj.addAttribute("date",date);
		obj.addAttribute("tem",time);
		return "/modelView/byModelMap";
	}
	
}

