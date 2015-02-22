package com.nilsonmassarenti.twittermessage.app;

import java.io.IOException;

import com.nilsonmassarenti.twittermessage.controller.oauth.twitter.TwitterAuthenticationException;
import com.nilsonmassarenti.twittermessage.view.ViewTwitterReader;


/**
 * Class to start the application
 * @author nilsonmassarenti - nilsonmassarenti@gmail.com
 * @version 0.1
 */
public class App {


	public static void main(String[] args)
			throws TwitterAuthenticationException, IOException {
		//instance the view to process the keywords in twitter
		ViewTwitterReader vw = new ViewTwitterReader();
		vw.twitterReader();
	}
	
}
