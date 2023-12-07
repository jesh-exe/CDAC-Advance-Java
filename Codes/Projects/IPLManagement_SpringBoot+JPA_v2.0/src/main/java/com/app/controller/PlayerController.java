package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Player;
import com.app.service.PlayerService;

@Controller
@RequestMapping("/player")			//Tell SC that all calls starting with /player will be handled here
public class PlayerController {

	@Autowired
    private	PlayerService pls;
	
	public PlayerController() {
		System.out.println("In Contructor of Player Controller");
	}
	
	@PostMapping("/add")
	/*
	 * @RequestParam followed by DataType and Variable name(same as attribute in jsp) fetches the Data
	 * from the form of jsp, Like from a form of Player Details
	 * 
	 * @DateTimeFormat is used to define the format as supported by LocalDate
	 * 
	 */
	
	/*
	 * Also we can use HttpSession by writing it in the argument of method and set the Attribute
	 */
	public String addThePlayerController(@RequestParam String team,@RequestParam String name,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob,
			@RequestParam double battingAvg, @RequestParam int wickets,Model attrMap,
		
			HttpSession session)
	{
		System.out.println("in Add Player Controller");
		Player obj = new Player(name, dob, battingAvg, wickets);
		String msg = pls.addPlayerToTeam(obj,team);
		if(!msg.equals("success"))
		{
			attrMap.addAttribute("errorMsg",msg);
			return "/player/failed";
		}
		
		session.setAttribute("playerTeam", team);

//		attrMap.addAttribute("playerTeam", team);
		
		return "/player/"+msg;
	}
	
}
