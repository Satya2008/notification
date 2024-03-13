package com.NotifEaze.NotifEaze.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NotifEaze.NotifEaze.dto.BaseEmailResponse;
import com.NotifEaze.NotifEaze.exception.NotFoundExceptions;
import com.NotifEaze.NotifEaze.exception.SomethingWentWrong;
import com.NotifEaze.NotifEaze.mock.GupshupMock;
import com.NotifEaze.NotifEaze.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private GupshupMock gupshupMock;

	@Override
	public BaseEmailResponse sendEmail(String to, String subject, String body) {

		String emailMockResponse = gupshupMock.getMockEmail(to);

		BaseEmailResponse baseEmailResponse = null;

		if (emailMockResponse != null) {
			try {

				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> jsonData = objectMapper.readValue(emailMockResponse, Map.class);

				String message = (String) jsonData.get("message");
				String status = (String) jsonData.get("status");

				boolean success = status != null && status.equalsIgnoreCase("success");

				baseEmailResponse = new BaseEmailResponse(success, message);
			} catch (JsonProcessingException e) {

				throw new SomethingWentWrong("Error during processing json response for email: " + to);
			}
		} else {

			throw new NotFoundExceptions("No mock response available for email: " + to);
		}

		return baseEmailResponse;
	}

}
