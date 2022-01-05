package com.emekamomodu.squadio;

import com.emekamomodu.squadio.model.request.AuthRequest;
import com.emekamomodu.squadio.model.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class SquadioApplicationTests extends AbstractTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void loginTestSuccess() throws Exception {

		String uri = "/auth/login";

		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername("admin");
		authRequest.setPassword("admin");

		String inputJson = super.mapToJson(authRequest);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();

		Response loginResponse = super.mapFromJson(content, Response.class);

		logger.info("loginResponse ::: " + loginResponse);

		assertEquals(loginResponse.getSuccess(), true);
		assertEquals(loginResponse.getMessage(), "User Login Successful");

		assertNotEquals(loginResponse.getData(), "N/A");
		assertNotEquals(loginResponse.getData(), null);

	}

}
