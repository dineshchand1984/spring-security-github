package com.bit.wonders.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.wonders.application.AppConfigProperties;

@RestController
public class HelloController {

	@Autowired
	private AppConfigProperties config;
	
	@GetMapping(value = "/version")
	public String projectVersion() {
		return config.getVersion();
	}
	
	@GetMapping(value = "/")
	public String helloWorld() {
		return config.getMessage();
	}
	
	@GetMapping("/user")
	public String getOidcUserPrincipal(@AuthenticationPrincipal OAuth2User principal) {
		return principal.getAttribute("login");
	}
}
