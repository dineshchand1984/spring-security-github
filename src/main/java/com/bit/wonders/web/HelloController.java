package com.bit.wonders.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.wonders.application.AppConfigProperties;

@RestController
public class HelloController {

	public static String PROJECT_VERSION = "Version - 1.0";
	public static String CONTROLLER_MSG  = "Hello World @ Azure.";
	
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
	public OidcUser getOidcUserPrincipal(@AuthenticationPrincipal OidcUser principal) {
		return principal;
	}
}
