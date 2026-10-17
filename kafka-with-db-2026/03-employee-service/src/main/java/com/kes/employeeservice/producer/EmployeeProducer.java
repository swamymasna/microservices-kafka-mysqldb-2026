package com.kes.employeeservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kes.employeeservice.event.EmployeeEvent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmployeeProducer {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeProducer.class);

	private static final String TOPIC = "employee-topic";

	private KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

	public void sendEmployeeDetails(EmployeeEvent employeeEvent) {

		kafkaTemplate.send(TOPIC, employeeEvent.getId().toString(), employeeEvent);

		LOG.info(String.format("Message Sent -> %s", employeeEvent.toString()));
	}
}
