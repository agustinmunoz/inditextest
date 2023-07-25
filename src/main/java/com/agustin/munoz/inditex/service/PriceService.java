package com.agustin.munoz.inditex.service;

import com.agustin.munoz.inditex.openapi.model.Price;


public interface PriceService {
		
	Price getPrice(Integer productId, Integer brandId, String fecha);

}
