package com.NotifEaze.NotifEaze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NotifEaze.NotifEaze.dto.BaseEmailResponse;
import com.NotifEaze.NotifEaze.dto.BasePushNotificationResponse;
import com.NotifEaze.NotifEaze.dto.BaseSmsResponse;
import com.NotifEaze.NotifEaze.dto.SmsDeliveryStatus;
import com.NotifEaze.NotifEaze.requestBodyDto.EmailRequestBody;
import com.NotifEaze.NotifEaze.requestBodyDto.NotificationRequestBody;
import com.NotifEaze.NotifEaze.requestBodyDto.SmsRequestBody;
import com.NotifEaze.NotifEaze.service.EmailService;
import com.NotifEaze.NotifEaze.service.PushNotificationService;
import com.NotifEaze.NotifEaze.service.SmsService;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PushNotificationService notificationService;

	@PostMapping("/send-sms")
	public ResponseEntity<BaseSmsResponse> sendSms(@RequestBody SmsRequestBody smsRequestBody) {

		BaseSmsResponse response = smsService.sendSms(smsRequestBody.getPhoneNumber(), smsRequestBody.getMessage());

		return new ResponseEntity<BaseSmsResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/send-email")
	public ResponseEntity<BaseEmailResponse> sendEmail(@RequestBody EmailRequestBody emailRequest) {

		BaseEmailResponse response = emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(),
				emailRequest.getBody());

		return new ResponseEntity<BaseEmailResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/send-notification")
	public ResponseEntity<BasePushNotificationResponse> sendNotification(
			@RequestBody NotificationRequestBody notificationRequest) {

		BasePushNotificationResponse response = notificationService.sendNotification(notificationRequest.getFcmToken(),
				notificationRequest.getMessage());

		return new ResponseEntity<BasePushNotificationResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/get-status/{messageId}")
	public ResponseEntity<SmsDeliveryStatus> getStatus(@PathVariable String messageId) {

		SmsDeliveryStatus response = smsService.checkDeliveryStatus(messageId);

		return new ResponseEntity<SmsDeliveryStatus>(response, HttpStatus.OK);
	}
}
