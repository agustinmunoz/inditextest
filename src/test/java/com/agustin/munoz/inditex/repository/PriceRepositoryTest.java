package com.agustin.munoz.inditex.repository;

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


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PriceRepositoryTest {
	
	@Autowired
	PricesRepository priceRepository;
	
		
	@Test
	void getFechas() {
		
		String fecha = "2020-06-14T00:00:00";
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime= null;
		dateTime = LocalDateTime.parse(fecha , formatter);
		

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
		
		
		String expectedToString = priceExpected.toString();
		
		int expectedHashCode = priceExpected.hashCode();
		

		
		listPriceEntitiesExpected.add(priceExpected);
		
		
		
		List<PriceEntity> listPriceEntitiesResponse = priceRepository.findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 35455, dateTime, dateTime);
		
		priceExpected.equals(listPriceEntitiesResponse.get(0));

		
		assertThat(listPriceEntitiesResponse.get(0).getBrandId()).isEqualTo(listPriceEntitiesExpected.get(0).getBrandId());
		assertThat(listPriceEntitiesResponse.get(0).getCurr()).isEqualTo(listPriceEntitiesExpected.get(0).getCurr());
		
		assertThat(listPriceEntitiesResponse.get(0).getEndDate()).isEqualTo(listPriceEntitiesExpected.get(0).getEndDate());
		assertThat(listPriceEntitiesResponse.get(0).getId()).isEqualTo(listPriceEntitiesExpected.get(0).getId());
		
		
		assertThat(listPriceEntitiesResponse.get(0).getPrice()).isEqualTo(listPriceEntitiesExpected.get(0).getPrice());
		assertThat(listPriceEntitiesResponse.get(0).getPriceList()).isEqualTo(listPriceEntitiesExpected.get(0).getPriceList());
		
		
		assertThat(listPriceEntitiesResponse.get(0).getPriority()).isEqualTo(listPriceEntitiesExpected.get(0).getPriority());
		assertThat(listPriceEntitiesResponse.get(0).getProductId()).isEqualTo(listPriceEntitiesExpected.get(0).getProductId());
		
		assertThat(listPriceEntitiesResponse.get(0).getStartDate()).isEqualTo(listPriceEntitiesExpected.get(0).getStartDate());
		
		assertThat(expectedToString).isEqualTo("PriceEntity(id=1, brandId=1, startDate=2020-06-14T00:00, endDate=2020-12-31T23:59:59, priceList=1, productId=35455, priority=0, price=35.50, curr=EUR)");
		
		assertThat(expectedHashCode).isNotNull();
		assertThat(listPriceEntitiesResponse).isEqualTo(listPriceEntitiesExpected );
	}
	
	
	

	

}
