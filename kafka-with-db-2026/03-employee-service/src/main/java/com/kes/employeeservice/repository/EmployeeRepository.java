package com.kes.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kes.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
