package com.hrmportal.controller;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/*
 * @author : sakshilavalkar
 * This is a controller class which controls mappings for different pages.
 */

@Controller
@CrossOrigin(origins = "*")
@SessionAttributes({"loggedUser","employeesData"})
public class HRMPortalContoller {
	
	/*
	 * @param model
	 * This method handles "/" mapping
	 * @return ModelAndView
	 */
	@RequestMapping("/")
	public ModelAndView home(Model model) {
		if(model.containsAttribute("loggedUser")) {
			JSONArray employees = RestController.getEmployees();
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("employeesData", employees);
			return mv;
		}
		else {
			return new ModelAndView("login");
		}
	}
	
	/*
	 * This method handles "/login.htm" mapping
	 * @return String
	 */
	@RequestMapping("/login.htm")
	public String login() {
		return "login";
	}
	
	/*
	 * This method handles "/register.htm" mapping
	 * @return String
	 */
	@RequestMapping("/register.htm")
	public String registerPage() {
		return "register";
	}
}
