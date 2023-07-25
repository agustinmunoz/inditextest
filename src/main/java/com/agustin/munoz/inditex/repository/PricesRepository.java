package com.agustin.munoz.inditex.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agustin.munoz.inditex.entity.PriceEntity;




@Repository
public interface PricesRepository extends JpaRepository<PriceEntity, Long> {

	List<PriceEntity> findAllByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer brandId, Integer productId, LocalDateTime date,LocalDateTime date2);

}
