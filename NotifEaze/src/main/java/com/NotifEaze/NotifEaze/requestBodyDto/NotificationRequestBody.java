package com.NotifEaze.NotifEaze.requestBodyDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRequestBody {
  
	
	private String fcmToken;
	private String message;
	
}
