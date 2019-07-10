package com.timereporting.core.service;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.timereporting.core.model.Employee;
import com.timereporting.core.model.Hours;
import com.timereporting.core.model.TimeInformation;
import com.timereporting.core.repositroy.HoursRepository;

@Service("hoursService")
public class HoursService {
	
	private static final Logger logger = LoggerFactory.getLogger(HoursService.class);
	
	private HoursRepository hoursRepository;
	
	@Autowired
    private EmployeeService employeeService;
	
	@Autowired
	public HoursService(HoursRepository hoursRepository) {
		this.hoursRepository = hoursRepository;
	}
	
	public Hours saveHours(TimeInformation timeInformation) {
		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication(); String email =
		 * authentication.getName(); Employee userExists =
		 * userService.findEmployeeByEmail(email);
		 */
		
		Employee userExists = employeeService.getLoggedInEmployee();
		
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		
		double week1 = timeInformation.getHoursForWeek1() + (timeInformation.getMinutesForWeek1()/60.0);
		double week2 = timeInformation.getHoursForWeek2() + (timeInformation.getMinutesForWeek2()/60.0);
		double week3 = timeInformation.getHoursForWeek3() + (timeInformation.getMinutesForWeek3()/60.0);
		double week4 = timeInformation.getHoursForWeek4() + (timeInformation.getMinutesForWeek4()/60.0);
		double totalHours = week1 + week2 + week3 + week4;
		
        Hours hours = new Hours();
        hours.setReferencePk(userExists.getReference());
        hours.setYear(timeInformation.getYear());
        hours.setMonth(timeInformation.getMonth());
        hours.setSubmittingDate(timestamp);
        hours.setTotalhours(totalHours);
        hours.setWeek1(week1);
        hours.setWeek2(week2);
        hours.setWeek3(week3);
        hours.setWeek4(week4);
        
        return hoursRepository.save(hours);
    }

}
