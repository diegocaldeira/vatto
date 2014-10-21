package com.aoks.social.facebook.config;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

@RequestScoped
public class Connect implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FacebookClient getFacebookClient(){
		return new DefaultFacebookClient("CAACEdEose0cBAC1H6KTmKZBw0m0wyNghFjBf5xUP2dMZCmBeW7IwhnA7QVszZAyGVMaHfBRs6YqriOdo4UNQVhpe8L5BGVTaAYJnE7QbUoNr9jMxLESjqwxiKTP3a6ZBZCQ1zwShfH5kCqwgB3KP6oM9ZCV73HPsDuhOtAhFeQWN5zY4DZChZCZCxZAcZB2ikIaMcwZD");
	}

}
