package com.test.bitswilp.microservices.contactsapi.exception;

import java.util.Date;

public class ExceptionResponse {
		//timestamp -- when the exception occurred
		private Date timestamp;
		// message 
		private String message;
		//detail 
		private String details;
		public ExceptionResponse(Date timestamp, String message, String details) {
			super();
			this.timestamp = timestamp;
			this.message = message;
			this.details = details;
		}
		public Date getTimestamp() {
			return timestamp;
		}

		public String getMessage() {
			return message;
		}

		public String getDetails() {
			return details;
		}

		
}
