package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.service.TeamService;

/*
 * In SpringBoot application with Hibernate support
 * We need to add few Properties in application.properties file under src/main/resources
 * To Configure the Database and Server and ViewResolver
 * 
 */

//First Call
//DispatcherServlet --> MainController as this class contain the root URI "/"

@Controller
public class MainController {
	
	/*
	 * As we Know ControllerLayer are dependent on Service Layer so we make a ref of
	 * Service Layer Interface, and write @AutoWired so that Dependency Injection
	 * will be performed by Spring Container automatically
	 */
	@Autowired
	private TeamService tms;		//Controller-DEPENDENT ON Service-DEPENDENCY

	public MainController() {
		System.out.println("In Team Controller");
	}
	
	//This GetMapping("/") is used to tell the SC that root call of Web Application will be
	//handled by this method
	@GetMapping("/")
	public String getAbbrvFromService(Model obj)
	{
		System.out.println("In Abbr Handler of Controller");
		List<String> abbrvList = tms.getAbbrvOfAllTeams();		//using Service Layer to get abbreviation of teams
		obj.addAttribute("abbrv", abbrvList);					//Using Model Map to add the Attribute to RequestScope
		return "/mainPage";									//Send Client to First Dynamic Page
	}
	
	
	
}
