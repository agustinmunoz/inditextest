package com.agustin.munoz.inditex.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;


import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.exception.InditexDataAccessException;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.mapper.InditexMapper;
import com.agustin.munoz.inditex.openapi.model.Price;
import com.agustin.munoz.inditex.repository.PricesRepository;


import java.util.ArrayList;
import java.util.List;

import com.agustin.munoz.inditex.entity.PriceEntity;


@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
	
	
	@Mock
	PricesRepository pricesRepository;
	
	@Mock
	InditexMapper modelMapper;
	
	@InjectMocks
	PriceServiceImpl priceService;
	

	@Test
	void getPrices_OK_1() throws InditexException {
		
		
		String fecha="2020-06-14T00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime= null;
		dateTime = LocalDateTime.parse(fecha , formatter);
		

		
		List<PriceEntity> listPriceEntity = new ArrayList<>();
		PriceEntity priceEntity = new PriceEntity();
		priceEntity.setBrandId(1);
		priceEntity.setCurr("EUR");
		priceEntity.setEndDate(dateTime);
		priceEntity.setId(1L);
		priceEntity.setPrice(new BigDecimal(23.40));
		priceEntity.setPriceList(1);
		priceEntity.setPriority(1);
		priceEntity.setProductId(1);
		priceEntity.setStartDate(dateTime);
		listPriceEntity.add(priceEntity);
		
	
	
		Price priceMaximaPrioridad = new Price();
		priceMaximaPrioridad.setProductId(Long.valueOf("35455"));
		priceMaximaPrioridad.setBrandId(1);
		priceMaximaPrioridad.setPriceList(1);
		priceMaximaPrioridad.setStartDate("2020-06-14T00:00:00");
		priceMaximaPrioridad.setEndDate("2020-12-31T23:59:59");
		priceMaximaPrioridad.setPrice(35.40);
		priceMaximaPrioridad.setCurr("EUR");
	
		
		
		when(pricesRepository.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 35455, dateTime, dateTime)).
		thenReturn(listPriceEntity);
		when(modelMapper.priceEntityToModel(Mockito.any(PriceEntity.class))).thenReturn(priceMaximaPrioridad);
		
		
		Price response = priceService.getPrice(35455,1, "2020-06-14T00:00:00");
		assertThat(response.equals(priceMaximaPrioridad));
		
		
	}
	
	
	
	@Test
	void getPrices_OK_2() throws InditexException {
		
		
		String fecha="2020-06-14T00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime= null;
		dateTime = LocalDateTime.parse(fecha , formatter);
		

		
		List<PriceEntity> listPriceEntity = new ArrayList<>();
		PriceEntity priceEntity = new PriceEntity();
		priceEntity.setBrandId(1);
		priceEntity.setCurr("EUR");
		priceEntity.setEndDate(dateTime);
		priceEntity.setId(1L);
		priceEntity.setPrice(new BigDecimal(23.40));
		priceEntity.setPriceList(1);
		priceEntity.setPriority(1);
		priceEntity.setProductId(1);
		priceEntity.setStartDate(dateTime);
		
		PriceEntity priceEntity2 = new PriceEntity();
		priceEntity2.setBrandId(1);
		priceEntity2.setCurr("EUR");
		priceEntity2.setEndDate(dateTime);
		priceEntity2.setId(1L);
		priceEntity2.setPrice(new BigDecimal(35.40));
		
		priceEntity2.setPriceList(1);
		priceEntity2.setPriority(1);
		priceEntity2.setProductId(1);
		priceEntity2.setStartDate(dateTime);
		
		
		listPriceEntity.add(priceEntity);
		listPriceEntity.add(priceEntity2);
		

	Price priceMaximaPrioridad = new Price();
	priceMaximaPrioridad.setProductId(Long.valueOf("35455"));
	priceMaximaPrioridad.setBrandId(1);
	priceMaximaPrioridad.setPriceList(1);
	priceMaximaPrioridad.setStartDate("2020-06-14T00:00:00");
	priceMaximaPrioridad.setEndDate("2020-12-31T23:59:59");
	priceMaximaPrioridad.setPrice(35.40);
	priceMaximaPrioridad.setCurr("EUR");
	
		
		
		when(pricesRepository.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 35455, dateTime, dateTime)).
		thenReturn(listPriceEntity);
		when(modelMapper.priceEntityToModel(Mockito.any(PriceEntity.class))).thenReturn(priceMaximaPrioridad);
		
		
		Price response = priceService.getPrice(35455,1, "2020-06-14T00:00:00");
		assertThat(response.equals(priceMaximaPrioridad));
		
		
	}
	
	
	@Test
	void getPrices_KO_BBDD() throws InditexException {
		
		
		String fecha="2020-06-14T00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime= null;
		dateTime = LocalDateTime.parse(fecha , formatter);
		
		when(pricesRepository.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 35455, dateTime, dateTime)).
		thenThrow(new InditexDataAccessException("Error acceso a BBDD"));
		try {
		priceService.getPrice(35455,1, "2020-06-14T00:00:00");	
		} catch (InditexException e) {
		assertThat(InditexErrorMessages.DATA_EXCEPTION.getDescription()).isEqualTo(e.getMessage());
		assertThat(InditexErrorMessages.DATA_EXCEPTION.getCode()).isEqualTo(e.getInditexCode());
		assertThat(InditexErrorCodes.ERROR_500.getCode()).isEqualTo(e.getStatus().value());
		}

	}
	
	
	@Test
	void getPrices_KO_EmptyList() throws InditexException {
		


		Price response = priceService.getPrice(35455,1, "2020-06-14T00:00:00");
		assertThat(response).isNull();
			
		
		
	}
	
	
	
	@Test
	public void expectedExceptionDataAccess() {
	    try {
	        throw new InditexDataAccessException("Fallo acceso bbdd");

	    } catch (InditexDataAccessException e) {
	        assertThat(e).isNotNull();
	    }
	}
	
	@Test
	public void expectedException() {
	    try {
	        
	    	
	    	throw new InditexException(InditexErrorMessages.DATA_EXCEPTION.getDescription(),InditexErrorMessages.DATA_EXCEPTION.getCode(),HttpStatus.valueOf(InditexErrorCodes.ERROR_500.getCode()));

	    } catch (InditexException e) {
	    	
	    	assertThat(e.getInditexCode()).isEqualTo("INDITEX 05");
	        assertThat(e.getStatus().value()).isEqualTo(500);
	        assertThat(e.getMessage()).isEqualTo("Error data exception");
	    }
	}
	
	@Test
	public void expectedException2() {
	    try {
	        
	    	
	    	InditexException inditexException = new InditexException(InditexErrorMessages.DATA_EXCEPTION.getDescription());
	    	inditexException.setInditexCode(InditexErrorMessages.DATA_EXCEPTION.getCode());
	    	inditexException.setStatus(HttpStatus.valueOf(InditexErrorCodes.ERROR_500.getCode()));
	    	throw inditexException;

	    } catch (InditexException e) {
	    	
	    	assertThat(e.getInditexCode()).isEqualTo("INDITEX 05");
	        assertThat(e.getStatus().value()).isEqualTo(500);
	        assertThat(e.getMessage()).isEqualTo("Error data exception");
	    }
	}
	
	

	
	@Test
	public void runException(){
	  
	
		try {
	        throw new RuntimeException("RuntimeException");

	    } catch (RuntimeException e) {
	    	
	 
	   	assertThat(e.getMessage()).isEqualTo("RuntimeException");
	 
	    }
	}
	

}
