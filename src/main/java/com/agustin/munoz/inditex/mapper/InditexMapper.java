package com.agustin.munoz.inditex.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import com.agustin.munoz.inditex.entity.PriceEntity;
import com.agustin.munoz.inditex.openapi.model.Price;

	@Mapper(
	        componentModel = MappingConstants.ComponentModel.SPRING,
	        unmappedTargetPolicy = ReportingPolicy.IGNORE
	)
	public interface InditexMapper{

		Price priceEntityToModel(PriceEntity priceEntity);
		List<Price> priceEntitiesToModels (List<PriceEntity> priceEntitiesList);
	    
	    PriceEntity priceModelToEntity(Price price);
	    List<PriceEntity> priceModeltToEntities (List<Price> priceList);

}
