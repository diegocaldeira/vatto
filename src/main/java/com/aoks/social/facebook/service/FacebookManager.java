package com.aoks.social.facebook.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

@RequestScoped
public class FacebookManager implements Serializable {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Returns list of user's friends.
	 * 
	 * @param facebookClient
	 * @return
	 */
	public Connection<User> loadFriendList(FacebookClient facebookClient){
		return facebookClient.fetchConnection("me/friends", User.class, Parameter.with("fields", "first_name, middle_name, last_name, birthday, gender, username, hometown, relationship_status"));
	}
	
	
}
