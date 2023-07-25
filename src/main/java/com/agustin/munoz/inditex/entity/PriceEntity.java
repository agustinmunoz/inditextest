package com.agustin.munoz.inditex.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import lombok.Data;


@Entity
@Data
@Table(name="prices")
public class PriceEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="brand_id")
	Integer brandId;
	
	@Column(name="start_date")
	LocalDateTime startDate;
	
	@Column(name="end_date")
	LocalDateTime endDate;
	
	@Column(name="price_list")
	Integer priceList;
	
	@Column(name="product_id")
	Integer productId;
	
	@Column(name="priority")
	Integer priority;
	

	@Column(name="price")
	BigDecimal price;
	
	@Column(name="curr")
	String curr;


}



