package com.nilsonmassarenti.twittermessage.model;

/**
 * Model class to TwitterMessage
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */
import java.util.Calendar;

public class TwitterMessage {
	private Long id;
	private Calendar date;
	private String message;
	private TwitterUser twitterUser;

	// constructor
	public TwitterMessage(Long id, Calendar date, String message,
			TwitterUser twitterUser) {
		super();
		this.id = id;
		this.date = date;
		this.message = message;
		this.twitterUser = twitterUser;
	}

	public Long getId() {
		return id;
	}

	public Calendar getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public TwitterUser getTwitterUser() {
		return twitterUser;
	}

}
