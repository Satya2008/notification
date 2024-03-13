package com.NotifEaze.NotifEaze.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NotifEaze.NotifEaze.dto.BasePushNotificationResponse;
import com.NotifEaze.NotifEaze.exception.NotFoundExceptions;
import com.NotifEaze.NotifEaze.exception.SomethingWentWrong;
import com.NotifEaze.NotifEaze.mock.GupshupMock;
import com.NotifEaze.NotifEaze.service.PushNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

	@Autowired
	private GupshupMock gupshupMock;

	@Override
	public BasePushNotificationResponse sendNotification(String fcmToken, String message) {

		String emailMockResponse = gupshupMock.getPnMocked(fcmToken);

		BasePushNotificationResponse basePushNotificationResponse = null;

		if (emailMockResponse == null) {
			throw new NotFoundExceptions("Data not found.");
		}

		ObjectMapper modelMapper = new ObjectMapper();

		try {
			Map<String, Object> jsonData = modelMapper.readValue(emailMockResponse, Map.class);

			String message1 = (String) jsonData.get("message");
			String status = (String) jsonData.get("status");

			boolean success = false;
			if (status.equalsIgnoreCase("success")) {
				success = true;
			}

			basePushNotificationResponse = new BasePushNotificationResponse(success, message1);

		} catch (JsonProcessingException e) {

			e.getStackTrace();
		}

		return basePushNotificationResponse;
	}

}
