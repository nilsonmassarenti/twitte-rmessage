package com.nilsonmassarenti.twittermessage.model;

/**
 * Class to Compare the names of Text and sort
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */
import java.util.Comparator;

public class NameComparator implements Comparator<TwitterMessage> {
	/**
	 * @param TwitterMessage
	 *            , TwitterMessage
	 */
	public int compare(TwitterMessage o1, TwitterMessage o2) {
		return o1.getTwitterUser().getScreenName()
				.compareToIgnoreCase(o2.getTwitterUser().getScreenName());
	}
}
