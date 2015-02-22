package com.nilsonmassarenti.twittermessage.view;

/**
 * Class to show the informations
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import com.nilsonmassarenti.twittermessage.controller.ControllerTwitter;
import com.nilsonmassarenti.twittermessage.model.TwitterMessage;
import com.nilsonmassarenti.twittermessage.model.TwitterUserMessage;

public class ViewTwitterReader {

	public void twitterReader() {
		ControllerTwitter controllerTwitter = new ControllerTwitter();
		
		System.out.println("Reader of Tweet\n\n");
		String consumerkey;
		String consumersecret;
		
		String keyword = "";
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter with consumer key: ");
		consumerkey = sc.nextLine();
		System.out.println("Enter with consumer secret: ");
		consumersecret = sc.nextLine();
		controllerTwitter.setConsumerkey(consumerkey);
		controllerTwitter.setConsumersecret(consumersecret);
		
		System.out.println("Type the keyword to search in Twitter: ");
		keyword = sc.nextLine();

		System.out.println("\n\n Starting process to read");

		controllerTwitter.processMessages(keyword);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date;
		
		//show the Users and your messages 
		for (Iterator<TwitterUserMessage> iterator = controllerTwitter
				.getTwitterUserMessages().iterator(); iterator.hasNext();) {
			System.out.println("\n");
			TwitterUserMessage twitterUserMessage = (TwitterUserMessage) iterator
					.next();
			date = twitterUserMessage.getTwitterUser().getCreated().getTime();
			System.out.println("User ID: "
					+ twitterUserMessage.getTwitterUser().getIdStr());
			System.out.println("User: "
					+ twitterUserMessage.getTwitterUser().getScreenName());
			System.out.println("User created: " + sdf.format(date));
			for (Iterator<TwitterMessage> iterator2 = twitterUserMessage
					.getTwitterMessages().iterator(); iterator2.hasNext();) {
				TwitterMessage twitterMessage = (TwitterMessage) iterator2
						.next();
				date = twitterMessage.getDate().getTime();
				System.out.println("Message ID: " + twitterMessage.getId());
				System.out.println("Message date: " + sdf.format(date));
				System.out.println("Message text:"
						+ twitterMessage.getMessage());
			}

		}
	}

}
