package com.agustin.munoz.inditex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.validateMockitoUsage;


import java.io.File;
import java.nio.file.Files;
import java.time.format.DateTimeParseException;


import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.WebApplicationContext;
import com.agustin.munoz.inditex.openapi.model.InditexErrorResponse;
import com.agustin.munoz.inditex.openapi.model.Price;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FechasTest {
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	private MockMvc mvc;
	

	@BeforeEach
	public void setUp() {
	    mvc = MockMvcBuilders
	            .webAppContextSetup(webApplicationContext)
	            .build();
	    
	}
	
	
	  @AfterEach
	    public void tearDown() {
	        validateMockitoUsage();
	    }
	
	
	
	
	
	
	
	@Test
	public void test1_OK() throws Exception {
		
		
		
		
		File resource = new ClassPathResource("jsons/test1.json").getFile();
		
	    String resourceStrng = Files.readString(resource.toPath());
	    

	    JSONObject json = new JSONObject(resourceStrng);
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Price expectedPrice = objectMapper.readValue(json.getJSONObject("jsonBody").toString(), Price.class);

	 
		ResultActions response =  mvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
				.param("fecha", "2020-06-14T10:00:00"));
		
		MvcResult mvcr = response.andReturn();
		
		
		String responseString = mvcr.getResponse().getContentAsString();
		Price responsePrice = objectMapper.readValue(responseString, Price.class);
		
	
		
		 assertThat(responsePrice).isEqualTo(expectedPrice);
	    assertEquals(mvcr.getResponse().getStatus(), HttpStatus.OK);
	}
	
	
	@Test
	public void test2_OK() throws Exception {
		
	
		
		File resource = new ClassPathResource("jsons/test2.json").getFile();
		
	    String resourceStrng = Files.readString(resource.toPath());
	    

	    JSONObject json = new JSONObject(resourceStrng);
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Price expectedPrice = objectMapper.readValue(json.getJSONObject("jsonBody").toString(), Price.class);

	 
		ResultActions response =  mvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
				.param("fecha", "2020-06-14T16:00:00"));
		
		MvcResult mvcr = response.andReturn();
		
		
		String responseString = mvcr.getResponse().getContentAsString();
		Price responsePrice = objectMapper.readValue(responseString, Price.class);
		
		
		
		 assertThat(responsePrice).isEqualTo(expectedPrice);
	    assertEquals(mvcr.getResponse().getStatus(), HttpStatus.OK);
	}
	
	
	@Test
	public void test3_OK() throws Exception {
		
	
		
		File resource = new ClassPathResource("jsons/test3.json").getFile();
		
	    String resourceStrng = Files.readString(resource.toPath());
	    

	    JSONObject json = new JSONObject(resourceStrng);
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Price expectedPrice = objectMapper.readValue(json.getJSONObject("jsonBody").toString(), Price.class);

	 
		ResultActions response =  mvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
				.param("fecha", "2020-06-14T21:00:00"));
		
		MvcResult mvcr = response.andReturn();
		
		
		String responseString = mvcr.getResponse().getContentAsString();
		Price responsePrice = objectMapper.readValue(responseString, Price.class);
		
		
		
		 assertThat(responsePrice).isEqualTo(expectedPrice);
	    assertEquals(mvcr.getResponse().getStatus(), HttpStatus.OK);
	}
	
	
	@Test
	public void test4_OK() throws Exception {
		
	
		
		File resource = new ClassPathResource("jsons/test4.json").getFile();
		
	    String resourceStrng = Files.readString(resource.toPath());
	    

	    JSONObject json = new JSONObject(resourceStrng);
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Price expectedPrice = objectMapper.readValue(json.getJSONObject("jsonBody").toString(), Price.class);

	 
		ResultActions response =  mvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
				.param("fecha", "2020-06-15T10:00:00"));
		
		MvcResult mvcr = response.andReturn();
		
		
		String responseString = mvcr.getResponse().getContentAsString();
		Price responsePrice = objectMapper.readValue(responseString, Price.class);
		
		
		
		 assertThat(responsePrice).isEqualTo(expectedPrice);
	    assertEquals(mvcr.getResponse().getStatus(), HttpStatus.OK);
	}
	
	
	@Test
	public void test5_OK() throws Exception {
		
	
		
		File resource = new ClassPathResource("jsons/test5.json").getFile();
		
	    String resourceStrng = Files.readString(resource.toPath());
	    

	    JSONObject json = new JSONObject(resourceStrng);
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Price expectedPrice = objectMapper.readValue(json.getJSONObject("jsonBody").toString(), Price.class);

	 
		ResultActions response =  mvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
				.param("fecha", "2020-06-16T21:00:00"));
		
		MvcResult mvcr = response.andReturn();
		
		
		String responseString = mvcr.getResponse().getContentAsString();
		Price responsePrice = objectMapper.readValue(responseString, Price.class);
		
		
		
		 assertThat(responsePrice).isEqualTo(expectedPrice);
	    assertEquals(mvcr.getResponse().getStatus(), HttpStatus.OK);
	}
	
	
	
	
	@Test
	public void prueba_fecha1_missing_param_KO() throws Exception {
		
		
		
		
		
	    ObjectMapper objectMapper = new ObjectMapper();
	 
		ResultActions response =  mvc.perform(get("/prices")
	            .param("productId", "35455")
	           // .param("brandId", "1")
				.param("fecha", "2020-06-14T10:00:00"));
		
		MvcResult mvcr = response.andReturn();
		
		Exception errorException  = mvcr.getResolvedException();
		int status = mvcr.getResponse().getStatus();
		
		String responseString = mvcr.getResponse().getContentAsString();
		InditexErrorResponse responsePrice = objectMapper.readValue(responseString, InditexErrorResponse.class);
		
		InditexErrorResponse inditexErrorExpected= new InditexErrorResponse();
		inditexErrorExpected.message("Required request parameter 'brandId' for method parameter type Integer is not present");
		inditexErrorExpected.timestamp("2020-01-01T00:00:00");
		inditexErrorExpected.error("INDITEX 02");

		
		 assertThat(errorException.getClass()).isEqualTo(MissingServletRequestParameterException.class);
		 assertThat(responsePrice.getMessage()).isEqualTo(inditexErrorExpected.getMessage());
		 assertThat(responsePrice.getError()).isEqualTo(inditexErrorExpected.getError());
		 assertThat(responsePrice.getTimestamp()).isNotBlank();
		 assertThat(inditexErrorExpected.getTimestamp()).isEqualTo("2020-01-01T00:00:00");
		 assertEquals(status, HttpStatus.BAD_REQUEST);
		 
		 responsePrice.toString();
		 responsePrice.hashCode();
		 responsePrice.equals(inditexErrorExpected);
		 
	}
	
	
	
	@Test
	public void prueba_fecha_fail_data_parser_KO() throws Exception {
		
		

		
	    ObjectMapper objectMapper = new ObjectMapper();
	 
		ResultActions response =  mvc.perform(get("/prices")
	            .param("productId", "35455")
	            .param("brandId", "1")
				.param("fecha", "2020-06-14T10:0"));
		
		MvcResult mvcr = response.andReturn();
		
		Exception errorException  = mvcr.getResolvedException();
		int status = mvcr.getResponse().getStatus();
		
		String responseString = mvcr.getResponse().getContentAsString();
		
		InditexErrorResponse responsePrice = objectMapper.readValue(responseString, InditexErrorResponse.class);
		
		
		
		 assertThat(errorException.getClass()).isEqualTo(DateTimeParseException.class);
		 assertThat(responsePrice.getMessage()).isEqualTo("Text '2020-06-14T10:0' could not be parsed at index 14");
		 assertThat(responsePrice.getError()).isEqualTo("INDITEX 02");
		 assertThat(responsePrice.getTimestamp()).isNotBlank();
		 assertEquals(status, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
}
	


