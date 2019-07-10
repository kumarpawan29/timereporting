package com.timereporting.core.controller;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.timereporting.core.model.Employee;
import com.timereporting.core.model.Hours;
import com.timereporting.core.repositroy.HoursRepository;
import com.timereporting.core.service.EmployeeService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private HoursRepository hoursRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/timereporting/home")
    public ModelAndView home(){
		
		List<String> list = new ArrayList<>();
	    String[] months = new DateFormatSymbols().getMonths();
	    for (int i = 0; i < 12; i++) {
	      String month = months[i];
	      list.add(month);
	    }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
	
	@GetMapping("/timereporting/add")
    public ModelAndView dashboard(){
		
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

	@GetMapping("/timereporting/status")
    public ModelAndView status(){
		
		Employee employee = employeeService.getLoggedInEmployee();
		
		List<Hours> hoursList = hoursRepository.findByReferencePk(employee.getReference());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", employee.getName());
        modelAndView.addObject("hoursList", hoursList);
        modelAndView.setViewName("status");
        return modelAndView;
    }

}
