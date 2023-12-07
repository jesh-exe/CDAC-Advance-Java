package com.spring.mvc.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/modelView")
public class ModelAndViewController {
		
	public ModelAndViewController()
	{
		System.out.println("In Contructor of ModelViewController");
	}
	
	@RequestMapping("/byReturnType")
	
	/*
	 * So as we know we make dynamic web application, and to make application dynamic
	 * we need to remember Data in further jsp to reflect the changes
	 * Example : after user logs in successfully , then we need to remember that user further till
	 * the session.... so we used to save data by HttpSesssion, request or other way by using
	 * setAttribute and getAttribute API
	 * But in Spring MVC we dont have session or request attribute under our control.... So 
	 * Spring Container has provided a ModeAndView class to return the viewFile name , attributeName
	 * and AttributeObject to be remembered by REQUEST as DS will forward the client....
	 * 
	 * We just simply return the object of ModelAndView...
	 * 
	 * But problem is, we can only save 1 attribute in ModelAndView Object.
	 * 
	 * So there is a better way -- refer (ModelAndViewBetterController.java)
	 * 
	 */
	public ModelAndView handleModelAndViewByReturnType()
	{
		LocalDateTime time = LocalDateTime.now();
		return new ModelAndView("/modelView/byReturnType", "time", time);
	}
	
}
