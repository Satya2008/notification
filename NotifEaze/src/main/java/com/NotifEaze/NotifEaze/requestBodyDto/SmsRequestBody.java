package com.NotifEaze.NotifEaze.requestBodyDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsRequestBody {
	
	private String phoneNumber;
	private String message;
}
