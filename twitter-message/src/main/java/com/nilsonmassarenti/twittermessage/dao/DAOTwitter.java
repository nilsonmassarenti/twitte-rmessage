package com.nilsonmassarenti.twittermessage.dao;

/**
 * Class to search the tweets in Twitter via params
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.nilsonmassarenti.twittermessage.model.TwitterMessage;
import com.nilsonmassarenti.twittermessage.model.TwitterUser;

public class DAOTwitter {

	private static final String MESSAGE_URL = "https://api.twitter.com/1.1/search/tweets.json";
	private static final Integer COUNT_TWEET = 100;
	private static final Integer TIMER_WAIT = 30;

	private HttpRequestFactory req;
	private ArrayList<TwitterMessage> arrayTwitterMessage = new ArrayList<TwitterMessage>();
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss z yyyy", Locale.US);

	/**
	 * method to search a keyword in Twitter
	 * 
	 * @param keyword
	 */
	public void listTwitterMessage(String keyword) {
		GenericUrl url = new GenericUrl(MESSAGE_URL + "?q=" + keyword
				+ "&count=" + COUNT_TWEET);
		HttpRequest request;

		// timer to execution
		Boolean timer = true;
		long startTime = System.currentTimeMillis();
		while (timer && (arrayTwitterMessage.size() < COUNT_TWEET)) {
			arrayTwitterMessage.clear();
			if ((System.currentTimeMillis() - startTime) / 1000 > TIMER_WAIT) {
				timer = false;
			}
			try {
				request = req.buildGetRequest(url);
				request.getHeaders().setContentType("application/json");
				JSONObject jsonObject = new JSONObject(request.execute()
						.parseAsString());
				JSONArray jsonArrayStatuses = (JSONArray) jsonObject
						.get("statuses");

				for (int i = 0; i < jsonArrayStatuses.length(); i++) {
					Calendar cal = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					JSONObject statuses = (JSONObject) jsonArrayStatuses.get(i);
					JSONObject user = statuses.getJSONObject("user");
					cal.setTime(sdf.parse(user.get("created_at").toString()));
					TwitterUser twitterUser = new TwitterUser(
							Long.parseLong(user.get("id").toString()), user
									.get("id_str").toString(), user.get("name")
									.toString(), user.get("screen_name")
									.toString(), user.get("location")
									.toString(), user.get("profile_location")
									.toString(), user.get("description")
									.toString(), user.get("url").toString(),
							cal);
					cal2.setTime(sdf
							.parse(statuses.get("created_at").toString()));
					TwitterMessage twitterMessage = new TwitterMessage(
							Long.parseLong(statuses.get("id").toString()), cal2,
							statuses.get("text").toString(), twitterUser);
					arrayTwitterMessage.add(twitterMessage);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * method to set the HttpRequestFactory
	 * 
	 * @param request
	 */
	public void setReq(HttpRequestFactory request) {
		this.req = request;
	}

	/**
	 * method to get the generated list of messages
	 */
	public ArrayList<TwitterMessage> getArrayTwitterMessage() {
		return arrayTwitterMessage;
	}

}
