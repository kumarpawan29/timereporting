package com.timereporting.core.service;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("utilService")
public class UtilService {
	
	private static final Logger logger = LoggerFactory.getLogger(HoursService.class);
	
	@Value("${spring.users.hours-limit}")
    private int hoursLimit;
	
	public JSONArray getAllYears() {
		JSONArray jsonArray = new JSONArray();
		 Calendar calendar = Calendar.getInstance();
		 int year = calendar.get(Calendar.YEAR);
		 for(int i = 1991 ;i<=year; i++ ) {
			 jsonArray.add(i);
		 }
		return jsonArray;
	}
	
	public JSONArray getAllMonths() {
		JSONArray jsonArray = new JSONArray();
	    String[] months = new DateFormatSymbols().getMonths();
	    for (int i = 0; i < 12; i++) {
	      String month = months[i];
	      jsonArray.add(month);
	    }
	    return jsonArray;
	}
	
	public JSONArray getAllHours() {
		JSONArray jsonArray = new JSONArray();
	    for (int i = 0; i <= hoursLimit; i++) {
	      jsonArray.add(i);
	    }
	    return jsonArray;
	}
	
	public JSONArray getAllMinutes() {
		JSONArray jsonArray = new JSONArray();
	    for (int i = 0; i < 60; i++) {
	      jsonArray.add(i);
	    }
	    return jsonArray;
	}

}
