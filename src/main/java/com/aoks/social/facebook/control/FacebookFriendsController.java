//package com.aoks.social.facebook.control;
//
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Named
//@SessionScoped
//public class FacebookFriendsController {
//
//	private final Facebook facebook;
//
//	@Inject
//	public FacebookFriendsController(Facebook facebook) {
//		this.facebook = facebook;
//	}
//
//	@RequestMapping(value="/facebook/friends", method=RequestMethod.GET)
//	public String showFeed(Model model) {
//		model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());
//		return "facebook/friends";
//	}
//
//}