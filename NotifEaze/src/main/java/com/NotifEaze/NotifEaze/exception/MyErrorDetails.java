package com.NotifEaze.NotifEaze.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorDetails {
	
	private LocalDate timeStamp;
	private String message;
	private String discription;

}
