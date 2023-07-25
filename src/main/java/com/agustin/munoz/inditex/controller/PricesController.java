package com.agustin.munoz.inditex.controller;


import java.util.Optional;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.agustin.munoz.inditex.openapi.PricesApiDelegate;
import com.agustin.munoz.inditex.openapi.model.Price;
import com.agustin.munoz.inditex.service.PriceService;
import lombok.AllArgsConstructor;


@CrossOrigin()
@RestController
@AllArgsConstructor
public class PricesController implements PricesApiDelegate {
	

	private final PriceService priceService;


	@Override
	public ResponseEntity<Price> getPrices(Integer productId, Integer brandId, String fecha)  {
	
		
		
		return Optional.ofNullable(priceService.getPrice(productId, brandId, fecha))
				.map(price-> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(price))
				.orElse(ResponseEntity.notFound().build());
		
	

    }
	
}






