package com.nilsonmassarenti.twittermessage.model;

/**
 * Model class to TwitterUser
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */

import java.util.Calendar;

public class TwitterUser {
	
	//Variables to create a User of Twitter
	private Long id;
	private String idStr;
	private String name;
	private String screenName;
	private String location;
	private String profileLocation;
	private String description;
	private String url;
	private Calendar created;
	
	//Super Class to create a Twitter User
	public TwitterUser(Long id, String idStr, String name, String screenName,
			String location, String profileLocation, String description,
			String url, Calendar cal) {
		super();
		this.id = id;
		this.idStr = idStr;
		this.name = name;
		this.screenName = screenName;
		this.location = location;
		this.profileLocation = profileLocation;
		this.description = description;
		this.url = url;
		this.created = cal;
	}

	//methods to get variables
	public Long getId() {
		return id;
	}

	public String getIdStr() {
		return idStr;
	}

	public String getName() {
		return name;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getLocation() {
		return location;
	}

	public String getProfileLocation() {
		return profileLocation;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public Calendar getCreated() {
		return created;
	}
	
	
	
}
