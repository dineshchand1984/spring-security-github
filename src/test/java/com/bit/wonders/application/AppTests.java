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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
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
	@WithMockUser
	@DisplayName(value = "Test controller")
	void testController() {
		assertEquals("1.0", homeController.projectVersion(), "Seems fine");
		assertEquals("This is dev", homeController.helloWorld());
	}

	@Test
	@DisplayName(value = "Test controller using TestRestTemplate")
	void testTemplate() {
		assertNotNull(testRestTemplate);
		String securedURL = "http://localhost:" + port + "/version";
		ResponseEntity<String> response = testRestTemplate.withBasicAuth("user", "password").getForEntity(securedURL,
				String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
