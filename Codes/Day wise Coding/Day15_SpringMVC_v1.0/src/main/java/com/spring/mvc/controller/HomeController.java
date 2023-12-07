package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * Working of MVC(Model View Controller)
 * MVC consists of components
 * 
 * 1. Provided by SpringFramework 
 * 		a.DispatcherServlet -- (Declare in the web.xml file to start the DS on start up)
 * 		b.ViewResolver -- (Declared in spring-servlet.xml file with p:suffix and p:prefix attribute)
 * 		c.RequestMappingHandler
 * 
 * 2. Provided by Programmer
 * 		a.Controller
 * 			i.Spring Bean Layer
 * 		   ii.Service Layer
 * 		  iii.Dao Layer
 * 		   iv.POJO's
 * 
 * User is forwarded from each layer and each layer has a significance role.
 * Steps:
 * # 	Whenever user clicks or performs any action on page, action is mapped and sent to
 * 		to the DispatcherServlet.
 * #	Before that we need to make ensure that the URI pattern we are sending to should be handled in 
 * 		Controller Class by name.(Refer Controller Class for more information).
 * #	So The controller class contains @RequestMapping of every particular requests on method level
 * 		, all the requests are mapped in a HashMap using Key(URI) and Value(Fully Qualified Name + method name).
 * 		EXM : Key = "/hello" , Value = "com.app.controller.MainController.sayHello" 	
 * #	So the DispatcherServlet check the Key in RequestMappingHandlet and if exists, then FQN is returned
 * 		else null is returned.
 * #	Next step is to invoke the method of Fully Qual. Name taken from the map and taking the return type as
 * 		answer from the Method, it decides the next stop for the client.
 * #	Suppose output is "/welcome", it is then sent to ViewResolver... It will prepend and append the content
 * 		we told it in the spring-servlet.xml file (Write a tag to import the view Resolver)
 * 		EXM : "/welcome" as input, returned as "prefix + /welcome + suffix";
 * #	Call comes back to DS and then Dispatcher has got the path of new file to send the user to,
 * 		so DS will now Forward the Client to the given path and hence a proper MVC is performed
 * 
 *  
 */



//It is used to tell the SC that this the Controller class and will be having all the
//Annotations needed for RequestMapping
@Controller
public class HomeController {

	public HomeController() {
		System.out.println("In Constructor of HomeController");
	}
	
	
	//It is the tag used to handle all the /hello incoming calls from user
	//It is stored in the HashMap as Key and Value pair with Value = Fully
	//Qualified Name + method name (com.spring.mvc.controller.HomeController.handleHello)
	
	@RequestMapping("/hello")
	public String handleHello()
	{
		System.out.println("In Hello Method!");
		return "/welcome";
	}
	
	/*
	 * More Example of Handling Annotations
	 * GetMapping
	 * PostMapping
	 * RequestMapping
	 * DeleteMapping
	 * etc... 
	 */
	
	
}
