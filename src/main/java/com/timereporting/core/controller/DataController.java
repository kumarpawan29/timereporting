package com.timereporting.core.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timereporting.core.model.Employee;
import com.timereporting.core.model.Hours;
import com.timereporting.core.model.LoadMonthYearRequest;
import com.timereporting.core.model.TimeInformation;
import com.timereporting.core.repositroy.HoursRepository;
import com.timereporting.core.scheduler.ReportScheduler;
import com.timereporting.core.service.EmployeeService;
import com.timereporting.core.service.HoursService;
import com.timereporting.core.service.UtilService;

@Controller
public class DataController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportScheduler.class);
	
	@Autowired
	private HoursService hoursService;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private HoursRepository hoursRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/timereporting/api/addtime")
	public @ResponseBody String getSearchResultViaAjax(
		@Valid @RequestBody TimeInformation timeInformation, Errors errors) {
		hoursService.saveHours(timeInformation);
		
		return "";
	}	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/timereporting/api/loadmonthyear")
	public @ResponseBody  JSONObject loadMonthYear(
			@RequestBody LoadMonthYearRequest loadMonthYearRequest) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("years", utilService.getAllYears());
		jsonObject.put("months",utilService.getAllMonths());
		jsonObject.put("hours", utilService.getAllHours());
		jsonObject.put("minutes",utilService.getAllMinutes());
		
		return jsonObject;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/timereporting/api/getexistinghours")
	public @ResponseBody  JSONObject getExistingHours(
			@RequestBody LoadMonthYearRequest loadMonthYearRequest) {
		String year = loadMonthYearRequest.getYear();
		String month = loadMonthYearRequest.getMonth();

		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication(); String email =
		 * authentication.getName(); Employee userExists =
		 * employeeService.findEmployeeByEmail(email);
		 */
		Employee userExists = employeeService.getLoggedInEmployee();
		
		List<Hours> hoursList = hoursRepository.findByReferencePk(userExists.getReference());
		Iterator<Hours> hoursListIterator = hoursList.iterator();
		JSONObject jsonObject = new JSONObject();
		boolean isDataFound = false;
		while(hoursListIterator.hasNext()) {
			Hours hours = hoursListIterator.next();
			
			if(year.equalsIgnoreCase(hours.getYear()) && month.equalsIgnoreCase(hours.getMonth())) {
				jsonObject.put("success","true");
				jsonObject.put("status", "The hours are already reported for "
						+ "year : "+year+" and month : "+ month +"!");

				jsonObject.put("hoursForWeek1",getHours(hours.getWeek1()));
				jsonObject.put("minutesForWeek1",getMinutes(hours.getWeek1()));
				
				jsonObject.put("hoursForWeek2",getHours(hours.getWeek2()));
				jsonObject.put("minutesForWeek2",getMinutes(hours.getWeek2()));
				
				jsonObject.put("hoursForWeek3",getHours(hours.getWeek3()));
				jsonObject.put("minutesForWeek3",getMinutes(hours.getWeek3()));
				
				jsonObject.put("hoursForWeek4",getHours(hours.getWeek4()));
				jsonObject.put("minutesForWeek4",getMinutes(hours.getWeek4()));
				
				isDataFound = true;
				
			}
		}
		if(!isDataFound) {
			jsonObject.put("success","false");
			jsonObject.put("status", "No hours are reported for "
					+ "year : "+year+" and month : "+ month +"!");
			
			jsonObject.put("hoursForWeek1",0);
			jsonObject.put("minutesForWeek1",0);
			
			jsonObject.put("hoursForWeek2",0);
			jsonObject.put("minutesForWeek2",0);
			
			jsonObject.put("hoursForWeek3",0);
			jsonObject.put("minutesForWeek3",0);
			
			jsonObject.put("hoursForWeek4",0);
			jsonObject.put("minutesForWeek4",0);
		}
		return jsonObject;
	}
	
	private int getHours(double hours) {
		return (int)(hours*60)/60;
	}
	
	private int getMinutes(double hours) {
		return (int)(hours*60)%60;
	}
}
