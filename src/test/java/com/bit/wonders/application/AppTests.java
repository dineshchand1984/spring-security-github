package com.bit.wonders.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.bit.wonders.web.HelloController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class AppTests {

	@LocalServerPort
	private int port;

	@Autowired
	private HelloController homeController;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName(value = "Loads application context and verifies if OK.")
	void contextLoads() {
		assertNotNull(homeController);
	}

	@Test
	@DisplayName(value = "Test controller")
	void testController() {
		assertEquals(homeController.projectVersion(), "1.0", "Seems fine");
		assertEquals(homeController.helloWorld(), "This is dev");
	}

	@Test
	@DisplayName(value = "Test controller using TestRestTemplate")
	void testTemplate() {
		assertNotNull(testRestTemplate);
		ResponseEntity<String> response = testRestTemplate.getForEntity("/hello", String.class);
		String repsonseText = response.getBody();
		assertEquals(repsonseText, "This is dev");
	}
}
