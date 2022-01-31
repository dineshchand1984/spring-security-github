package com.bit.wonders.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(value="com.bit.wonders.app")
public class AppConfigProperties {

	private String message;

	private String version;
}
