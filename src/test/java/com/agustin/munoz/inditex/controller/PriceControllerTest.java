package com.agustin.munoz.inditex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.openapi.model.Price;
import com.agustin.munoz.inditex.service.PriceService;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {
	
	
	@Mock
	PriceService priceService;
	
	
	@InjectMocks
	PricesController priceController;
	
	@Test
	void getPrices_OK() throws InditexException{
		
		
		Price priceMaximaPrioridad = new Price();
		priceMaximaPrioridad.setProductId(Long.valueOf("35455"));
		priceMaximaPrioridad.setBrandId(1);
		priceMaximaPrioridad.setPriceList(1);
		priceMaximaPrioridad.setStartDate("2020-06-14T00:00:00");
		priceMaximaPrioridad.setEndDate("2020-12-31T23:59:59");
		priceMaximaPrioridad.setPrice(35.40);
		priceMaximaPrioridad.setCurr("EUR");
		
		priceMaximaPrioridad.brandId(1);
		priceMaximaPrioridad.productId(Long.valueOf("35455"));
		priceMaximaPrioridad.priceList(1);
		priceMaximaPrioridad.startDate("2020-06-14T00:00:00");
		priceMaximaPrioridad.endDate("2020-12-31T23:59:59");
		priceMaximaPrioridad.price(35.40);
		priceMaximaPrioridad.curr("EUR");
		
		when(priceService.getPrice(35455, 1, "2020-06-14T00:00:00")).thenReturn(priceMaximaPrioridad);
		
		ResponseEntity<Price> response = priceController.getPrices(35455, 1, "2020-06-14T00:00:00");
		priceMaximaPrioridad.equals(response.getBody());
		assertThat(response.getBody().getBrandId()).isEqualTo(priceMaximaPrioridad.getBrandId());
		assertThat(response.getBody().getCurr()).isEqualTo(priceMaximaPrioridad.getCurr());
		assertThat(response.getBody().getEndDate()).isEqualTo(priceMaximaPrioridad.getEndDate());
		assertThat(response.getBody().getPrice()).isEqualTo(priceMaximaPrioridad.getPrice());
		assertThat(response.getBody().getPriceList()).isEqualTo(priceMaximaPrioridad.getPriceList());
		assertThat(response.getBody().getProductId()).isEqualTo(priceMaximaPrioridad.getProductId());
		assertThat(response.getBody().getStartDate()).isEqualTo(priceMaximaPrioridad.getStartDate());
	
		assertThat(priceMaximaPrioridad.toString()).isNotNull();
		assertThat(priceMaximaPrioridad.hashCode()).isNotNull();
		assertThat(response.getStatusCode().value()).isEqualTo(200);
		
	}
	

	@Test
	void getPrices_Empty_OK()  {
		
	
		when(priceService.getPrice(35455, 1, "2020-06-14T00:00:00")).thenReturn(null);
			
		ResponseEntity<Price> response = priceController.getPrices(35455, 1, "2020-06-14T00:00:00");
		assertThat(response.getBody()).isNull();
		assertThat(response.getStatusCode().value()).isEqualTo(InditexErrorCodes.ERROR_404.getCode());
	
		
	
	
}
	
}
	


