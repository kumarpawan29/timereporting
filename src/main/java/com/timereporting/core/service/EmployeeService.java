package com.timereporting.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.timereporting.core.model.Employee;
import com.timereporting.core.repositroy.EmployeeRepository;

@Service("employeeService")
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
   
    public Employee saveEmployee(Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setActive(1);
        employee.setRole(1);
        return employeeRepository.save(employee);
    }
    
    public boolean isEmployeeLoggedIn() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return !(authentication instanceof AnonymousAuthenticationToken);
    }
    
    public Employee getLoggedInEmployee() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return employeeRepository.findByEmail(email);
    }
}