package com.emekamomodu.squadio.controller;

import com.emekamomodu.squadio.AbstractTest;
import com.emekamomodu.squadio.model.request.AuthRequest;
import com.emekamomodu.squadio.model.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/8/22 11:24 AM
 */
public class AuthControllerTests  extends AbstractTest  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @DisplayName("Admin user login successful")
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
