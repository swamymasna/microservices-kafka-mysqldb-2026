package com.kes.employeeservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEvent {

	private Integer id;

	private String employeeName;

	private String email;

	private String eventType;
}
