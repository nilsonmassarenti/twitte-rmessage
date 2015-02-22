package com.nilsonmassarenti.twittermessage.controller.oauth.twitter;

public class TwitterAuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5400293141537834535L;

	public TwitterAuthenticationException() {
		super();
	}
	
	public TwitterAuthenticationException(final String message) {
		super(message);
	}
	
	public TwitterAuthenticationException(final String message, final Throwable t) {
		super(message, t);
	}
	
	public TwitterAuthenticationException(final Throwable t) {
		super(t);
	}
}
