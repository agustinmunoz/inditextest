package com.agustin.munoz.inditex.api.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.exception.InditexDataAccessException;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.exception.InditexParserException;
import com.agustin.munoz.inditex.openapi.model.InditexErrorResponse;
import com.agustin.munoz.inditex.openapi.model.Price;
import com.agustin.munoz.inditex.repository.PricesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ApiServerMock {
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	private MockMvc mvc;
	
	@MockBean
	private PricesRepository pricesRepositoryMock;
	

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
		public void apiServerPrices_KO_InditexException() throws Exception {
			
	
		    
		    ObjectMapper objectMapper = new ObjectMapper();


		    when(pricesRepositoryMock.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
		    .thenThrow(new InditexDataAccessException("error BBDD"));
		    		
		 
			ResultActions response =  mvc.perform(get("/prices")
		            .param("productId", "35455")
		            .param("brandId", "1")
					.param("fecha", "2020-06-14T00:00:00"));
			
			MvcResult mvcr = response.andReturn();
			
			Exception errorException  = mvcr.getResolvedException();
			int status = mvcr.getResponse().getStatus();
			
			String responseString = mvcr.getResponse().getContentAsString();
			
			
			InditexErrorResponse responsePrice = objectMapper.readValue(responseString, InditexErrorResponse.class);
			
			InditexErrorResponse inditexErrorExpected= new InditexErrorResponse();
			inditexErrorExpected.message("Error data exception");
			//inditexErrorExpected.timestamp("2020-01-01T00:00:00");
			inditexErrorExpected.error("INDITEX 05");

			
			 assertThat(errorException.getClass()).isEqualTo(InditexException.class);
			 assertThat(responsePrice.getMessage()).isEqualTo(inditexErrorExpected.getMessage());
			 assertThat(responsePrice.getError()).isEqualTo(inditexErrorExpected.getError());
			 assertThat(responsePrice.getTimestamp()).isNotBlank();
			 assertThat(responsePrice.getTimestamp()).isNotNull();
			 assertEquals(status, HttpStatus.INTERNAL_SERVER_ERROR);
			 
			 responsePrice.toString();
			 responsePrice.hashCode();
			 responsePrice.equals(inditexErrorExpected);
			

		}
		
		
	  @Test
			public void apiServerPrices_KO_RuntimeException() throws Exception {
				
				
				
			    
			    ObjectMapper objectMapper = new ObjectMapper();
			    
			    when(pricesRepositoryMock.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
			    .thenThrow(new RuntimeException("error BBDD"));
			    		
			 
				ResultActions response =  mvc.perform(get("/prices")
			            .param("productId", "35455")
			            .param("brandId", "1")
						.param("fecha", "2020-06-14T00:00:00"));
				
				MvcResult mvcr = response.andReturn();
				
				Exception errorException  = mvcr.getResolvedException();
				int status = mvcr.getResponse().getStatus();
				
				String responseString = mvcr.getResponse().getContentAsString();
				
				
				InditexErrorResponse responsePrice = objectMapper.readValue(responseString, InditexErrorResponse.class);
				
				InditexErrorResponse inditexErrorExpected= new InditexErrorResponse();
				inditexErrorExpected.message("Error Interno");
				//inditexErrorExpected.timestamp("2020-01-01T00:00:00");
				inditexErrorExpected.error("INDITEX 04");

				
				 assertThat(errorException.getClass()).isEqualTo(RuntimeException.class);
				 assertThat(responsePrice.getMessage()).isEqualTo(inditexErrorExpected.getMessage());
				 assertThat(responsePrice.getError()).isEqualTo(inditexErrorExpected.getError());
				 assertThat(responsePrice.getTimestamp()).isNotBlank();
				 assertThat(responsePrice.getTimestamp()).isNotNull();
				 assertEquals(status, HttpStatus.INTERNAL_SERVER_ERROR);
				 
				 responsePrice.toString();
				 responsePrice.hashCode();
				 responsePrice.equals(inditexErrorExpected);
				

			}
	  
	  
	  
	  @Test
		public void apiServerPrices_KO_InditexParserException() throws Exception {
			
			
		    ObjectMapper objectMapper = new ObjectMapper();


		    when(pricesRepositoryMock.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
		    .thenThrow(new InditexParserException());
		    		
		 
			ResultActions response =  mvc.perform(get("/prices")
		            .param("productId", "35455")
		            .param("brandId", "1")
					.param("fecha", "2020-06-14T00:00:00"));
			
			MvcResult mvcr = response.andReturn();
			
			Exception errorException  = mvcr.getResolvedException();
			int status = mvcr.getResponse().getStatus();
			
			String responseString = mvcr.getResponse().getContentAsString();
			
			
			InditexErrorResponse responsePrice = objectMapper.readValue(responseString, InditexErrorResponse.class);
			
			InditexErrorResponse inditexErrorExpected= new InditexErrorResponse();
			inditexErrorExpected.message(InditexErrorMessages.PARSER_EXCEPTION.getDescription());
			//inditexErrorExpected.timestamp("2020-01-01T00:00:00");
			inditexErrorExpected.error(InditexErrorMessages.PARSER_EXCEPTION.getCode());

			
			 assertThat(errorException.getClass()).isEqualTo(InditexParserException.class);
			 assertThat(responsePrice.getMessage()).isEqualTo(inditexErrorExpected.getMessage());
			 assertThat(responsePrice.getError()).isEqualTo(inditexErrorExpected.getError());
			 assertThat(responsePrice.getTimestamp()).isNotBlank();
			 assertThat(responsePrice.getTimestamp()).isNotNull();
			 assertEquals(status, HttpStatus.INTERNAL_SERVER_ERROR);
			 
			 responsePrice.toString();
			 responsePrice.hashCode();
			 responsePrice.equals(inditexErrorExpected);
			

		}
			
		
	
	

}
