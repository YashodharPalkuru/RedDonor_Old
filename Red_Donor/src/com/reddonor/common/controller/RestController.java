package com.reddonor.common.controller;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;

@Controller
public class RestController{
 
	@RequestMapping("/login.htm")
	public String signin(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Facebook facebook = new FacebookFactory().getInstance();
        String appId = "";
        String appSecret="";
        facebook.setOAuthAppId(appId, appSecret);
        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        
        int index = callbackURL.lastIndexOf("/");
        System.out.println(callbackURL);
        callbackURL.replace(index, callbackURL.length(), "").append("/callback.htm");
        String redirectURL = facebook.getOAuthAuthorizationURL(callbackURL.toString()).replaceFirst("www.facebook.com", "m.facebook.com");
        System.out.println(redirectURL);
        return "redirect:"+redirectURL;
 
	}
 
	@RequestMapping("/callback.htm")
	public ModelAndView delete(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String oauthCode = request.getParameter("code");
        try {
            facebook.getOAuthAccessToken(oauthCode);
        } catch (FacebookException e) {
            throw new ServletException(e);
        }
		return new ModelAndView("welcome", "msg","delete() method");
 
	}
 
	@RequestMapping("/logout.htm")
	public ModelAndView update(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
		return new ModelAndView(" ", " "," ");
 
	}
 
	@RequestMapping("/getUserDetails.htm")
	public ModelAndView list(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
		return new ModelAndView("welcome", "msg","");
 
	}
 
}