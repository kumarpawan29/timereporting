package com.timereporting.core.scheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.timereporting.core.model.Employee;
import com.timereporting.core.model.Hours;
import com.timereporting.core.model.MonthlyReport;
import com.timereporting.core.repositroy.EmployeeRepository;
import com.timereporting.core.repositroy.HoursRepository;

@Component
public class ReportScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportScheduler.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private HoursRepository hoursRepository;
	
	@Value("${spring.path.employeeMonthReport}")
    private String employeeMonthReport;
	
	@Value("${spring.path.monthReport}")
    private String monthReport;

	@Scheduled(cron = "0 0 6 * * ?") 
	public void employeeMonthReport() { 
		try {
			List<Employee> employeeList = employeeRepository.findAll();
			Iterator<Employee> employeeListIterator = employeeList.iterator();
			while(employeeListIterator.hasNext()) {
				Employee employee = employeeListIterator.next();
				int reference = employee.getReference();
				
				File file = new File(employeeMonthReport+"\\"+reference + "_" + employee.getName()+".csv");
				FileWriter outputfile = new FileWriter(file); 
				CSVWriter writer = new CSVWriter(outputfile); 
				String[] header = {"Year", "Month", "Week 1", "Week 2", "Week3", "Week4", "Total Hours"};
				writer.writeNext(header);
				
				List<Hours> hoursList = hoursRepository.findByReferencePk(reference);
				
				Collections.sort(hoursList, new Comparator<Hours>(){
					@Override
					public int compare(Hours u1, Hours u2) {
						return u1.getYear().compareTo(u2.getYear());
					}
				});
				
				Iterator<Hours> hoursListIterator = hoursList.iterator();
				while(hoursListIterator.hasNext()) {
					Hours hours = hoursListIterator.next();
					String[] data = new String[7];
					data[0] = hours.getYear();
					data[1] = hours.getMonth();
					data[2] = String.valueOf(hours.getWeek1());
					data[3] = String.valueOf(hours.getWeek2());
					data[4] = String.valueOf(hours.getWeek3());
					data[5] = String.valueOf(hours.getWeek4());
					data[6] = String.valueOf(hours.getTotalhours());
					writer.writeNext(data); 
				}
				writer.close(); 
				outputfile.close();
			}
		}catch(IOException e){
			logger.error("::::::Exception occured in employeeMonthReport():::::{}",e);
		}
	}
	
	@Scheduled(cron = "0 0 6 * * ?") 
		public void MonthReportData() {
			List<MonthlyReport> monthlyReportList = new ArrayList<>();
			
			List<Hours> hoursList = hoursRepository.findAll();
			Iterator<Hours> hoursListIterator = hoursList.iterator();
			while(hoursListIterator.hasNext()) {
				Hours hours = hoursListIterator.next();
				String year = hours.getYear();
				String month = hours.getMonth();
				double totalHours = hours.getTotalhours();
				
				MonthlyReport monthlyReport = monthlyReportList.stream()
						.filter(mr -> year.equalsIgnoreCase(mr.getYear()))
						.findAny()
						.orElse(null);
				
				if(monthlyReport == null) {
					monthlyReportList.add(new MonthlyReport(year, month, totalHours));
				}else {
					MonthlyReport monthlyReport2 = monthlyReportList.stream()
							.filter(mr -> month.equalsIgnoreCase(mr.getMonth()))
							.findAny()
							.orElse(null);
					if(monthlyReport2 == null) {
						monthlyReportList.add(new MonthlyReport(year, month, totalHours));
					}else {
						monthlyReport2.setHours(monthlyReport2.getHours() + totalHours);
						monthlyReportList.add(monthlyReport);
					}
				}
			}
			
			if(!monthlyReportList.isEmpty()) {
				Collections.sort(monthlyReportList, new Comparator<MonthlyReport>(){
					@Override
					public int compare(MonthlyReport u1, MonthlyReport u2) {
						return u1.getYear().compareTo(u2.getYear());
					}
				});
				creteMonthlyReport(monthlyReportList);
			}
		}

	private void creteMonthlyReport(List<MonthlyReport> monthlyReportList) {
		try {
			File file = new File(monthReport+"\\"+"MonthlyReport.csv");
			FileWriter outputfile = new FileWriter(file); 
			CSVWriter writer = new CSVWriter(outputfile); 
			String[] header = {"Year", "Month", "Total Hours"};
			writer.writeNext(header);
			
			Iterator<MonthlyReport> monthlyReportListIterator = monthlyReportList.iterator();
			while(monthlyReportListIterator.hasNext()) {
				MonthlyReport monthlyReport = monthlyReportListIterator.next();
				String[] data = new String[3];
				data[0] = monthlyReport.getYear();
				data[1] = monthlyReport.getMonth();
				data[2] = String.valueOf(monthlyReport.getHours());
				writer.writeNext(data); 
			}
			writer.close(); 
			outputfile.close();
		}catch(Exception e) {
			logger.error("::::::Exception occured in creteMonthlyReport():::::{}",e);
		}	
	}
}
