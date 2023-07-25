package com.agustin.munoz.inditex.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agustin.munoz.inditex.entity.PriceEntity;
import com.agustin.munoz.inditex.openapi.model.Price;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MapperTest {
	
	
	@Autowired
	InditexMapperImpl mapper;
	
	
	@Test
	void mapperEntityToPrice() {
		

		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		

		PriceEntity priceExpected = new PriceEntity();
		priceExpected.setId(1L);
		priceExpected.setBrandId(1);
		priceExpected.setStartDate(LocalDateTime.parse("2020-06-14T00:00" , formatter));
		priceExpected.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59" , formatter));
		priceExpected.setPriceList(1);
		priceExpected.setProductId(35455);
		priceExpected.setPriority(0);
		priceExpected.setPrice(new BigDecimal(35.50).setScale(2, BigDecimal.ROUND_UP));
		
		priceExpected.setCurr("EUR");
		
		Price price = mapper.priceEntityToModel(priceExpected);
		
		
		Price responsePrice = new Price();
		responsePrice.setBrandId(1);
		responsePrice.setCurr("EUR");
		responsePrice.setEndDate("2020-12-31T23:59:59");
		responsePrice.setPrice(35.5);
		responsePrice.setPriceList(1);
		responsePrice.setProductId(Long.valueOf("35455"));
		responsePrice.setStartDate("2020-06-14T00:00:00");
		

		assertThat(price).isEqualTo(responsePrice);

}
	
	
	@Test
	void mapperPriceToEntity() {
		
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
		
		Price responsePrice = new Price();
		responsePrice.setBrandId(1);
		responsePrice.setCurr("EUR");
		responsePrice.setEndDate("2020-12-31T23:59:59");
		responsePrice.setPrice(35.50);
		responsePrice.setPriceList(1);
		responsePrice.setProductId(Long.valueOf("35455"));
		responsePrice.setStartDate("2020-06-14T00:00:00");
		

		PriceEntity priceEntity = mapper.priceModelToEntity(responsePrice);
		
	
	
		PriceEntity priceExpected = new PriceEntity();

		priceExpected.setBrandId(1);
		priceExpected.setStartDate(LocalDateTime.parse("2020-06-14T00:00" , formatter));
		priceExpected.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59" , formatter));
		priceExpected.setPriceList(1);
		priceExpected.setProductId(35455);
		priceExpected.setPrice(new BigDecimal(35.50));
		
		priceExpected.setCurr("EUR");
	

		assertThat(priceEntity).isEqualTo(priceExpected);

}
	
	
	
	
	@Test
	void mapperEntitiesToPrices() {

		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		

		List<PriceEntity> listPriceEntitiesExpected = new ArrayList<>();
		PriceEntity priceExpected = new PriceEntity();
		priceExpected.setId(1L);
		priceExpected.setBrandId(1);
		priceExpected.setStartDate(LocalDateTime.parse("2020-06-14T00:00" , formatter));
		priceExpected.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59" , formatter));
		priceExpected.setPriceList(1);
		priceExpected.setProductId(35455);
		priceExpected.setPriority(0);
		priceExpected.setPrice(new BigDecimal(35.50).setScale(2, BigDecimal.ROUND_UP));
		
		priceExpected.setCurr("EUR");
		
		listPriceEntitiesExpected.add(priceExpected);
		
		List<Price> priceList = mapper.priceEntitiesToModels(listPriceEntitiesExpected);
		
		
		List<Price> priceListResponse = new ArrayList<>();
		Price responsePrice = new Price();
		responsePrice.setBrandId(1);
		responsePrice.setCurr("EUR");
		responsePrice.setEndDate("2020-12-31T23:59:59");
		responsePrice.setPrice(35.5);
		responsePrice.setPriceList(1);
		responsePrice.setProductId(Long.valueOf("35455"));
		responsePrice.setStartDate("2020-06-14T00:00:00");
		
		priceListResponse.add(responsePrice);
		

		assertThat(priceList).isEqualTo(priceListResponse);

}
	
	
	
	
	
	@Test
	void mapperPricesToEntities() {
		
	
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


		List<Price> listPrice = new ArrayList<>();
		Price responsePrice = new Price();
		responsePrice.setBrandId(1);
		responsePrice.setCurr("EUR");
		responsePrice.setEndDate("2020-12-31T23:59:59");
		responsePrice.setPrice(35.50);
		responsePrice.setPriceList(1);
		responsePrice.setProductId(Long.valueOf("35455"));
		responsePrice.setStartDate("2020-06-14T00:00:00");
		
		listPrice.add(responsePrice);
		

		List<PriceEntity> priceEntityList = mapper.priceModeltToEntities(listPrice);
		

		
		List<PriceEntity> priceEntityListResponse = new ArrayList<>();
		PriceEntity priceExpected = new PriceEntity();
		priceExpected.setBrandId(1);
		priceExpected.setStartDate(LocalDateTime.parse("2020-06-14T00:00" , formatter));
		priceExpected.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59" , formatter));
		priceExpected.setPriceList(1);
		priceExpected.setProductId(35455);
		priceExpected.setPrice(new BigDecimal(35.50));
		
		priceExpected.setCurr("EUR");
	
		
		priceEntityListResponse.add(priceExpected);
		

		
		assertThat(priceEntityList).isEqualTo(priceEntityListResponse);

}
	
	
	@Test
	void mapperEntityToPriceNull() {
		

		PriceEntity priceExpected = null;	
		Price price = mapper.priceEntityToModel(priceExpected);
		
		assertThat(price).isNull();

}
	
	
	
	@Test
	void mapperPriceToEntityNull() {
		
		Price responsePrice = null;
		PriceEntity priceEntity = mapper.priceModelToEntity(responsePrice);
		assertThat(priceEntity).isNull();

}
	
	
	
	
	
}
