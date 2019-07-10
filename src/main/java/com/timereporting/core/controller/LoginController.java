package com.timereporting.core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.timereporting.core.model.Employee;
import com.timereporting.core.service.EmployeeService;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/")
    public String addData() {
        return "redirect:/timereporting/home";
    }
    
    @GetMapping("/login")
    public Object login(){
    	if(employeeService.isEmployeeLoggedIn()) {
    		return "redirect:/timereporting/home";
    	}else {
    		ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            return modelAndView;
    	}
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Employee employee = new Employee();
        modelAndView.addObject("employee", employee);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
	public ModelAndView createNewUser(@Valid Employee employee, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Employee userExists = employeeService.findEmployeeByEmail(employee.getEmail());
		
		if (userExists != null) { 
			bindingResult .rejectValue("email", "error.user",
					"There is already a user registered with the email provided"); 
			}
		 
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            employeeService.saveEmployee(employee);
            modelAndView.addObject("userName", employee.getName());
            modelAndView.addObject("message", "Your account has been successfully registered."
            		+ " Please login and submit your hours.");
            modelAndView.setViewName("thankyou");
        }
        return modelAndView;
    }

}
