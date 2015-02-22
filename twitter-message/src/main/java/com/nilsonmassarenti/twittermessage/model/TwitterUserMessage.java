package com.nilsonmassarenti.twittermessage.model;

/**
 * Model class to TwitterUserMessage
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */

import java.util.List;

public class TwitterUserMessage {
	private TwitterUser twitterUser;
	private List<TwitterMessage> twitterMessages;
	
	public TwitterUserMessage(TwitterUser twitterUser,
			List<TwitterMessage> twitterMessages) {
		super();
		this.twitterUser = twitterUser;
		this.twitterMessages = twitterMessages;
	}

	public TwitterUser getTwitterUser() {
		return twitterUser;
	}

	public List<TwitterMessage> getTwitterMessages() {
		return twitterMessages;
	}
}
