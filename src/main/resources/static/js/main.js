 $(document).ready(function() {
	 
	 $("#addtime").click(function() {
		 
		 var year = $("#years").children("option:selected").val();
		 var month = $("#months").children("option:selected").val();
		 
		 var hoursForWeek1 = $("#hoursForWeek1").children("option:selected").val();
		 var minutesForWeek1 = $("#minutesForWeek1").children("option:selected").val();
		 var hoursForWeek2 = $("#hoursForWeek2").children("option:selected").val();
		 var minutesForWeek2 = $("#minutesForWeek2").children("option:selected").val();
		 var hoursForWeek3 = $("#hoursForWeek3").children("option:selected").val();
		 var minutesForWeek3 = $("#minutesForWeek3").children("option:selected").val();
		 var hoursForWeek4 = $("#hoursForWeek4").children("option:selected").val();
		 var minutesForWeek4 = $("#minutesForWeek4").children("option:selected").val();
		 
		 var timeInformation = {};
		 	timeInformation["year"] = year;
		 	timeInformation["month"] = month;
		 	timeInformation["hoursForWeek1"] = hoursForWeek1;
		 	timeInformation["minutesForWeek1"] = minutesForWeek1;
		 	timeInformation["hoursForWeek2"] = hoursForWeek2;
		 	timeInformation["minutesForWeek2"] = minutesForWeek2;
		 	timeInformation["hoursForWeek3"] = hoursForWeek3;
		 	timeInformation["minutesForWeek3"] = minutesForWeek3;
		 	timeInformation["hoursForWeek4"] = hoursForWeek4;
		 	timeInformation["minutesForWeek4"] = minutesForWeek4;
		    
		 $.ajax({
		        type: "POST",
		        contentType: "application/json",
		        url: "/timereporting/api/addtime",
		        data: JSON.stringify(timeInformation),
		        dataType: 'json',
		        success: function (data) {
		        	console.log("success");
		        	console.log(data);
		        },
		        error: function (e) {
		        	console.log("error");
		        	console.log(e);
		        }
		    });
	 });
	 
	 
	 var loadMonthYearRequest = {};
	 	loadMonthYearRequest["action"] = "action-love";
	 	
	 $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/timereporting/api/loadmonthyear",
	        data: JSON.stringify(loadMonthYearRequest),
	        dataType: 'json',
	        success: function (data) {
	        	var years = data.years;
	        	var yearDiv = "";
	        	for ( var key in years) {
	        		yearDiv = yearDiv + "<option>"+years[key].toString()+"</option>";
	        	}
	        	$("#years").append( yearDiv);
	        	
	        	var months = data.months;
	        	var monthsDiv = "";
	        	for ( var key in months) {
	        		monthsDiv = monthsDiv + "<option>"+months[key].toString()+"</option>";
	        	}
	        	$("#months").append( monthsDiv);
	        	
	        	var hours = data.hours;
	        	var hoursDiv = "";
	        	for ( var key in hours) {
	        		hoursDiv = hoursDiv + "<option>"+hours[key].toString()+"</option>";
	        	}
	        	$("#hoursForWeek1").append( hoursDiv);
	        	$("#hoursForWeek2").append( hoursDiv);
	        	$("#hoursForWeek3").append( hoursDiv);
	        	$("#hoursForWeek4").append( hoursDiv);
	        	
	        	var minutes = data.minutes;
	        	var minutesDiv = "";
	        	for ( var key in minutes) {
	        		minutesDiv = minutesDiv + "<option>"+minutes[key].toString()+"</option>";
	        	}
	        	$("#minutesForWeek1").append( minutesDiv);
	        	$("#minutesForWeek2").append( minutesDiv);
	        	$("#minutesForWeek3").append( minutesDiv);
	        	$("#minutesForWeek4").append( minutesDiv);
	        	
	        	
	        },
	        error: function (e) {
	        	console.log("error");
	        	console.log(e);
	        }
	    });
//});
	 
	 $( "#years" ).change(function() {
		 
		 var loadMonthYearRequest = {};
		 	loadMonthYearRequest["action"] = "action-love";
		 	
		 $.ajax({
		        type: "POST",
		        contentType: "application/json",
		        url: "/timereporting/api/loadmonthyear",
		        data: JSON.stringify(loadMonthYearRequest),
		        dataType: 'json',
		        success: function (data) {
		        	var date = new Date();
		        	var currentYear = date.getFullYear();
		        	var selectedYear = $("#years").children("option:selected").val();
		        	
		        	
		        		var currentMonth = date.getMonth();
			        	var months = data.months;
			        	var monthsDiv = "";
			        	$("#months").html( monthsDiv);
			        	for ( var key in months) {
			        		console.log("A");
			        		console.log("key"+key);
			        		if(currentYear == selectedYear){
			        			if(key < currentMonth)
			        				monthsDiv = monthsDiv + "<option>"+months[key].toString() + "</option>";
			        		} else{
			        			monthsDiv = monthsDiv + "<option>"+months[key].toString() + "</option>";
			        		}
			        	}
			        	$("#months").append( monthsDiv);
		        	
		        },
		        error: function (e) {
		        	console.log("error");
		        	console.log(e);
		        }
		    });
	 });
	 
$( "#months" ).change(function() {
		
	var year = $("#years").children("option:selected").val();
	 var month = $("#months").children("option:selected").val();
	 
		var loadMonthYearRequest = {};
		 	loadMonthYearRequest["year"] = year;
		 	loadMonthYearRequest["month"] = month;
		 	console.log("gaga");
		 $.ajax({
		        type: "POST",
		        contentType: "application/json",
		        url: "/timereporting/api/getexistinghours",
		        data: JSON.stringify(loadMonthYearRequest),
		        dataType: 'json',
		        success: function (data) {
		        	console.log(data);
		        	var status = data.status;
		        	$("#status").text( status);
		        	$("#hoursForWeek1").val(data.hoursForWeek1);
		        	$("#minutesForWeek1").val(data.minutesForWeek1);
		        	$("#hoursForWeek2").val(data.hoursForWeek2);
		        	$("#minutesForWeek2").val(data.minutesForWeek2);
		        	$("#hoursForWeek3").val(data.hoursForWeek3);
		        	$("#minutesForWeek3").val(data.minutesForWeek3);
		        	$("#hoursForWeek4").val(data.hoursForWeek4);
		        	$("#minutesForWeek4").val(data.minutesForWeek4);
		        	
		        },
		        error: function (e) {
		        	console.log("error");
		        	console.log(e);
		        }
		    });
	 });
	 
});