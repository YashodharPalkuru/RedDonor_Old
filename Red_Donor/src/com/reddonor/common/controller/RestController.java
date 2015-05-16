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
	public String signin(HttpServletRequest request , HttpServletResponse response) throws Exception {
		Facebook facebook = new FacebookFactory().getInstance();
        String appId = "827202724017029";
        String appSecret = "8bdc6372917fb927803528804329180e";
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
        try
        {
            facebook.getOAuthAccessToken(oauthCode);
        }
        catch (FacebookException e)
        {
            throw new ServletException(e);
        }
		return new ModelAndView("welcome", "msg","delete() method");
 
	}
 
	@RequestMapping("/logout.htm")
	public String logout(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		 Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
	        String accessToken = null;
	        try
	        {
	        	accessToken = facebook.getOAuthAccessToken().getToken();
	        }
	        catch (Exception e)
	        {
	            throw new ServletException(e);
	        }
	        request.getSession().invalidate();

	        // Log Out of the Facebook
	        StringBuffer next = request.getRequestURL();
	        int index = next.lastIndexOf("/");
	        next.replace(index+1, next.length(), "");
	        
	        String redirectURL = "http://www.facebook.com/logout.php?next=" + next.toString() + "&access_token=" + accessToken;
	        System.out.println(redirectURL);
	        return "redirect:"+redirectURL;
 
	}
 
	@RequestMapping("/getUserDetails.htm")
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response) throws Exception {
 
		return new ModelAndView("jsonView", "msg","msg");
 
	}
 
}