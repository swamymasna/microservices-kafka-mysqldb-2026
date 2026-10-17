package com.kes.notificationservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kes.notificationservice.entity.Notification;
import com.kes.notificationservice.event.EmployeeEvent;
import com.kes.notificationservice.repository.NotificationRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmployeeConsumer {

	private NotificationRepository notificationRepository;

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeConsumer.class);

	@KafkaListener(topics = "employee-topic", groupId = "notification-group")
	public void consumeEmployee(EmployeeEvent employeeEvent) {

		LOG.info(String.format("Received Event -> %s", employeeEvent.toString()));

		Notification notification = new Notification();
		notification.setEmployeeId(employeeEvent.getId());
		notification.setEmployeeName(employeeEvent.getEmployeeName());
		notification.setEmail(employeeEvent.getEmail());
		notification.setEventType(employeeEvent.getEventType());
		notification.setMessage("Employee Created Successfully : " + employeeEvent.getEmployeeName());

		// save employee details into notification database
		notificationRepository.save(notification);

		LOG.info(String.format("Notification Saved For Employee ID : %s", employeeEvent.getId()));
	}
}
