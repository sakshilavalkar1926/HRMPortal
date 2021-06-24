package com.hrmportal.controller;


import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hrmportal.dao.UserDAO;
import com.hrmportal.entity.Employee;
import com.hrmportal.entity.User;

/*
 * @author sakshilavalkar
 * This is a controller class which handles user actions.
 */
@Controller
@CrossOrigin(origins = "*")
@SessionAttributes({"loggedUser","employeesData","message"})
public class UserController {

	@Autowired
	UserDAO userdao;
	
	/*
	 * @param User user
	 * This method handles post mapping for "/login"
	 * @return ModelAndView
	 */
	@PostMapping("/login")
	public ModelAndView loginUser(@ModelAttribute User user) {
		System.out.println("User is here "+user);
		if(userdao.loginUser(user)) {
			System.out.println(user+" login succesfully");
			JSONArray employees = RestController.getEmployees();
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("loggedUser", user);
			mv.addObject("employeesData", employees);
			return mv;
		}
		else {
			System.out.println("user does not exist");
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("message", "Username or Password is incorrect!");
			return mv;
		}
	}
	
	
	/*
	 * @param User user
	 * This method handles post mapping for "/register"
	 * @return ModelAndView
	 */
	@PostMapping("/register")
	public ModelAndView registerUser(@ModelAttribute User user) {
		
		System.out.println("User is registering "+user);
		if(userdao.createUser(user)) {
			JSONArray employees = RestController.getEmployees();
			ModelAndView mv = new ModelAndView("index");
			System.out.println(user+" register succesfully");
			mv.addObject("loggedUser", user);
			mv.addObject("employeesData", employees);
			return mv;
		}
		else {
			System.out.println("user cannot be registred!");
			ModelAndView mv = new ModelAndView("register");
			mv.addObject("message", "user cannot be registered, this username already exists!");
			return mv;
		}
	}
	
	/*
	 * @param Employee employee
	 * This method handles post mapping for "/update"
	 * @return String
	 */
	@PostMapping("/update")
	public String updateDetails(@ModelAttribute Employee employee) {
		RestController.updateEmployee(employee);
		return "redirect:/";
		
	}
	
	/*
	 * @param HttpServletRequest request
	 * This method handles post mapping for "/logout"
	 * @return String
	 */
	@PostMapping("/logout")
	public String logout(WebRequest request,SessionStatus status) {
		System.out.println("In logout");
//		System.out.println(request.getSession().getAttribute("loggedUser"));
//		request.getSession().removeAttribute("loggedUser");
//		request.getSession().removeAttribute("employeesData");
//		System.out.println("Still logged in user :-> "+request.getSession().getAttribute("loggedUser"));
		status.setComplete();
		request.removeAttribute("loggedUser",WebRequest.SCOPE_SESSION);
		request.removeAttribute("employeesData", WebRequest.SCOPE_SESSION);
		return "redirect:/";
		}
	
	/*
	 * @param MultipartFile file
	 * This method handles post mapping for "/upload"
	 * @return String
	 */
	@PostMapping(path="/upload", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public String upload(@RequestParam("file") MultipartFile file) {
		System.out.println(file);
		RestController.uploadCSV(file);
		return "redirect:/";
		
	}
}
