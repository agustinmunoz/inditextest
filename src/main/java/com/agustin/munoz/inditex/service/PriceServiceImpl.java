package com.agustin.munoz.inditex.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.agustin.munoz.inditex.entity.PriceEntity;
import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.exception.InditexDataAccessException;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.mapper.InditexMapper;
import com.agustin.munoz.inditex.openapi.model.Price;
import com.agustin.munoz.inditex.repository.PricesRepository;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {
	
    	private static Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

	    private final PricesRepository pricesRepository;

	    private final InditexMapper  modelMapper;
	    
		@Override
		public Price getPrice(Integer productId, Integer brandId, String fecha){
			logger.info("Conviertiendo formato fechas....");
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime dateTime= null;
			dateTime = LocalDateTime.parse(fecha , formatter);

			
			
			/*String text ="pruebas GlobalHandler";
			ObjectMapper obMapper = new ObjectMapper();
			try {
				Price pp = obMapper.readValue(text, Price.class);
			} catch (JsonProcessingException e1) {
				//throw new RuntimeException("Error de parseo", e1.getCause());
				throw new InditexException(InditexErrorMessages.BAD_REQUEST.getDescription(),InditexErrorMessages.BAD_REQUEST.getCode(),  HttpStatus.valueOf(InditexErrorCodes.ERROR_400.getCode()));
			}*/
			
			
			List<PriceEntity> pricesEntitiesList=null;
			try {
			logger.info("Consulta BBDD....");
			pricesEntitiesList =  pricesRepository.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(brandId, productId, dateTime, dateTime);
			} catch (InditexDataAccessException e) {
			logger.info("Error acceso repositorio....");
			throw new InditexException(InditexErrorMessages.DATA_EXCEPTION.getDescription(),InditexErrorMessages.DATA_EXCEPTION.getCode(),  HttpStatus.valueOf(InditexErrorCodes.ERROR_500.getCode()));
			}
	
			
			Price precioMaximaPrioridad = null;
			if (pricesEntitiesList.size()>0) {
			PriceEntity precioEntityMaximaPrioridad = Collections.max(pricesEntitiesList, Comparator.comparing(c -> c.getPriority()));
			precioMaximaPrioridad = modelMapper.priceEntityToModel(precioEntityMaximaPrioridad);
			
			} 
			
			return precioMaximaPrioridad;
		
		}


}
