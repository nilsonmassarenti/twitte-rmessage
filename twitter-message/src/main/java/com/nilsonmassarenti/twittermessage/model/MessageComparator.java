package com.nilsonmassarenti.twittermessage.model;

/**
 * Class to sort the messages
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */
import java.util.Comparator;

public class MessageComparator implements Comparator<TwitterMessage> {
	/**
	 * this method is to compare and sort messages
	 * 
	 * @param TwitterMessage
	 *            , TwitterMessage
	 */
	public int compare(TwitterMessage o1, TwitterMessage o2) {
		return o1.getId().compareTo(o2.getId());
	}
}
