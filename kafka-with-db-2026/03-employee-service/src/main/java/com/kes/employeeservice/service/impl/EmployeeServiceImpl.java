package com.kes.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kes.employeeservice.dto.EmployeeDto;
import com.kes.employeeservice.entity.Employee;
import com.kes.employeeservice.event.EmployeeEvent;
import com.kes.employeeservice.producer.EmployeeProducer;
import com.kes.employeeservice.repository.EmployeeRepository;
import com.kes.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private ModelMapper modelMapper;

	private EmployeeProducer employeeProducer;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

		// convert DTO to Entity
		Employee employee = modelMapper.map(employeeDto, Employee.class);

		// save employee into database
		Employee savedEmployee = employeeRepository.save(employee);

		// create & set EmployeeEvent with event type
		EmployeeEvent employeeEvent = new EmployeeEvent(savedEmployee.getId(), savedEmployee.getEmployeeName(),
				savedEmployee.getEmail(), "EMPLOYEE_CREATED");

		// send employee event to kafka
		employeeProducer.sendEmployeeDetails(employeeEvent);

		// convert Entity to DTO
		return modelMapper.map(savedEmployee, EmployeeDto.class);
	}
}
