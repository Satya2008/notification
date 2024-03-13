package com.NotifEaze.NotifEaze.requestBodyDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequestBody {

	private String to;
	private String subject;
	private String body;
}
