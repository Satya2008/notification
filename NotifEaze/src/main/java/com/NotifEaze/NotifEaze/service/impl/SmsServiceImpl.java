package com.NotifEaze.NotifEaze.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NotifEaze.NotifEaze.dto.BaseSmsResponse;
import com.NotifEaze.NotifEaze.dto.SmsDeliveryStatus;
import com.NotifEaze.NotifEaze.exception.NotFoundExceptions;
import com.NotifEaze.NotifEaze.exception.SomethingWentWrong;
import com.NotifEaze.NotifEaze.mock.GupshupMock;
import com.NotifEaze.NotifEaze.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private GupshupMock gupshupMock;

	@Override
	public BaseSmsResponse sendSms(String phoneNumber, String message) {
		String response = gupshupMock.getSmsMocked(phoneNumber);
		if (response == null) {
			throw new NotFoundExceptions("Data not found for phone number: " + phoneNumber);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String, Object> jsonData = objectMapper.readValue(response, Map.class);

			String providerResponse = (String) jsonData.get("providerResponse");
			boolean success = (boolean) jsonData.get("success");

			return new BaseSmsResponse(success, providerResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Error occurred while processing JSON response");
		}
	}

	@Override
	public SmsDeliveryStatus checkDeliveryStatus(String messageId) {

		SmsDeliveryStatus deliveryStatus = null;
		String response = gupshupMock.getSmsDeliveryStatusMocked(messageId);

		if (response == null) {
			throw new NotFoundExceptions("Data not found.");
		}
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			Map<String, Object> jsonData = objectMapper.readValue(response, Map.class);
			String message = (String) jsonData.get("message");
			String status = (String) jsonData.get("status");

			boolean stat = false;
			if (status.equalsIgnoreCase("success")) {
				stat = true;
			}
			deliveryStatus = new SmsDeliveryStatus(stat, message);

		} catch (JsonProcessingException e) {
			e.getStackTrace();
		}
		return deliveryStatus;
	}

}
