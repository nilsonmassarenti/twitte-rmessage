package com.nilsonmassarenti.twittermessage.controller;

/**
 * Class to control all the operations to get information in Twitter
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.api.client.http.HttpRequestFactory;
import com.nilsonmassarenti.twittermessage.controller.oauth.twitter.TwitterAuthenticationException;
import com.nilsonmassarenti.twittermessage.controller.oauth.twitter.TwitterAuthenticator;
import com.nilsonmassarenti.twittermessage.dao.DAOTwitter;
import com.nilsonmassarenti.twittermessage.model.MessageComparator;
import com.nilsonmassarenti.twittermessage.model.NameComparator;
import com.nilsonmassarenti.twittermessage.model.TwitterMessage;
import com.nilsonmassarenti.twittermessage.model.TwitterUser;
import com.nilsonmassarenti.twittermessage.model.TwitterUserMessage;

public class ControllerTwitter {

	private String consumerkey;
	private String consumersecret;

	private DAOTwitter daoTwitter = new DAOTwitter();
	private List<TwitterUserMessage> twitterUserMessages = new ArrayList<TwitterUserMessage>();

	/**
	 * this method is responsable to process and get data in twitter
	 * 
	 * @param keyword
	 */
	public void processMessages(String keyword) {
		PrintStream out = null;
		out = new PrintStream(System.out);
		TwitterAuthenticator twitterAuth = new TwitterAuthenticator(out,
				consumerkey, consumersecret);
		HttpRequestFactory reqFac = null;
		try {
			reqFac = twitterAuth.getAuthorizedHttpRequestFactory();
		} catch (TwitterAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// set HttpRequestFactory in DAO to get information in twitter
		daoTwitter.setReq(reqFac);

		System.out.println("\n\n Reading tweets...");
		if (keyword != null) {
			daoTwitter.listTwitterMessage(keyword);
			List<TwitterMessage> arrayTwitterMessages = daoTwitter
					.getArrayTwitterMessage();
			if (arrayTwitterMessages.size() > 0) {

				// sort by name of user
				Collections.sort(arrayTwitterMessages, new NameComparator());
				TwitterUser oldTwitterUser = null;
				Integer counter = arrayTwitterMessages.size();
				List<TwitterMessage> twitterMessages = null;

				for (Iterator<TwitterMessage> iterator = arrayTwitterMessages
						.iterator(); iterator.hasNext();) {
					TwitterMessage twitterMessage = (TwitterMessage) iterator
							.next();

					/*
					 * this case is to oldTwitterUser == null, because the first
					 * time was null
					 */
					if (oldTwitterUser == null) {
						oldTwitterUser = twitterMessage.getTwitterUser();
						twitterMessages = new ArrayList<TwitterMessage>();
					}

					/*
					 * compare the oldTwitterUser to know if equal, add
					 * twitterMessage but if oldTwitter was different the
					 * procedure is add all informations on twitterUserMessages
					 */
					if (twitterMessage.getTwitterUser().getId()
							.equals(oldTwitterUser.getId())) {
						twitterMessages.add(twitterMessage);
					} else {
						Collections.sort(twitterMessages,
								new MessageComparator());
						TwitterUserMessage twitterUserMessage = new TwitterUserMessage(
								oldTwitterUser, twitterMessages);
						twitterUserMessages.add(twitterUserMessage);
						twitterMessages = new ArrayList<TwitterMessage>();
						twitterMessages.add(twitterMessage);
						oldTwitterUser = twitterMessage.getTwitterUser();

					}

					/*
					 * this case if EOF of iterator, the if will save all
					 * informations on twitterUserMessages
					 */
					if (--counter == 0) {
						TwitterUserMessage twitterUserMessage = new TwitterUserMessage(
								oldTwitterUser, twitterMessages);
						twitterUserMessages.add(twitterUserMessage);
					}

				}

			}
		}

	}

	/**
	 * this method return the list of TwitterUserMessage
	 * 
	 * @return List<TwitterUserMessage>
	 */
	public List<TwitterUserMessage> getTwitterUserMessages() {
		return twitterUserMessages;
	}

	public void setConsumerkey(String consumerkey) {
		this.consumerkey = consumerkey;
	}

	public void setConsumersecret(String consumersecret) {
		this.consumersecret = consumersecret;
	}

}
